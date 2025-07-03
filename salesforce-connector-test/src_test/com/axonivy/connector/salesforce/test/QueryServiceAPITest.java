package com.axonivy.connector.salesforce.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;

import com.axonivy.connector.salesforce.constant.SalesforceConstant;
import com.axonivy.connector.salesforce.context.MultiEnvironmentContextProvider;
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

@IvyProcessTest(enableWebServer = true)
@ExtendWith(MultiEnvironmentContextProvider.class)
public class QueryServiceAPITest extends BaseTest {
	private static final BpmProcess QUERYSERVICE_PROCESS = BpmProcess.path("QueryService");

	@TestTemplate
	void getAllOpps(ExtensionContext context, BpmClient bpmClient)
			throws NoSuchFieldException, StreamReadException, DatabindException, IOException {
		boolean isRealContext = context.getDisplayName().equals(SalesforceConstant.REAL_CALL_CONTEXT_DISPLAY_NAME);
		BpmElement startable = QUERYSERVICE_PROCESS.elementName("call(String)");
		ExecutionResult result =
				bpmClient.start().subProcess(startable).execute("Select FIELDS(ALL) from Opportunity LIMIT 200");
		JsonNode jsonNode = (JsonNode) result.data().last().get("jsonNode");

		List<Opportunity> opps = ConvertUtils.convertToListOpportunity(jsonNode);
		if (!isRealContext) {
			assertThat(opps.size()).isEqualTo(3);
			assertThat(opps.get(0).getName()).isEqualTo("Test 1");
		} else {
			assertThat(opps.size()).isGreaterThan(1);
		}

	}
}
