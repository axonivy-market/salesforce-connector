package com.axonivy.connector.salesforce.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import com.axonivy.connector.salesforce.model.Opportunity;
import com.axonivy.utils.e2etest.context.MultiEnvironmentContextProvider;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.ExecutionResult;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmElement;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmProcess;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;

@IvyProcessTest(enableWebServer = true)
@ExtendWith(MultiEnvironmentContextProvider.class)
public class GetOppServiceAPITest extends BaseTest {
  private static final BpmProcess GETOPPSERVICE_PROCESS = BpmProcess.path("GetOppService");

  @TestTemplate
  void getOpportunity(BpmClient bpmClient)
      throws NoSuchFieldException, StreamReadException, DatabindException, IOException {
    BpmElement startable = GETOPPSERVICE_PROCESS.elementName("call(String)");

    ExecutionResult result = bpmClient.start().subProcess(startable).execute("0065g00000aaS29AAE");
    Opportunity response = (Opportunity) result.data().last().get("opportunity");

    assertThat(response.getName()).isEqualTo("Pyramid Emergency Generators");
  }
}
