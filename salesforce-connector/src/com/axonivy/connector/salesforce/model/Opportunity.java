package com.axonivy.connector.salesforce.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.converters.DateConverter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Opportunity implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("Id")
	private String id;

	@JsonProperty("IsDeleted")
	private Boolean isDeleted;

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

	@JsonProperty("ExpectedRevenue")
	private Double expectedRevenue;

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

	@JsonProperty("IsClosed")
	private Boolean isClosed;

	@JsonProperty("IsWon")
	private Boolean isWon;

	@JsonProperty("ForecastCategory")
	private String forecastCategory;

	@JsonProperty("ForecastCategoryName")
	private String forecastCategoryName;

	@JsonProperty("CampaignId")
	private String campaignId;

	@JsonProperty("HasOpportunityLineItem")
	private Boolean hasOpportunityLineItem;

	@JsonProperty("Pricebook2Id")
	private String pricebook2Id;

	@JsonProperty("OwnerId")
	private String ownerId;

	@JsonProperty("CreatedDate")
	private Date createdDate;

	@JsonProperty("CreatedById")
	private String createdById;

	@JsonProperty("LastModifiedDate")
	private Date lastModifiedDate;

	@JsonProperty("LastModifiedById")
	private String lastModifiedById;

	@JsonProperty("SystemModstamp")
	private Date systemModstamp;

	@JsonProperty("LastActivityDate")
	private Date lastActivityDate;

	@JsonProperty("PushCount")
	private Integer pushCount;

	@JsonProperty("LastStageChangeDate")
	private Date lastStageChangeDate;

	@JsonProperty("FiscalQuarter")
	private Integer fiscalQuarter;

	@JsonProperty("FiscalYear")
	private Integer fiscalYear;

	@JsonProperty("Fiscal")
	private String fiscal;

	@JsonProperty("ContactId")
	private String contactId;

	@JsonProperty("LastViewedDate")
	private Date lastViewedDate;

	@JsonProperty("LastReferencedDate")
	private Date lastReferencedDate;

	@JsonProperty("HasOpenActivity")
	private Boolean hasOpenActivity;

	@JsonProperty("HasOverdueTask")
	private Boolean hasOverdueTask;

	@JsonProperty("LastAmountChangedHistoryId")
	private String lastAmountChangedHistoryId;

	@JsonProperty("LastCloseDateChangedHistoryId")
	private String lastCloseDateChangedHistoryId;

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
	@JsonIgnore
	private String accName;

	final public static ThreadLocal<SimpleDateFormat> dateFormat = new ThreadLocal<SimpleDateFormat>() {
	    @Override
	    protected SimpleDateFormat initialValue() {
	        return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	    }
	};
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

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

	public Double getExpectedRevenue() {
		return expectedRevenue;
	}

	public void setExpectedRevenue(Double expectedRevenue) {
		this.expectedRevenue = expectedRevenue;
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

	public Boolean getIsClosed() {
		return isClosed;
	}

	public void setIsClosed(Boolean isClosed) {
		this.isClosed = isClosed;
	}

	public Boolean getIsWon() {
		return isWon;
	}

	public void setIsWon(Boolean isWon) {
		this.isWon = isWon;
	}

	public String getForecastCategory() {
		return forecastCategory;
	}

	public void setForecastCategory(String forecastCategory) {
		this.forecastCategory = forecastCategory;
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

	public Boolean getHasOpportunityLineItem() {
		return hasOpportunityLineItem;
	}

	public void setHasOpportunityLineItem(Boolean hasOpportunityLineItem) {
		this.hasOpportunityLineItem = hasOpportunityLineItem;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedById() {
		return createdById;
	}

	public void setCreatedById(String createdById) {
		this.createdById = createdById;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getLastModifiedById() {
		return lastModifiedById;
	}

	public void setLastModifiedById(String lastModifiedById) {
		this.lastModifiedById = lastModifiedById;
	}

	public Date getSystemModstamp() {
		return systemModstamp;
	}

	public void setSystemModstamp(Date systemModstamp) {
		this.systemModstamp = systemModstamp;
	}

	public Date getLastActivityDate() {
		return lastActivityDate;
	}

	public void setLastActivityDate(Date lastActivityDate) {
		this.lastActivityDate = lastActivityDate;
	}

	public Integer getPushCount() {
		return pushCount;
	}

	public void setPushCount(Integer pushCount) {
		this.pushCount = pushCount;
	}

	public Date getLastStageChangeDate() {
		return lastStageChangeDate;
	}

	public void setLastStageChangeDate(Date lastStageChangeDate) {
		this.lastStageChangeDate = lastStageChangeDate;
	}

	public Integer getFiscalQuarter() {
		return fiscalQuarter;
	}

	public void setFiscalQuarter(Integer fiscalQuarter) {
		this.fiscalQuarter = fiscalQuarter;
	}

	public Integer getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(Integer fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	public String getFiscal() {
		return fiscal;
	}

	public void setFiscal(String fiscal) {
		this.fiscal = fiscal;
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public Date getLastViewedDate() {
		return lastViewedDate;
	}

	public void setLastViewedDate(Date lastViewedDate) {
		this.lastViewedDate = lastViewedDate;
	}

	public Date getLastReferencedDate() {
		return lastReferencedDate;
	}

	public void setLastReferencedDate(Date lastReferencedDate) {
		this.lastReferencedDate = lastReferencedDate;
	}

	public Boolean getHasOpenActivity() {
		return hasOpenActivity;
	}

	public void setHasOpenActivity(Boolean hasOpenActivity) {
		this.hasOpenActivity = hasOpenActivity;
	}

	public Boolean getHasOverdueTask() {
		return hasOverdueTask;
	}

	public void setHasOverdueTask(Boolean hasOverdueTask) {
		this.hasOverdueTask = hasOverdueTask;
	}

	public String getLastAmountChangedHistoryId() {
		return lastAmountChangedHistoryId;
	}

	public void setLastAmountChangedHistoryId(String lastAmountChangedHistoryId) {
		this.lastAmountChangedHistoryId = lastAmountChangedHistoryId;
	}

	public String getLastCloseDateChangedHistoryId() {
		return lastCloseDateChangedHistoryId;
	}

	public void setLastCloseDateChangedHistoryId(String lastCloseDateChangedHistoryId) {
		this.lastCloseDateChangedHistoryId = lastCloseDateChangedHistoryId;
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

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}
	
	public String date2Str(Date d) {
		
		return d==null?"":dateFormat.get().format(d);
	}

	@Override
	public String toString() {
		return "Opportunity [id=" + id + ", isDeleted=" + isDeleted + ", accountId=" + accountId + ", isPrivate="
				+ isPrivate + ", name=" + name + ", description=" + description + ", stageName=" + stageName
				+ ", amount=" + amount + ", probability=" + probability + ", expectedRevenue=" + expectedRevenue
				+ ", totalOpportunityQuantity=" + totalOpportunityQuantity + ", closeDate=" + date2Str(closeDate) + ", type="
				+ type + ", nextStep=" + nextStep + ", leadSource=" + leadSource + ", isClosed=" + isClosed + ", isWon="
				+ isWon + ", forecastCategory=" + forecastCategory + ", forecastCategoryName=" + forecastCategoryName
				+ ", campaignId=" + campaignId + ", hasOpportunityLineItem=" + hasOpportunityLineItem
				+ ", pricebook2Id=" + pricebook2Id + ", ownerId=" + ownerId + ", createdDate=" + date2Str(createdDate)
				+ ", createdById=" + createdById + ", lastModifiedDate=" + date2Str(lastModifiedDate) + ", lastModifiedById="
				+ lastModifiedById + ", systemModstamp=" + date2Str(systemModstamp) + ", lastActivityDate=" + date2Str(lastActivityDate)
				+ ", pushCount=" + pushCount + ", lastStageChangeDate=" + date2Str(lastStageChangeDate) + ", fiscalQuarter="
				+ fiscalQuarter + ", fiscalYear=" + fiscalYear + ", fiscal=" + fiscal + ", contactId=" + contactId
				+ ", lastViewedDate=" + date2Str(lastViewedDate) + ", lastReferencedDate=" + date2Str(lastReferencedDate)
				+ ", hasOpenActivity=" + hasOpenActivity + ", hasOverdueTask=" + hasOverdueTask
				+ ", lastAmountChangedHistoryId=" + lastAmountChangedHistoryId + ", lastCloseDateChangedHistoryId="
				+ lastCloseDateChangedHistoryId + ", deliveryInstallationStatus__c=" + deliveryInstallationStatus__c
				+ ", trackingNumber__c=" + trackingNumber__c + ", orderNumber__c=" + orderNumber__c
				+ ", currentGenerators__c=" + currentGenerators__c + ", mainCompetitors__c=" + mainCompetitors__c
				+ ", accName=" + accName + "]";
	}
}
