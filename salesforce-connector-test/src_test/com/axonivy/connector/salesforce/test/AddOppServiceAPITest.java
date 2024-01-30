package com.axonivy.connector.salesforce.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.Date;

import org.junit.jupiter.api.Test;

import com.axonivy.connector.salesforce.model.Opportunity;
import com.axonivy.connector.salesforce.response.CreateOppResponse;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.ExecutionResult;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmElement;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmProcess;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;

@IvyProcessTest
public class AddOppServiceAPITest extends BaseTest {
	private static final BpmProcess ADDOPPSERVICE_PROCESS = BpmProcess.path("AddOppService");

	@Test
	void addNewOpp(BpmClient bpmClient)
			throws NoSuchFieldException, StreamReadException, DatabindException, IOException {
		Opportunity opportunity = new Opportunity();
		opportunity.setName("Test 1");
		opportunity.setStageName("Stage Name test 1");
		opportunity.setCloseDate(new Date());
		BpmElement startable = ADDOPPSERVICE_PROCESS.elementName("call(Opportunity)");

		ExecutionResult result = bpmClient.start().subProcess(startable).execute(opportunity);
		CreateOppResponse response = (CreateOppResponse) result.data().last().get("oppResponse");

		assertThat(response.getId()).isEqualTo("0065h00000OxIEAAA3");
	}
}
