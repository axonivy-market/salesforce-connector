package com.axonivy.connector.salesforce.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils2.BeanUtils;

import com.axonivy.connector.salesforce.model.Account;
import com.axonivy.connector.salesforce.model.Event;
import com.axonivy.connector.salesforce.model.Opportunity;
import com.axonivy.connector.salesforce.model.OpportunityUpdateDTO;
import com.axonivy.connector.salesforce.model.Task;
import com.axonivy.connector.salesforce.response.CreateOppResponse;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

public class ConvertUtils {
	private static final ObjectMapper mapper = new JsonMapper();
	private static final String RECORDS = "records";

	public static Opportunity convertToOpportunity(JsonNode jsonNode) {
		return mapper.treeToValue(jsonNode, Opportunity.class);
	}

	public static List<Opportunity> convertToListOpportunity(JsonNode jsonNode) {
		JsonNode node = jsonNode.findValue(RECORDS);

		return mapper.treeToValue(node, new TypeReference<List<Opportunity>>() {});
	}

	public static Account convertToAccount(JsonNode jsonNode) {
		return mapper.treeToValue(jsonNode, Account.class);
	}

	public static List<Account> convertToListAccounts(JsonNode jsonNode) {
		JsonNode node = jsonNode.findValue(RECORDS);

		return mapper.treeToValue(node, new TypeReference<List<Account>>() {});
	}

	public static List<Task> convertToListTask(JsonNode jsonNode) {
		JsonNode node = jsonNode.findValue(RECORDS);

		return mapper.treeToValue(node, new TypeReference<List<Task>>() {});
	}

	public static List<Event> convertToListEvent(JsonNode jsonNode) {
		JsonNode node = jsonNode.findValue(RECORDS);

		return mapper.treeToValue(node, new TypeReference<List<Event>>() {});
	}

	public static OpportunityUpdateDTO convertToOpportunityObjUpdate(Opportunity opportunity)
			throws IllegalAccessException, InvocationTargetException {
		OpportunityUpdateDTO dto = new OpportunityUpdateDTO();
		BeanUtils.copyProperties(dto, opportunity);
		return dto;
	}

	public static CreateOppResponse convertToOpportunityResponse(JsonNode jsonNode) {
		return mapper.treeToValue(jsonNode, CreateOppResponse.class);
	}
}
