package com.axonivy.connector.salesforce.oauth2;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import ch.ivyteam.di.restricted.DiCore;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.application.RuntimeLogCategory;
import ch.ivyteam.ivy.persistence.IPersistentTransaction;
import ch.ivyteam.ivy.security.ISecurityManager;
import ch.ivyteam.ivy.security.ISession;
import ch.ivyteam.ivy.security.ISessionExtension;
import ch.ivyteam.ivy.security.IUser;

/**
 * Store that provides a token store per session
 * @author rwei
 * @since 9.2
 */
public final class SessionTokenStore {
  private final Map<String, TokenStore> services = new ConcurrentHashMap<>();

  static SessionTokenStore current() {
    var session = ISession.current();
    SessionTokenStore store ;
    try {
    	store = (SessionTokenStore) session.getAttribute(SessionTokenStore.class.getName());
    }catch(Exception e) {
    	store = null;
    }
    if (store == null) {
      store = new SessionTokenStore();
      session.setAttribute(SessionTokenStore.class.getName(), store);
    }
    return store;
  }

  TokenStore forService(String service) {
    return services.computeIfAbsent(service, SessionTokenStore::createForService);
  }

  private static TokenStore createForService(String service) {
    return new TokenStore(ISession.current(), service, IProcessModelVersion.current().getRuntimeLog(RuntimeLogCategory.REST_CLIENT));
  }

  public static void clear() {
    var manager = DiCore.getGlobalInjector().getInstance(ISecurityManager.class);
    manager.getSessions().forEach(SessionTokenStore::clearFrom);
  }

  private static void clearFrom(ISession session) {
    session.removeAttribute(SessionTokenStore.class.getName());
  }

  public static void start() {
    DiCore.getGlobalInjector().getInstance(ISecurityManager.class).addSessionExtension(SessionExtension.INSTANCE);
  }

  public static void stop() {
    DiCore.getGlobalInjector().getInstance(ISecurityManager.class).removeSessionExtension(SessionExtension.INSTANCE);
  }

  private static final class SessionExtension implements ISessionExtension {
    private static final SessionExtension INSTANCE = new SessionExtension();

    @Override
    public void logoutSession(ISession session, IPersistentTransaction transaction, long currentTaskId) {
      clearFrom(session);
    }

    @Override
    public void loginSession(ISession session, IPersistentTransaction transaction, IUser sessionUser) {
      clearFrom(session);
    }
  }
}
