package com.axonivy.connector.salesforce.auth;

import java.net.URI;
import java.util.Optional;

import javax.ws.rs.Priorities;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import ch.ivyteam.ivy.bpm.error.BpmPublicErrorBuilder;
import ch.ivyteam.ivy.rest.client.FeatureConfig;
import ch.ivyteam.ivy.rest.client.oauth2.OAuth2BearerFilter;
import ch.ivyteam.ivy.rest.client.oauth2.OAuth2RedirectErrorBuilder;
import ch.ivyteam.ivy.rest.client.oauth2.OAuth2TokenRequester.AuthContext;
import ch.ivyteam.ivy.rest.client.oauth2.uri.OAuth2CallbackUriBuilder;
import ch.ivyteam.ivy.rest.client.oauth2.uri.OAuth2UriProperty;

public class OAuth2Feature implements Feature {
	public static interface Default {
		String AUTH_URI = "https://login.salesforce.com/services/oauth2/token";
	}

	public static interface Property {
		String APP_ID = AuthConstant.AUTH + AuthConstant.DOT + AuthConstant.APP_ID;
		String SECRET_KEY = AuthConstant.AUTH + AuthConstant.DOT + AuthConstant.SECRET_KEY;
		String SCOPE = AuthConstant.AUTH + AuthConstant.DOT + AuthConstant.SCOPE;
		String AUTH_BASE_URI = AuthConstant.AUTH + AuthConstant.DOT + AuthConstant.AUTH_BASE_URI;
		String USE_APP_PERMISSIONS = AuthConstant.AUTH + AuthConstant.DOT + AuthConstant.USE_APP_PERMISSIONS;
		String USE_USER_PASS_FLOW = AuthConstant.AUTH + AuthConstant.DOT + AuthConstant.USE_USER_PASS_FLOW;

		String USER = AuthConstant.AUTH + AuthConstant.DOT + AuthConstant.USER;
		String PASS = AuthConstant.AUTH + AuthConstant.DOT + AuthConstant.PASS;
	}

	@Override
	public boolean configure(FeatureContext context) {
		var config = new FeatureConfig(context.getConfiguration(), OAuth2Feature.class);
		var graphUri = new OAuth2UriProperty(config, Property.AUTH_BASE_URI, Default.AUTH_URI);
		var oauth2 = new OAuth2BearerFilter(ctxt -> requestToken(ctxt, graphUri), graphUri);
		oauth2.tokenSuffix(() -> GrantType.of(config).type);
		context.register(oauth2, Priorities.AUTHORIZATION);
		return true;
	}

	private static Response requestToken(AuthContext ctxt, OAuth2UriProperty uriFactory) {
		FeatureConfig config = ctxt.config;
		var authCode = ctxt.authCode();
		var refreshToken = ctxt.refreshToken();
		GrantType grant = GrantType.of(config);
		if (authCode.isEmpty() && refreshToken.isEmpty() && GrantType.AUTH_CODE == grant) {
			authError(config, uriFactory).withMessage("missing permission from user to act in his name.").throwError();
		}

		Form form = createTokenPayload(config, authCode, refreshToken);
		var response = ctxt.target.request().accept(MediaType.WILDCARD).post(Entity.form(form));
		return response;
	}

	static Form createTokenPayload(FeatureConfig config, Optional<String> authCode, Optional<String> refreshToken) {
		Form form = new Form();
		form.param(AuthConstant.CLIENT_ID, config.readMandatory(Property.APP_ID));
		form.param(AuthConstant.CLIENT_SECRET, config.readMandatory(Property.SECRET_KEY));
		GrantType grant = GrantType.of(config);
		form.param(AuthConstant.GRANT_TYPE, grant.type);
		configureGrant(config, authCode, form, grant);
		if (refreshToken.isPresent()) {
			form.param(AuthConstant.REDIRECT_URI, ivyCallbackUri().toASCIIString());
			form.param(AuthConstant.REFRESH_TOKEN, refreshToken.get());
			form.asMap().putSingle(AuthConstant.GRANT_TYPE, AuthConstant.REFRESH_TOKEN);
		}
		return form;
	}

	private static void configureGrant(FeatureConfig config, Optional<String> authCode, Form form, GrantType grant) {
		switch (grant) {
		case APPLICATION:
			break;
		case PASSWORD:
			form.param(AuthConstant.USER_NAME, config.readMandatory(Property.USER));
			form.param(AuthConstant.PASSWORD, config.readMandatory(Property.PASS));
			break;
		default:
		case AUTH_CODE:
			form.param(AuthConstant.REDIRECT_URI, ivyCallbackUri().toASCIIString());
			authCode.ifPresent(code -> form.param("code", code));
		}
	}

	private static BpmPublicErrorBuilder authError(FeatureConfig config, OAuth2UriProperty uriFactory) {
		var uri = createMsAuthCodeUri(config, uriFactory);
		return OAuth2RedirectErrorBuilder.create(uri).withMessage("Missing permission from user to act in his name.");
	}

	private static URI createMsAuthCodeUri(FeatureConfig config, OAuth2UriProperty uriFactory) {
		return UriBuilder.fromUri(uriFactory.getUri("authorize"))
				.queryParam("client_id", config.readMandatory(Property.APP_ID))
				.queryParam("response_type", "code").queryParam("response_mode", "query").build();
	}

	private static URI ivyCallbackUri() {
		return OAuth2CallbackUriBuilder.create().toUrl();
	}

	private static enum GrantType {
		/** work in the name of a user: requires user consent **/
		AUTH_CODE("authorization_code"),

		APPLICATION("client_credentials"),

		/** weak security: app acts as pre-configured personal user! **/
		PASSWORD("password");

		private String type;

		GrantType(String type) {
			this.type = type;
		}

		static GrantType of(FeatureConfig config) {
			if (isAppAuth(config)) {
				return GrantType.APPLICATION;
			}
			if (isUserPassAuth(config)) {
				return GrantType.PASSWORD;
			}
			return GrantType.AUTH_CODE;
		}

		private static boolean isAppAuth(FeatureConfig config) {
			return toBoolean(config.read(Property.USE_APP_PERMISSIONS));
		}

		private static boolean isUserPassAuth(FeatureConfig config) {
			return toBoolean(config.read(Property.USE_USER_PASS_FLOW));
		}

		private static boolean toBoolean(Optional<String> value) {
			return value.map(Boolean::parseBoolean).orElse(Boolean.FALSE);
		}
	}

}
