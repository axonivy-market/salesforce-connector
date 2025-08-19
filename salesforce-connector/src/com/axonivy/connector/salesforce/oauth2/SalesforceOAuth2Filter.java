package com.axonivy.connector.salesforce.oauth2;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import ch.ivyteam.ivy.rest.client.oauth2.OAuth2TokenRequester;
import ch.ivyteam.ivy.rest.client.oauth2.Token;
import ch.ivyteam.ivy.rest.client.oauth2.uri.OAuth2UriProvider;

public class SalesforceOAuth2Filter extends ch.ivyteam.ivy.rest.client.oauth2.OAuth2BearerFilter {

  public SalesforceOAuth2Filter(OAuth2TokenRequester getToken, OAuth2UriProvider uriFactory) {
    super(getToken, uriFactory);
  }

  @Override
  protected boolean isExpired(Token token) {
    return isXPercentOfExpiry(token, 49); // Saleforce tokens expires 50% sooner if no user activity
  }

  /**
   * Some services dont wait till expiry, if token not used for e.g. 50 % of expiry time, expiry is reached...
   * Use this method to expiry token sooner, specify how many percent sooner
   * @param percent how many percent of expiry, if eg.50 that means - half of expiry date already reached
   * @return true if expired according to percentage
   */
  private boolean isXPercentOfExpiry(Token token, int percent) {
    var expiresAt = token.createdAt().plus(token.expiresIn() / 100 * percent, ChronoUnit.SECONDS);
    return Instant.now().isAfter(expiresAt);
  }
}
