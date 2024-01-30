package com.axonivy.connector.salesforce.test;

import org.junit.jupiter.api.BeforeEach;

import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;
import ch.ivyteam.ivy.environment.AppFixture;

@IvyProcessTest
public class BaseTest {
	@BeforeEach
	void beforeEach(AppFixture fixture) {
		// Disable OAuth feature for mock rest service
		fixture.config("RestClients.SalesforceAPI.Features", "ch.ivyteam.ivy.rest.client.mapper.JsonFeature");
		fixture.var("salesforce-connector.auth.url", "http://localhost:8081/designer/api/salesforceMock");
	}
}
