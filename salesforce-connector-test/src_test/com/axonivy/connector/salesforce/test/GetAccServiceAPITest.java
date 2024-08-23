package com.axonivy.connector.salesforce.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.axonivy.connector.salesforce.model.Account;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.ExecutionResult;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmElement;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmProcess;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;

@IvyProcessTest
public class GetAccServiceAPITest extends BaseTest {
	private static final BpmProcess GETACCSERVICE_PROCESS = BpmProcess.path("GetAccService");

	@Test
	void getAccount(BpmClient bpmClient)
			throws NoSuchFieldException, StreamReadException, DatabindException, IOException {
		BpmElement startable = GETACCSERVICE_PROCESS.elementName("call(String)");

		ExecutionResult result = bpmClient.start().subProcess(startable).execute("123456789");
		Account response = (Account) result.data().last().get("acc");

		assertThat(response.getName()).isEqualTo("Acc test1");
	}
}
