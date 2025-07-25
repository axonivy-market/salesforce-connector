package ch.ivyteam.ivy.rest.client.oauth2;


	import java.util.Optional;

	import javax.ws.rs.client.WebTarget;
	import javax.ws.rs.core.Response;

	import ch.ivyteam.api.PublicAPI;
	import ch.ivyteam.ivy.rest.client.FeatureConfig;
	import ch.ivyteam.ivy.rest.client.oauth2.uri.OAuth2UriProvider;

	/**
	 * Requests a OAuth2 bearer access token
	 * @author rwei
	 * @since 9.2
	 */
	@PublicAPI
	public interface OAuth2TokenRequesterNew {
	  /**
	   * Requests a OAuth2 bearer access token
	   * @param ctxt authentication context
	   * @return response from the remote accessToken request. Yet with an unread entity.
	   */
	  @PublicAPI
	  Response requestToken(AuthContextNew ctxt);

	  /**
	   * The authentication context to be used to fire an 'accessToken' request.
	   * @since 9.2
	   */
	  @PublicAPI
	  public static class AuthContextNew {
	    /** JAX-RS client pre-configured to call the accessToken URI defined by {@link OAuth2UriProvider#getTokenUri()} */
	    @PublicAPI
	    public final WebTarget target;

	    /** Current feature configuration provided by Rest Client properties or programmatic features. */
	    @PublicAPI
	    public final FeatureConfig config;

	    private final Optional<String> authCode;
	    private final Optional<String> refreshToken;

	    AuthContextNew(WebTarget target, FeatureConfig config, String authCode, String refreshToken) {
	      this.target = target;
	      this.config = config;
	      this.authCode = Optional.ofNullable(authCode);
	      this.refreshToken = Optional.ofNullable(refreshToken);
	    }

	    /**
	     * @return User access <code>code</code> query parameter obtained from an OAuth2 '/auth' user consent request. Or {@link Optional#empty()}
	     *         when an implicit grant is being implemented.
	     */
	    @PublicAPI
	    public Optional<String> authCode() {
	      return authCode;
	    }

	    /**
	     * @return Refresh token if one is available
	     */
	    public Optional<String> refreshToken() {
	      return refreshToken;
	    }
	  }
	}
