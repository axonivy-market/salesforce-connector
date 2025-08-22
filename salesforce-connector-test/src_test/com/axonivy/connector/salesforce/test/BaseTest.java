package com.axonivy.connector.salesforce.test;

import static com.axonivy.utils.e2etest.enums.E2EEnvironment.REAL_SERVER;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtensionContext;

import com.axonivy.connector.salesforce.constant.SalesforceTestConstants;
import com.axonivy.utils.e2etest.utils.E2ETestUtils;

import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;
import ch.ivyteam.ivy.environment.AppFixture;

@IvyProcessTest(enableWebServer = true)
public class BaseTest {
  protected boolean isRealContext;

  @BeforeEach
  void beforeEach(ExtensionContext context, AppFixture fixture) {
    isRealContext = context.getDisplayName().equals(REAL_SERVER.getDisplayName());
    E2ETestUtils.determineConfigForContext(context.getDisplayName(), runRealEnv(fixture), runMockEnv(fixture));
  }

  private Runnable runRealEnv(AppFixture fixture) {
    return () -> {
      String url = System.getProperty(SalesforceTestConstants.URL);
      String subDomain = System.getProperty(SalesforceTestConstants.SUB_DOMAIN);
      String clientId = System.getProperty(SalesforceTestConstants.CLIENT_ID);
      String clientSecret = System.getProperty(SalesforceTestConstants.CLIENT_SECRET);

      fixture.var("salesforceConnector.auth.url", url);
      fixture.var("salesforceConnector.auth.subdomain", subDomain);
      fixture.var("salesforceConnector.auth.clientId", clientId);
      fixture.var("salesforceConnector.auth.clientSecret", clientSecret);
    };
  }

  static Runnable runMockEnv(AppFixture fixture) {
    return () -> {
      fixture.config("RestClients.SalesforceAPI.Features", "ch.ivyteam.ivy.rest.client.mapper.JsonFeature");
      fixture.var("salesforceConnector.auth.url", "{ivy.app.baseurl}/api/salesforceMock");
    };
  }
}
