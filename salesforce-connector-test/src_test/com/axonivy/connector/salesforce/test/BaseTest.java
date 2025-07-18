package com.axonivy.connector.salesforce.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtensionContext;

import com.axonivy.connector.salesforce.utils.SalesforceTestUtils;

import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;
import ch.ivyteam.ivy.environment.AppFixture;

@IvyProcessTest(enableWebServer = true)
public class BaseTest {

	@BeforeEach
	void beforeEach(ExtensionContext context, AppFixture fixture) {
		SalesforceTestUtils.setUpConfigForContext(context.getDisplayName(), fixture);
	}
}
