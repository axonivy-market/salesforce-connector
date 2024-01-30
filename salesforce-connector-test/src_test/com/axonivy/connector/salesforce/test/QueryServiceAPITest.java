package com.axonivy.connector.salesforce.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.axonivy.connector.salesforce.model.Opportunity;
import com.axonivy.connector.salesforce.utils.ConvertUtils;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonNode;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.ExecutionResult;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmElement;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmProcess;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;

@IvyProcessTest
public class QueryServiceAPITest extends BaseTest {
	private static final BpmProcess QUERYSERVICE_PROCESS = BpmProcess.path("QueryService");

	@Test
	void getAllOpps(BpmClient bpmClient)
			throws NoSuchFieldException, StreamReadException, DatabindException, IOException {
		BpmElement startable = QUERYSERVICE_PROCESS.elementName("call(String)");
		ExecutionResult result = bpmClient.start().subProcess(startable)
				.execute("Select FIELDS(ALL) from Opportunity LIMIT 200");
		JsonNode jsonNode = (JsonNode) result.data().last().get("jsonNode");

		List<Opportunity> opps = ConvertUtils.convertToListOpportunity(jsonNode);

		assertThat(opps.size()).isEqualTo(3);
		assertThat(opps.get(0).getName()).isEqualTo("Test 1");
	}
}
