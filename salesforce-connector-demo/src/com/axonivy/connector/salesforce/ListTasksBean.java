package com.axonivy.connector.salesforce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.util.LangUtils;

import com.axonivy.connector.salesforce.dto.ActivityDTO;
import com.axonivy.connector.salesforce.enums.Stage;
import com.axonivy.connector.salesforce.model.Account;
import com.axonivy.connector.salesforce.model.Opportunity;
import com.axonivy.connector.salesforce.model.Task;
import com.axonivy.connector.salesforce.utils.ConvertUtils;

import ch.ivyteam.ivy.process.call.SubProcessCall;

public class ListTasksBean {
	private List<Task> tasks;
	private List<Task> filterTasks;
	private Task selectedTask;
	private String accountName;
	private BarChartModel barModel;
	private String ownerId;
	private List<Opportunity> opportunities;
	private Opportunity selectedOpp;
	private List<Account> accs;
	private List<String> stages;
	private ActivityDTO activityDTO;
	private String whatOpportunity;

	public ListTasksBean() {
		ownerId = ConvertUtils.extractOwnerId();

		getAllTasks();
		getAllAccounts();
		getListStages();

	}
	public void updateTask(String id) {
		openTaskDetail(id);
	}

	public void openTaskDetail(String id) {
		selectedTask = SubProcessCall.withPath("Functional Processes/getTask")
				.withStartSignature("getTask(String)").withParam("id", id).call().get("task", Task.class);
		accountName = Utils.getAccName(selectedTask.getAccountId());
		whatOpportunity= Utils.getOppName(selectedTask.getWhatId());
	}


	public void getAllOpps() {
		opportunities = Utils.getAllOpps();
	}
	public void getAllTasks() {
		getAllOpps();
		tasks = Utils.getAllTasks();
		for(Task t : tasks) {
			t.setAccName(getOpportunityNameById(t.getWhatId()));
		}
	}

	@SuppressWarnings("unchecked")
	public void getAllAccounts() {
		accs = (List<Account>) SubProcessCall.withPath("Functional Processes/getAccounts")
				.withStartSignature("getAllAccounts()").call().get("accs", Account.class);
	}

	public void addNewTask() {
		accountName = null;
		setSelectedTask(new Task());
		getSelectedTask().setOwnerId(ownerId);
		getAllAccounts();
		getListStages();
	}

	public void beforeDelete(String id) {
		setSelectedTask(SubProcessCall.withPath("Functional Processes/getTask")
				.withStartSignature("getTask(String)").withParam("id", id).call().get("task", Task.class));
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

	public void reset() {
		setSelectedTask(new Task());
		accountName = null;
		whatOpportunity=null;
		getAllTasks();
		getAllAccounts();

	}

	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
		if (LangUtils.isBlank(filterText)) {
			return true;
		}

		Task task = (Task) value;
		boolean containsText = task.getAccountId()!=null&&task.getAccountId().toLowerCase().contains(filterText)
				|| task.getDescription()!=null&&task.getDescription().toLowerCase().contains(filterText)
				|| task.getActivityDate()!=null&&task.getActivityDate().toInstant().toString().contains(filterText)
				|| task.getSubject()!=null&&task.getSubject().toLowerCase().contains(filterText)
				|| task.getTaskSubtype()!=null&&task.getTaskSubtype().toString().toLowerCase().contains(filterText);
		if(!containsText) {
			containsText = task.toString().toLowerCase().contains(filterText);
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
//
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

	public List<Task> getFilterTasks() {
		return filterTasks;
	}

	public void setFilterTasks(List<Task> filterTasks) {
		this.filterTasks = filterTasks;
	}

	public ActivityDTO getActivityDTO() {
		return activityDTO;
	}

	public void setActivityDTO(ActivityDTO activityDTO) {
		this.activityDTO = activityDTO;
	}

	public Task getSelectedTask() {
		return selectedTask;
	}

	public void setSelectedTask(Task selectedTask) {
		this.selectedTask = selectedTask;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public String getWhatOpportunity() {
		return whatOpportunity;
	}

	public void setWhatOpportunity(String whatOpportunity) {
		this.whatOpportunity = whatOpportunity;
	}

}
