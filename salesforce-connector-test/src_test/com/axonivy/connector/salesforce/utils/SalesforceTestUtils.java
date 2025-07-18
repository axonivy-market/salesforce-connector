package com.axonivy.connector.salesforce.utils;

import com.axonivy.connector.salesforce.constant.SalesforceTestConstants;

import ch.ivyteam.ivy.environment.AppFixture;

public class SalesforceTestUtils {
	public static void setUpConfigForContext(String contextName, AppFixture fixture) {
		switch (contextName) {
		case SalesforceTestConstants.REAL_CALL_CONTEXT_DISPLAY_NAME:
			setUpConfigForApiTest(fixture);
			break;
		case SalesforceTestConstants.MOCK_SERVER_CONTEXT_DISPLAY_NAME:
			setUpConfigForMockServer(fixture);
			break;
		default:
			break;
		}
	}

	public static void setUpConfigForApiTest(AppFixture fixture) {
		String url = System.getProperty(SalesforceTestConstants.URL);
		String subDomain = System.getProperty(SalesforceTestConstants.SUB_DOMAIN);
		String clientId = System.getProperty(SalesforceTestConstants.CLIENT_ID);
		String clientSecret = System.getProperty(SalesforceTestConstants.CLIENT_SECRET);

		fixture.var("salesforceConnector.auth.url", url);
		fixture.var("salesforceConnector.auth.subdomain", subDomain);
		fixture.var("salesforceConnector.auth.clientId", clientId);
		fixture.var("salesforceConnector.auth.clientSecret", clientSecret);
	}

	public static void setUpConfigForMockServer(AppFixture fixture) {
		fixture.config("RestClients.SalesforceAPI.Features", "ch.ivyteam.ivy.rest.client.mapper.JsonFeature");
		fixture.var("salesforceConnector.auth.url", "{ivy.app.baseurl}/api/salesforceMock");
	}
}
