package com.axonivy.connector.salesforce;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.primefaces.util.LangUtils;

import com.axonivy.connector.salesforce.dto.ActivityDTO;
import com.axonivy.connector.salesforce.dto.OpportunityDTO;
import com.axonivy.connector.salesforce.enums.Stage;
import com.axonivy.connector.salesforce.model.Account;
import com.axonivy.connector.salesforce.model.Opportunity;
import com.axonivy.connector.salesforce.model.OpportunityUpdateDTO;
import com.axonivy.connector.salesforce.utils.ConvertUtils;

import ch.ivyteam.ivy.process.call.SubProcessCall;

public class ListOppsBean {
	private List<Opportunity> opportunities;
	private List<OpportunityDTO> opps;
	private List<OpportunityDTO> filterOpps;
	private Opportunity selectedOpp;
	private String accountName;
	private List<Account> accs;
	private List<String> stages;
	private OpportunityUpdateDTO updateDTO;
	private ActivityDTO activityDTO;

	public ListOppsBean() {
		opps = new ArrayList<>();
		getAllOpps();

		opps = Utils.convertToOppDTO(opportunities);

	}

	public void openOpportunityDetail(String id) {
		selectedOpp = SubProcessCall.withPath("Functional Processes/getOpportunity")
				.withStartSignature("getOpportunity(String)").withParam("id", id).call().get("opp", Opportunity.class);
		accountName = Utils.getAccName(selectedOpp.getAccountId());
		getActivities();
	}

	private void getActivities() {
		activityDTO = new ActivityDTO();
		activityDTO.setTasks(Utils.getAllTasks(selectedOpp.getId()));
		activityDTO.setEvents(Utils.getAllEvents(selectedOpp.getId()));
	}

	public void getAllOpps() {
		opportunities = Utils.getAllOpps();
	}

	@SuppressWarnings("unchecked")
	public void getAllAccounts() {
		accs = (List<Account>) SubProcessCall.withPath("Functional Processes/getAccounts")
				.withStartSignature("getAllAccounts()").call().get("accs", Account.class);
	}

	// OwnerId is left unset -> Salesforce assigns the authenticated user as owner
	public void addNewOpportunity() {
		accountName = null;
		selectedOpp = new Opportunity();
		getAllAccounts();
		getListStages();
	}

	public void updateOpportunity(String id) {
		updateDTO = new OpportunityUpdateDTO();
		openOpportunityDetail(id);
		getAllAccounts();
		getListStages();
	}

	public void convertToUpdateDTO() throws IllegalAccessException, InvocationTargetException {
		selectedOpp.setAccountId(getAccountIdByName(accountName));
		updateDTO = ConvertUtils.convertToOpportunityObjUpdate(selectedOpp);
	}

	public void updateCurrentListAfterUpdate() {
		OptionalInt result = IntStream.range(0, opps.size())
				.filter(x -> selectedOpp.getId().equals(opps.get(x).getId())).findFirst();
		if (result.isPresent()) {
			int index = result.getAsInt();
			opps.set(index, Utils.convertToOppDTO(selectedOpp, updateDTO));
		}
	}

	public void beforeDelete(String id) {
		selectedOpp = SubProcessCall.withPath("Functional Processes/getOpportunity")
				.withStartSignature("getOpportunity(String)").withParam("id", id).call().get("opp", Opportunity.class);
	}

	public void updateCurrentListAfterDelete() {
		OptionalInt result = IntStream.range(0, opps.size())
				.filter(x -> selectedOpp.getId().equals(opps.get(x).getId())).findFirst();
		if (result.isPresent()) {
			int index = result.getAsInt();
			opps.remove(index);
		}
	}

	private void getListStages() {
		stages = Arrays.stream(Stage.values()).map(e -> e.getLabel()).collect(Collectors.toList());
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

	public String getAccountIdByName(String name) {
		String accId = null;
		Account acc = accs.stream().filter(t -> t.getName().equals(name)).findFirst().orElse(null);
		if (acc != null)
			accId = acc.getId();
		return accId;
	}

	public void reset() {
		selectedOpp = new Opportunity();
		accountName = null;
	}

	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
		if (LangUtils.isBlank(filterText)) {
			return true;
		}

		OpportunityDTO opp = (OpportunityDTO) value;
		return opp.getOppName().toLowerCase().contains(filterText)
				|| opp.getAccName().toLowerCase().contains(filterText)
				|| opp.getStage().toLowerCase().contains(filterText)
				|| opp.getCloseDate().toString().toLowerCase().contains(filterText);
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

	public List<OpportunityDTO> getOpps() {
		return opps;
	}

	public void setOpps(List<OpportunityDTO> opps) {
		this.opps = opps;
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

	public List<Account> getAccs() {
		return accs;
	}

	public void setAccs(List<Account> accs) {
		this.accs = accs;
	}

	public List<String> getStages() {
		return stages;
	}

	public OpportunityUpdateDTO getUpdateDTO() {
		return updateDTO;
	}

	public void setUpdateDTO(OpportunityUpdateDTO updateDTO) {
		this.updateDTO = updateDTO;
	}

	public List<OpportunityDTO> getFilterOpps() {
		return filterOpps;
	}

	public void setFilterOpps(List<OpportunityDTO> filterOpps) {
		this.filterOpps = filterOpps;
	}

	public ActivityDTO getActivityDTO() {
		return activityDTO;
	}

	public void setActivityDTO(ActivityDTO activityDTO) {
		this.activityDTO = activityDTO;
	}

}
