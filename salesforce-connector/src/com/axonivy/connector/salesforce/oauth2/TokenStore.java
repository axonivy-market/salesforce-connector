package com.axonivy.connector.salesforce.oauth2;

import java.util.concurrent.atomic.AtomicReference;

import org.apache.log4j.Logger;

import ch.ivyteam.api.API;
import ch.ivyteam.ivy.security.ISession;

/**
 * Keeps OAUTH2 access tokens per session and rest-service.
 *
 * @since 9.2
 */
public class TokenStore {
  private final ISession session;
  private final String service;
  private final Logger log;
  private final AtomicReference<Token> token = new AtomicReference<>();

  public static TokenStore get(String service) {
    return SessionTokenStore.current().forService(service);
  }

  TokenStore(ISession session, String service, Logger log) {
    API.checkParameterNotNull(session, "session");
    API.checkParameterNotBlank(service, "service");
    API.checkParameterNotNull(log, "log");
    this.session = session;
    this.service = service;
    this.log = log;
  }

  public boolean hasToken() {
    return getToken() != null;
  }

  public void setToken(Token newToken) {
    log.debug("Storing service-token for '" + service + "' on session '" + session + "' value: " + newToken);
    token.set(newToken);
  }

  public Token getToken() {
    log.debug("Reading service-token for '" + service + "' on session '" + session + "' value: " + token);
    return token.get();
  }
}
