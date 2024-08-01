package com.axonivy.connector.salesforce.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.Date;

import org.junit.jupiter.api.Test;

import com.axonivy.connector.salesforce.model.OpportunityUpdateDTO;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.ExecutionResult;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmElement;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmProcess;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;

@IvyProcessTest(enableWebServer = true)
public class UpdateOppServiceAPITest extends BaseTest {
	private static final BpmProcess UPPDATEOPPSERVICE_PROCESS = BpmProcess.path("UpdateOppService");

	@Test
	void updateOpp(BpmClient bpmClient)
			throws NoSuchFieldException, StreamReadException, DatabindException, IOException {
		OpportunityUpdateDTO opportunity = new OpportunityUpdateDTO();
		opportunity.setName("Test 1");
		opportunity.setStageName("Stage Name test 1");
		opportunity.setCloseDate(new Date());
		BpmElement startable = UPPDATEOPPSERVICE_PROCESS.elementName("call(String,OpportunityUpdateDTO)");

		ExecutionResult result = bpmClient.start().subProcess(startable).execute("123456789", opportunity);
		OpportunityUpdateDTO response = (OpportunityUpdateDTO) result.data().last().get("opportunity");

		assertThat(response.getName()).isEqualTo("Test 1");
	}
}
