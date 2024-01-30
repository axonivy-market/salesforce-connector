package com.axonivy.connector.salesforce.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpportunityUpdateDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("AccountId")
	private String accountId;

	@JsonProperty("IsPrivate")
	private Boolean isPrivate;

	@JsonProperty("Name")
	private String name;

	@JsonProperty("Description")
	private String description;

	@JsonProperty("StageName")
	private String stageName;

	@JsonProperty("Amount")
	private Double amount;

	@JsonProperty("Probability")
	private Double probability;

	@JsonProperty("TotalOpportunityQuantity")
	private Long totalOpportunityQuantity;

	@JsonProperty("CloseDate")
	private Date closeDate;

	@JsonProperty("Type")
	private String type;

	@JsonProperty("NextStep")
	private String nextStep;

	@JsonProperty("LeadSource")
	private String leadSource;

	@JsonProperty("ForecastCategoryName")
	private String forecastCategoryName;

	@JsonProperty("CampaignId")
	private String campaignId;

	@JsonProperty("Pricebook2Id")
	private String pricebook2Id;

	@JsonProperty("OwnerId")
	private String ownerId;

	@JsonProperty("ContactId")
	private String contactId;

	@JsonProperty("DeliveryInstallationStatus__c")
	private String deliveryInstallationStatus__c;

	@JsonProperty("TrackingNumber__c")
	private String trackingNumber__c;

	@JsonProperty("OrderNumber__c")
	private String orderNumber__c;

	@JsonProperty("CurrentGenerators__c")
	private String currentGenerators__c;

	@JsonProperty("MainCompetitors__c")
	private String mainCompetitors__c;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public Boolean getIsPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(Boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getProbability() {
		return probability;
	}

	public void setProbability(Double probability) {
		this.probability = probability;
	}

	public Long getTotalOpportunityQuantity() {
		return totalOpportunityQuantity;
	}

	public void setTotalOpportunityQuantity(Long totalOpportunityQuantity) {
		this.totalOpportunityQuantity = totalOpportunityQuantity;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNextStep() {
		return nextStep;
	}

	public void setNextStep(String nextStep) {
		this.nextStep = nextStep;
	}

	public String getLeadSource() {
		return leadSource;
	}

	public void setLeadSource(String leadSource) {
		this.leadSource = leadSource;
	}

	public String getForecastCategoryName() {
		return forecastCategoryName;
	}

	public void setForecastCategoryName(String forecastCategoryName) {
		this.forecastCategoryName = forecastCategoryName;
	}

	public String getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
	}

	public String getPricebook2Id() {
		return pricebook2Id;
	}

	public void setPricebook2Id(String pricebook2Id) {
		this.pricebook2Id = pricebook2Id;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public String getDeliveryInstallationStatus__c() {
		return deliveryInstallationStatus__c;
	}

	public void setDeliveryInstallationStatus__c(String deliveryInstallationStatus__c) {
		this.deliveryInstallationStatus__c = deliveryInstallationStatus__c;
	}

	public String getTrackingNumber__c() {
		return trackingNumber__c;
	}

	public void setTrackingNumber__c(String trackingNumber__c) {
		this.trackingNumber__c = trackingNumber__c;
	}

	public String getOrderNumber__c() {
		return orderNumber__c;
	}

	public void setOrderNumber__c(String orderNumber__c) {
		this.orderNumber__c = orderNumber__c;
	}

	public String getCurrentGenerators__c() {
		return currentGenerators__c;
	}

	public void setCurrentGenerators__c(String currentGenerators__c) {
		this.currentGenerators__c = currentGenerators__c;
	}

	public String getMainCompetitors__c() {
		return mainCompetitors__c;
	}

	public void setMainCompetitors__c(String mainCompetitors__c) {
		this.mainCompetitors__c = mainCompetitors__c;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
