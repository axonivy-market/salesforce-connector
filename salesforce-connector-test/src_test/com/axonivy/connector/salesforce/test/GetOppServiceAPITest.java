package com.axonivy.connector.salesforce.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.axonivy.connector.salesforce.model.Opportunity;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.ExecutionResult;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmElement;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmProcess;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;

@IvyProcessTest
public class GetOppServiceAPITest extends BaseTest {
	private static final BpmProcess GETOPPSERVICE_PROCESS = BpmProcess.path("GetOppService");

	@Test
	void getOpportunity(BpmClient bpmClient)
			throws NoSuchFieldException, StreamReadException, DatabindException, IOException {
		BpmElement startable = GETOPPSERVICE_PROCESS.elementName("call(String)");

		ExecutionResult result = bpmClient.start().subProcess(startable).execute("123456789");
		Opportunity response = (Opportunity) result.data().last().get("opportunity");

		assertThat(response.getName()).isEqualTo("Test 1");
	}
}
