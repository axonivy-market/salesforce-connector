package com.axonivy.connector.salesforce;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean2;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean2;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.beanutils.converters.DateConverter;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.util.LangUtils;

import com.axonivy.connector.salesforce.dto.ActivityDTO;
import com.axonivy.connector.salesforce.enums.Stage;
import com.axonivy.connector.salesforce.model.Account;
import com.axonivy.connector.salesforce.model.Opportunity;
import com.axonivy.connector.salesforce.utils.ConvertUtils;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCall;

public class ListOppsBean {
	private List<Opportunity> opportunities;
	//private List<OpportunityDTO> opps;
	private List<Opportunity> filterOpps;
	private Opportunity selectedOpp;
	private String accountName;
	private BarChartModel barModel;
	private String ownerId;
	private List<Account> accs;
	private List<String> stages;
	//private OpportunityUpdateDTO updateDTO;
	private ActivityDTO activityDTO;

	public ListOppsBean() {
		ownerId = ConvertUtils.extractOwnerId();
		getAllOpps();

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

	public void addNewOppotunity() {
		accountName = null;
		selectedOpp = new Opportunity();
		selectedOpp.setOwnerId(ownerId);
		getAllAccounts();
		getListStages();
	}

	public void updateOppotunity(String id) {
		//updateDTO = new OpportunityUpdateDTO();
		openOpportunityDetail(id);
		getAllAccounts();
		getListStages();
	}

	public void convertToUpdateDTO() throws IllegalAccessException, InvocationTargetException {
		selectedOpp.setAccountId(getAccountIdByName(accountName));
		//updateDTO = ConvertUtils.convertToOpportunityObjUpdate(selectedOpp);
	}

//	public void updateCurrentListAfterUpdate() {
//		OptionalInt result = IntStream.range(0, opportunities.size())
//				.filter(x -> selectedOpp.getId().equals(opportunities.get(x).getId())).findFirst();
//		if (result.isPresent()) {
//			int index = result.getAsInt();
//			opportunities.set(index, selectedOpp);
//		}
//	}

	public void beforeDelete(String id) {
		selectedOpp = SubProcessCall.withPath("Functional Processes/getOpportunity")
				.withStartSignature("getOpportunity(String)").withParam("id", id).call().get("opp", Opportunity.class);
	}

//	public void updateCurrentListAfterDelete() {
//		OptionalInt result = IntStream.range(0, opportunities.size())
//				.filter(x -> selectedOpp.getId().equals(opportunities.get(x).getId())).findFirst();
//		if (result.isPresent()) {
//			int index = result.getAsInt();
//			opportunities.remove(index);
//		}
//	}

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
		getAllOpps();
	}

	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
		if (LangUtils.isBlank(filterText)) {
			return true;
		}

		Opportunity opp = (Opportunity) value;
		boolean containsText = opp.getName()!=null&&opp.getName().toLowerCase().contains(filterText)
				|| opp.getAccName()!=null&&opp.getAccName().toLowerCase().contains(filterText)
				|| opp.getStageName()!=null&&opp.getStageName().toLowerCase().contains(filterText)
				|| opp.getCloseDate()!=null&&opp.getCloseDate().toString().toLowerCase().contains(filterText);
		if(!containsText) {
			containsText = opp.toString().toLowerCase().contains(filterText);
		}
		return containsText;
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

//	public List<OpportunityDTO> getOpps() {
//		return opps;
//	}

//	public void setOpps(List<OpportunityDTO> opps) {
//		this.opps = opps;
//	}

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

	public BarChartModel getBarModel() {
		return barModel;
	}

	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
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

	public List<String> getStages() {
		return stages;
	}

//	public OpportunityUpdateDTO getUpdateDTO() {
//		return updateDTO;
//	}
//
//	public void setUpdateDTO(OpportunityUpdateDTO updateDTO) {
//		this.updateDTO = updateDTO;
//	}

	public List<Opportunity> getFilterOpps() {
		return filterOpps;
	}

	public void setFilterOpps(List<Opportunity> filterOpps) {
		this.filterOpps = filterOpps;
	}

	public ActivityDTO getActivityDTO() {
		return activityDTO;
	}

	public void setActivityDTO(ActivityDTO activityDTO) {
		this.activityDTO = activityDTO;
	}

}
