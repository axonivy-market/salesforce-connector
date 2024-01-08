package com.axonivy.connector.salesforce.utils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.axonivy.connector.salesforce.model.Account;
import com.axonivy.connector.salesforce.model.Event;
import com.axonivy.connector.salesforce.model.Opportunity;
import com.axonivy.connector.salesforce.model.OpportunityUpdateDTO;
import com.axonivy.connector.salesforce.model.Task;
import com.axonivy.connector.salesforce.response.CreateOppResponse;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertUtils {
	private static final ObjectMapper mapper = new ObjectMapper();
	private static final String RECORDS = "records";
	
	public static Opportunity convertToOpportunity(JsonNode jsonNode) throws StreamReadException, DatabindException, IOException {
		Opportunity opportunity = mapper.readValue( mapper.treeAsTokens(jsonNode), Opportunity.class);
		return opportunity;
	}
	
	public static List<Opportunity> convertToListOpportunity(JsonNode jsonNode) throws StreamReadException, DatabindException, IOException {
		JsonNode node = jsonNode.findValue(RECORDS);
		
		List<Opportunity> opportunities = mapper.readValue(mapper.treeAsTokens(node), new TypeReference<List<Opportunity>>(){});
		return opportunities;
	}
	
	public static Account convertToAccount(JsonNode jsonNode) throws StreamReadException, DatabindException, IOException {
		Account acc = mapper.readValue( mapper.treeAsTokens(jsonNode), Account.class);
		return acc;
	}
	
	public static List<Account> convertToListAccounts(JsonNode jsonNode) throws StreamReadException, DatabindException, IOException {
		JsonNode node = jsonNode.findValue(RECORDS);
		
		List<Account> accounts = mapper.readValue(mapper.treeAsTokens(node), new TypeReference<List<Account>>(){});
		return accounts;
	}
	
	public static List<Task> convertToListTask(JsonNode jsonNode) throws StreamReadException, DatabindException, IOException {
		JsonNode node = jsonNode.findValue(RECORDS);
		
		List<Task> tasks = mapper.readValue(mapper.treeAsTokens(node), new TypeReference<List<Task>>(){});
		return tasks;
	}
	
	public static List<Event> convertToListEvent(JsonNode jsonNode) throws StreamReadException, DatabindException, IOException {
		JsonNode node = jsonNode.findValue(RECORDS);
		
		List<Event> events = mapper.readValue(mapper.treeAsTokens(node), new TypeReference<List<Event>>(){});
		return events;
	}
	
	public static OpportunityUpdateDTO convertToOpportunityObjUpdate(Opportunity opportunity) throws IllegalAccessException, InvocationTargetException {
		OpportunityUpdateDTO dto = new OpportunityUpdateDTO();
		BeanUtils.copyProperties(dto, opportunity);
		return dto;
	}
	
	public static CreateOppResponse convertToOpportunityResponse(JsonNode jsonNode) throws StreamReadException, DatabindException, IOException {
		return mapper.readValue( mapper.treeAsTokens(jsonNode), CreateOppResponse.class);
	}
}
