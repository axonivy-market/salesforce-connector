package com.axonivy.connector.salesforce.utils;

import com.axonivy.connector.salesforce.constant.SalesforceConstant;

import ch.ivyteam.ivy.environment.AppFixture;

public class SalesforceUtils {
	public static void setUpConfigForContext(String contextName, AppFixture fixture) {
		switch (contextName) {
		case SalesforceConstant.REAL_CALL_CONTEXT_DISPLAY_NAME:
			setUpConfigForApiTest(fixture);
			break;
		case SalesforceConstant.MOCK_SERVER_CONTEXT_DISPLAY_NAME:
			setUpConfigForMockServer(fixture);
			break;
		default:
			break;
		}
	}

	public static void setUpConfigForApiTest(AppFixture fixture) {
		String url = System.getProperty(SalesforceConstant.URL);
		String subDomain = System.getProperty(SalesforceConstant.SUB_DOMAIN);
		String clientId = System.getProperty(SalesforceConstant.CLIENT_ID);
		String clientSecret = System.getProperty(SalesforceConstant.CLIENT_SECRET);

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
