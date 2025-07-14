package com.axonivy.connector.salesforce.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;

import com.axonivy.connector.salesforce.constant.SalesforceConstant;
import com.axonivy.connector.salesforce.context.MultiEnvironmentContextProvider;
import com.axonivy.connector.salesforce.model.Account;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.ExecutionResult;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmElement;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmProcess;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;

@IvyProcessTest(enableWebServer = true)
@ExtendWith(MultiEnvironmentContextProvider.class)
public class GetAccServiceAPITest extends BaseTest {
	private static final BpmProcess GETACCSERVICE_PROCESS = BpmProcess.path("GetAccService");

	@TestTemplate
	void getAccount(ExtensionContext context, BpmClient bpmClient)
			throws NoSuchFieldException, StreamReadException, DatabindException, IOException {
		boolean isRealContext = context.getDisplayName().equals(SalesforceConstant.REAL_CALL_CONTEXT_DISPLAY_NAME);
		BpmElement startable = GETACCSERVICE_PROCESS.elementName("call(String)");

		ExecutionResult result = bpmClient.start().subProcess(startable).execute("0015g00001UvqSvAAJ");
		Account response = (Account) result.data().last().get("acc");
		if (isRealContext) {
			assertThat(response.getName()).isEqualTo("Pyramid Construction Inc.");
		} else {
			assertThat(response.getName()).isEqualTo("Acc test1");
		}
	}
}
