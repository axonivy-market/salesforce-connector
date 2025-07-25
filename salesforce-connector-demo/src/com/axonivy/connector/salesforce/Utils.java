package com.axonivy.connector.salesforce;

import java.util.ArrayList;
import java.util.List;

import com.axonivy.connector.salesforce.model.Account;
import com.axonivy.connector.salesforce.model.Event;
import com.axonivy.connector.salesforce.model.Lead;
import com.axonivy.connector.salesforce.model.Opportunity;
import com.axonivy.connector.salesforce.model.Task;

import ch.ivyteam.ivy.process.call.SubProcessCall;

public class Utils {
	public static String getAccName(String accountId) {
		if(accountId!=null) {
		Account acc = SubProcessCall.withPath("Functional Processes/getAccount")
				.withStartSignature("getAccount(String)")
				.withParam("id", accountId)
				.call()
				.get("acc", Account.class);

		return acc.getName();
		}else return "";
	}
	public static String getOppName(String oppId) {
		if(oppId!=null) {
		Opportunity opp = SubProcessCall.withPath("Functional Processes/getOpportunity")
				.withStartSignature("getOpportunity(String)")
				.withParam("id", oppId)
				.call()
				.get("opp", Opportunity.class);

		return opp.getName();
		}else return "";
	}

	@SuppressWarnings("unchecked")
	public static List<Opportunity> getAllOpps() {
		List<Opportunity> opportunities = new ArrayList<>();
		opportunities = (List<Opportunity>) SubProcessCall.withPath("Functional Processes/getAllOpps")
				.withStartSignature("getAllOpps()")
				.call()
				.get("opps", Opportunity.class);

		return opportunities;
	}

	@SuppressWarnings("unchecked")
	public static List<Task> getAllTasks() {
		List<Task> tasks = new ArrayList<>();
		tasks = (List<Task>) SubProcessCall.withPath("Functional Processes/getAllTasks")
				.withStartSignature("getAllTasks()")
				.call()
				.get("tasks", Task.class);

		return tasks;
	}

	@SuppressWarnings("unchecked")
	public static List<Lead> getAllLeads() {
		List<Lead> tasks = new ArrayList<>();
		tasks = (List<Lead>) SubProcessCall.withPath("Functional Processes/getAllLeads")
				.withStartSignature("getAllLeads()")
				.call()
				.get("leads", Lead.class);

		return tasks;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Task> getAllTasks(String oppId) {
		List<Task> tasks = new ArrayList<>();
		tasks = (List<Task>) SubProcessCall.withPath("Functional Processes/findAllTaskByOpportunityId")
				.withStartSignature("findTask(String)")
				.withParam("oppId", oppId)
				.call()
				.get("tasks", Task.class);

		return tasks;
	}

	@SuppressWarnings("unchecked")
	public static List<Event> getAllEvents(String oppId) {
		List<Event> events = new ArrayList<>();
		events = (List<Event>) SubProcessCall.withPath("Functional Processes/findAllEventByOpportunityId")
				.withStartSignature("findEvent(String)")
				.withParam("oppId", oppId)
				.call()
				.get("events", Event.class);

		return events;
	}

//	public static List<OpportunityDTO> convertToOppDTO(List<Opportunity> list) {
//		return list.stream().map( o -> new OpportunityDTO(o.getId(), o.getName(), getAccName(o.getAccountId()), o.getAmount(), o.getCloseDate(), o.getStageName())).collect(Collectors.toList());
//	}

//	public static OpportunityDTO convertToOppDTO(Opportunity o) {
//		return new OpportunityDTO(o.getId(), o.getName(), getAccName(o.getAccountId()), o.getAmount(), o.getCloseDate(), o.getStageName());
//	}
//
//	public static OpportunityDTO convertToOppDTO(Opportunity o, OpportunityUpdateDTO o1) {
//		return new OpportunityDTO(o.getId(), o1.getName(), getAccName(o1.getAccountId()), o1.getAmount(), o1.getCloseDate(), o1.getStageName());
//	}
}
