package com.axonivy.connector.salesforce.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.Date;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import com.axonivy.connector.salesforce.model.Opportunity;
import com.axonivy.connector.salesforce.model.OpportunityUpdateDTO;
import com.axonivy.connector.salesforce.response.CreateOppResponse;
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
public class AddOppServiceAPITest extends BaseTest {
  private static final BpmProcess ADDOPPSERVICE_PROCESS = BpmProcess.path("AddOppService");
  private static final BpmProcess DELETEOPPSERVICE_PROCESS = BpmProcess.path("DeleteOppService");
  private static final BpmProcess UPPDATEOPPSERVICE_PROCESS = BpmProcess.path("UpdateOppService");

  @TestTemplate
  void addNewOpp(ExtensionContext context, BpmClient bpmClient)
      throws NoSuchFieldException, StreamReadException, DatabindException, IOException {
    Opportunity opportunity = new Opportunity();
    opportunity.setName("Test 1");
    opportunity.setStageName("Stage Name test 1");
    opportunity.setCloseDate(new Date());
    BpmElement startable = ADDOPPSERVICE_PROCESS.elementName("call(Opportunity)");

    ExecutionResult result = bpmClient.start().subProcess(startable).execute(opportunity);
    CreateOppResponse response = (CreateOppResponse) result.data().last().get("oppResponse");
    if (isRealContext) {
      assertNotNull(response.getId());
      OpportunityUpdateDTO updateOpportunityDTO = new OpportunityUpdateDTO();
      updateOpportunityDTO.setName("Test 1 update");
      updateOpportunityDTO.setStageName("Stage Name test 1");
      updateOpportunityDTO.setCloseDate(new Date());
      BpmElement updateStartable = UPPDATEOPPSERVICE_PROCESS.elementName("call(String,OpportunityUpdateDTO)");

      ExecutionResult updateResult =
          bpmClient.start().subProcess(updateStartable).execute(response.getId(), updateOpportunityDTO);
      OpportunityUpdateDTO updateResponse = (OpportunityUpdateDTO) updateResult.data().last().get("opportunity");
      assertThat(updateResponse.getName()).isEqualTo("Test 1 update");

      BpmElement deleteStartable = DELETEOPPSERVICE_PROCESS.elementName("call(String)");
      ExecutionResult deleteResult = bpmClient.start().subProcess(deleteStartable).execute(response.getId());
      assertThat(response.getId()).isEqualTo((String) deleteResult.data().last().get("id"));
    } else {
      assertThat(response.getId()).isEqualTo("0065h00000OxIEAAA3");
    }
  }
}
