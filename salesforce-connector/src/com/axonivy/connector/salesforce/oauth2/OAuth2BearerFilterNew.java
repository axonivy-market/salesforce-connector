package com.axonivy.connector.salesforce.oauth2;

import java.io.IOException;
import java.time.Instant;
import java.util.Map;
import java.util.function.Supplier;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response.Status.Family;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.connector.salesforce.oauth2.OAuth2TokenRequesterNew.AuthContextNew;

import ch.ivyteam.api.PublicAPI;
import ch.ivyteam.ivy.bpm.error.BpmError;
import ch.ivyteam.ivy.bpm.error.BpmPublicErrorBuilder;
import ch.ivyteam.ivy.request.IRequest;
import ch.ivyteam.ivy.rest.client.FeatureConfig;
import ch.ivyteam.ivy.rest.client.RestClientFactoryConstants;
import ch.ivyteam.ivy.rest.client.internal.oauth2.RedirectToIdentityProvider;
import ch.ivyteam.ivy.rest.client.oauth2.OAuth2TokenRequester;
import ch.ivyteam.ivy.rest.client.oauth2.uri.OAuth2UriProvider;
import ch.ivyteam.ivy.security.ISession;

/**
 * Filter to simplify OAUTH2 authorization flows.
 *
 * <p>
 * It initiates OAUTH2 authentication requests to resolve a 'Bearer' token which
 * is sub-sequently added as 'Authorization' request header.
 * </p>
 *
 *
 * @since 9.2
 */
@Provider
@PublicAPI
public class OAuth2BearerFilterNew implements javax.ws.rs.client.ClientRequestFilter {
	private static final String AUTHORIZATION = "Authorization";
	private static final String BEARER = "Bearer ";
	public static final String CODE_PARAM = "code";

	private final OAuth2TokenRequesterNew getToken;
	private final OAuth2UriProvider uriFactory;

	private static final String TOKEN_SEPARATOR = ":";
	public static final String AUTH_SCOPE_PROPERTY = "AUTH.scope";
	private String property;
	private Supplier<String> name = null;

	/**
	 * @param getToken   a {@link OAuth2TokenRequester} implementation that actually
	 *                   does the 'accessToken' call to the OAUTH2 authority in case
	 *                   the current session has not already a valid accessToken.
	 * @param uriFactory an {@link OAuth2UriProvider} to setup OAUTH2 authority URIs
	 *                   for the various requests that may must be fired (.e.g
	 *                   /auth, /token, /userinfo, ...)
	 */
	@PublicAPI
	public OAuth2BearerFilterNew(OAuth2TokenRequesterNew getToken, OAuth2UriProvider uriFactory) {
		this.getToken = getToken;
		this.uriFactory = uriFactory;
	}

	/**
	 * @param tokenSuffix to uniquely identify and separate this token from other on
	 *                    the same restClient and {@link ISession session}.
	 * @return this instance, for fluent building
	 * @since 10.0.3
	 */
	@PublicAPI
	public OAuth2BearerFilterNew tokenSuffix(Supplier<String> tokenSuffix) {
		this.name = tokenSuffix;
		return this;
	}

	/**
	 * Sets a rest-client property, that will be used to isolate your auth-token
	 * from other calls to the rest-service, that work with the same {@link ISession
	 * session}.
	 *
	 * <p>
	 * By default we read the rest-client property {@value #AUTH_SCOPE_PROPERTY}, if
	 * it exists and holds a value, it will be used.<br/>
	 * You can pass another property name, if you happen to have your own 'scope'
	 * property.
	 * </p>
	 *
	 * @param scopeProperty the property of your rest client feature configuration,
	 *                      that holds the "scope" (permissions) to ask for.
	 * @return this instance, for fluent building
	 * @since 10.0.3
	 */
	@PublicAPI
	public OAuth2BearerFilterNew tokenScopeProperty(String scopeProperty) {
		this.property = scopeProperty;
		return this;
	}

	@Override
	public void filter(ClientRequestContext context) throws IOException {
		if (uriFactory.isAuthRequest(context.getUri())) { // already in token request: avoid stackOverflow
			return;
		}

		if (context.getHeaders().containsKey(AUTHORIZATION)) { // already set by other feature or explicit header
			return;
		}

		Token accessToken = getAccessToken(context);
		context.getHeaders().add(AUTHORIZATION, BEARER + accessToken.accessToken());
	}

	protected final Token getAccessToken(ClientRequestContext context) {
		FeatureConfig config = new FeatureConfig(context.getConfiguration(), getSource());
		TokenStore store = TokenStore.get(createKey(config));
		var token = store.getToken();
		if (token == null || token.isExpired()|| token.isXPercentOfExpiry(49)) {//saleforce tokens expiry 50% sooner if no user activity
		      if (token == null || !token.hasRefreshToken()) {
		        token = getNewAccessToken(context.getClient(), config);
		      } else {
		        token = getRefreshedAccessToken(context.getClient(), config, token.refreshToken());
		      }
		      store.setToken(token); // keep for following requests
		    }
		if (!token.hasAccessToken()) {
			store.setToken(null);
			authError().withMessage("Failed to read 'access_token' or refresh token from " + token).throwError();
		}

		return token;// .accessToken();
	}

	String createKey(FeatureConfig config) {
		Object clientId = config.readMandatory(RestClientFactoryConstants.PROPERTY_CLIENT_ID);
		var key = new StringBuilder((String) clientId);
		if (property != null) {
			key.append(TOKEN_SEPARATOR).append(config.readMandatory(property));
		} else {
			config.read(AUTH_SCOPE_PROPERTY).ifPresent(scope -> key.append(TOKEN_SEPARATOR).append(scope));
		}
		if (name != null) {
			key.append(TOKEN_SEPARATOR).append(name.get());
		}
		return key.toString();
	}

	private Class<?> getSource() {
		Class<?> type = getToken.getClass();
		Class<?> declaring = type.getDeclaringClass();
		if (declaring != null) {
			return declaring;
		}
		return type;
	}

	private Token getNewAccessToken(Client client, FeatureConfig config) {
		return getAccessToken(client, config, null);
	}

	private Token getRefreshedAccessToken(Client client, FeatureConfig config, String refreshToken) {
		return getAccessToken(client, config, refreshToken);
	}

	private Token getAccessToken(Client client, FeatureConfig config, String refreshToken) {
		GenericType<Map<String, Object>> mapType = new GenericType<>(Map.class);
		var tokenUri = uriFactory.getTokenUri();
		String authCode = getAuthCode();
		var authContext = new AuthContextNew(client.target(tokenUri), config, authCode, refreshToken);
		var response = getToken.requestToken(authContext);
		if (response.getStatusInfo().getFamily() == Family.SUCCESSFUL) {
			Map<String, Object> mapValues = response.readEntity(mapType);
			return new Token(mapValues);// new String(m.get("access_token").toString());
		}
		throw authError().withMessage("Failed to get or refresh access token: " + response)
				.withAttribute("status", response.getStatus())
				.withAttribute("payload", response.readEntity(String.class)).build();
	}

	protected String getAuthCode() {
		var request = IRequest.current();
		if (request == null) {
			return null;
		}
		var authCode = request.getFirstParameter(CODE_PARAM);
		if (StringUtils.isBlank(authCode)) {
			return null;
		}
		return authCode;
	}

	private static BpmPublicErrorBuilder authError() {
		return BpmError.create(RedirectToIdentityProvider.OAUTH2_ERROR_CODE);
	}
}
