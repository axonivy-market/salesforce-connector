package com.axonivy.connector.salesforce;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.beanutils.BeanUtils;
import org.primefaces.util.LangUtils;

import com.axonivy.connector.salesforce.model.Account;
import com.axonivy.connector.salesforce.model.Lead;
import com.axonivy.connector.salesforce.model.Opportunity;
import com.axonivy.connector.salesforce.utils.ConvertUtils;

import ch.ivyteam.ivy.process.call.SubProcessCall;

public class ListLeadsBean {
	
	private Set<String> statuses = new TreeSet<>();
	private List<Lead> leads;
	private List<Lead> filteredLeads;
	private Lead selected;
	private String accountName;
	private String ownerId;
	private List<Opportunity> opportunities;
	private Opportunity selectedOpp;
	private List<Account> accs;
	private String whatOpportunity;

	public ListLeadsBean() {
		ownerId = ConvertUtils.extractOwnerId();

		getAllOpps();
		getAllLeads();
		getAllAccounts();

	}
	@SuppressWarnings("unchecked")
	public void getAllAccounts() {
		accs = (List<Account>) SubProcessCall.withPath("Functional Processes/getAccounts")
				.withStartSignature("getAllAccounts()").call().get("accs", Account.class);
	}

	public void updateLead(String id) {
		openLeadDetail(id);
	}

	public void openLeadDetail(String id) {
		selected = SubProcessCall.withPath("Functional Processes/getLead")
				.withStartSignature("getLead(String)").withParam("id", id).call().get("lead", Lead.class);
		accountName = Utils.getAccName(selected.getConvertedAccountId());
		
		whatOpportunity= Utils.getOppName(selected.getConvertedOpportunityId());
		selected.setAccName(whatOpportunity);
	}

	public List<String> completeLeadStatus(String query) {
		
		String queryLowerCase = query.toLowerCase();
		List<String> res = statuses.stream().filter(s -> s.toLowerCase().startsWith(queryLowerCase))
				.collect(Collectors.toList());
		if(res.isEmpty()) {
			res.add("Open - Not Contacted");
			res.add("Working - Contacted");
			res.add("Closed - Converted");
			res.add("Closed - Not Converted");

		}
		return res;
	}

	public void getAllOpps() {
		opportunities = Utils.getAllOpps();
	}
	public void getAllLeads() {
		setLeads(Utils.getAllLeads());
		for(Lead l : leads) {
			l.setAccName(getOpportunityNameById(l.getConvertedOpportunityId()));
			statuses.add(l.getStatus());
		}
		 
	}

	public void addNewLead() {
		accountName = null;
		setSelected(new Lead());
		getSelected().setOwnerId(ownerId);
	}


	public void beforeDelete(String id) {
		setSelected(SubProcessCall.withPath("Functional Processes/getLead")
				.withStartSignature("getLead(String)").withParam("id", id).call().get("lead", Lead.class));
	}



	public List<String> completeAccount(String query) {
		String queryLowerCase = query.toLowerCase();
		List<String> accountList = new ArrayList<>();
		List<Account> acccountList = accs;
		for (Account acc : acccountList) {
			accountList.add(acc.getName());
		}

		return accountList.stream().filter(t -> t.toLowerCase().startsWith(queryLowerCase))
				.collect(Collectors.toList());
	}

	
	public List<String> completeOpportunity(String query) {
		String queryLowerCase = query.toLowerCase();
		List<String> opportunityList = new ArrayList<>();
		List<Opportunity> oppDropdown = opportunities;
		for (Opportunity opp : oppDropdown) {
			opportunityList.add(opp.getName());
		}

		return opportunityList.stream().filter(t -> t.toLowerCase().startsWith(queryLowerCase))
				.collect(Collectors.toList());
	}

	public String getAccountIdByName(String name) {
		String accId = null;
		Account acc = accs.stream().filter(t -> t.getName().equals(name)).findFirst().orElse(null);
		if (acc != null)
			accId = acc.getId();
		return accId;
	}

	public void reset() {
		setSelected(new Lead());
		accountName = null;
		whatOpportunity= null;
		getAllOpps();
		getAllLeads();
		getAllAccounts();

	}

	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
		if (LangUtils.isBlank(filterText)) {
			return true;
		}

		Lead lead = (Lead) value;
		boolean containsText = lead.getCity()!=null&&lead.getCity().toLowerCase().contains(filterText)
				|| lead.getCompany()!=null&&lead.getCompany().toLowerCase().contains(filterText)
				|| lead.getCreatedDate()!=null&&lead.getCreatedDate().toInstant().toString().contains(filterText)
				|| lead.getDescription()!=null&&lead.getDescription().toLowerCase().contains(filterText)
				|| lead.getEmail()!=null&&lead.getEmail().toString().toLowerCase().contains(filterText)
		|| lead.getFirstName()!=null&&lead.getFirstName().toString().toLowerCase().contains(filterText)
		|| lead.getLastName()!=null&&lead.getLastName().toString().toLowerCase().contains(filterText)
		|| lead.getName()!=null&&lead.getName().toString().toLowerCase().contains(filterText);

		if(!containsText) {
			containsText=lead.toString().toLowerCase().contains(filterText);
		}
		
		return containsText;
		
		
	}

	public String getOpportunityIdByName(String name) {
		String oppId = null;
		Opportunity opp = opportunities.stream().filter(t -> t.getName().equals(name)).findFirst().orElse(null);
		if (opp != null)
			oppId = opp.getId();
		return oppId;
	}

	public String getOpportunityNameById(String id) {
		if(id!=null&&!id.isBlank()) {
		String oppName = null;
		Opportunity opp = opportunities.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
		if (opp != null)
			oppName = opp.getName();
		return oppName;
		}else { return "";}				
				
	}

	public String getAccountName(String id) {
		return Utils.getAccName(id);
	}

	public List<Opportunity> getOpportunities() {
		return opportunities;
	}

	public void setOpportunities(List<Opportunity> opportunities) {
		this.opportunities = opportunities;
	}

	public Opportunity getSelectedOpp() {
		return selectedOpp;
	}

	public void setSelectedOpp(Opportunity selectedOpp) {
		this.selectedOpp = selectedOpp;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public List<Account> getAccs() {
		return accs;
	}

	public void setAccs(List<Account> accs) {
		this.accs = accs;
	}

	public Lead getSelected() {
		return selected;
	}

	public void setSelected(Lead selected) {
		this.selected = selected;
	}


	public String getWhatOpportunity() {
		return whatOpportunity;
	}

	public void setWhatOpportunity(String whatOpportunity) {
		this.whatOpportunity = whatOpportunity;
	}
	public List<Lead> getLeads() {
		return leads;
	}
	public void setLeads(List<Lead> leads) {
		this.leads = leads;
	}
	public List<Lead> getFilteredLeads() {
		return filteredLeads;
	}
	public void setFilteredLeads(List<Lead> filteredLeads) {
		this.filteredLeads = filteredLeads;
	}

	
}
