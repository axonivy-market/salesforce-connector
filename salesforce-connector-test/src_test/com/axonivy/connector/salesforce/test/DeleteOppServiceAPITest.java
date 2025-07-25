package com.axonivy.connector.salesforce.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.connector.salesforce.utils.SalesforceTestUtils;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.ExecutionResult;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmElement;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmProcess;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;
import ch.ivyteam.ivy.environment.AppFixture;

@IvyProcessTest(enableWebServer = true)
public class DeleteOppServiceAPITest {
	private static final BpmProcess DELETEOPPSERVICE_PROCESS = BpmProcess.path("DeleteOppService");

	@BeforeEach
	void beforeEach(AppFixture fixture) {
		SalesforceTestUtils.setUpConfigForMockServer(fixture);
	}

	@Test
	void deleteOpportunity(BpmClient bpmClient)
			throws NoSuchFieldException, StreamReadException, DatabindException, IOException {

		BpmElement startable = DELETEOPPSERVICE_PROCESS.elementName("call(String)");

		ExecutionResult result = bpmClient.start().subProcess(startable).execute("123456789");

		String id = (String) result.data().last().get("id");
		assertThat(id).isEqualTo("123456789");
	}
}
