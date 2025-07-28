package com.axonivy.connector.salesforce.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Task implements Serializable {

	private static final long serialVersionUID = 1L;

	    @JsonProperty("Id") 
	    private String id;
	    @JsonProperty("WhoId") 
	    private String whoId;
	    @JsonProperty("WhatId") 
	    private String whatId;
	    @JsonProperty("Subject") 
	    private String subject;
	    @JsonProperty("ActivityDate") 
	    private Date activityDate;
	    @JsonProperty("Status") 
	    private String status;
	    @JsonProperty("Priority") 
	    private String priority;
	    @JsonProperty("IsHighPriority") 
	    private Boolean isHighPriority;
	    @JsonProperty("OwnerId") 
	    private String ownerId;
	    @JsonProperty("Description") 
	    private String description;
	    @JsonProperty("IsDeleted") 
	    private Boolean isDeleted;
	    @JsonProperty("AccountId") 
	    private String accountId;
	    @JsonProperty("IsClosed") 
	    private Boolean isClosed;
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
	    @JsonProperty("IsArchived") 
	    private Boolean isArchived;
	    @JsonProperty("CallDurationInSeconds") 
	    private int callDurationInSeconds;
	    @JsonProperty("CallType") 
	    private String callType;
	    @JsonProperty("CallDisposition") 
	    private String callDisposition;
	    @JsonProperty("CallObject") 
	    private String callObject;
	    @JsonProperty("ReminderDateTime") 
	    private Date reminderDateTime;
	    @JsonProperty("IsReminderSet") 
	    private Boolean isReminderSet;
	    @JsonProperty("RecurrenceActivityId") 
	    private String recurrenceActivityId;
	    @JsonProperty("IsRecurrence") 
	    private Boolean isRecurrence;
	    @JsonProperty("RecurrenceStartDateOnly") 
	    private Boolean recurrenceStartDateOnly;
	    @JsonProperty("RecurrenceEndDateOnly") 
	    private Boolean recurrenceEndDateOnly;
	    @JsonProperty("RecurrenceTimeZoneSidKey") 
	    private String recurrenceTimeZoneSidKey;
	    @JsonProperty("RecurrenceType") 
	    private String recurrenceType;
	    @JsonProperty("RecurrenceInterval") 
	    private String recurrenceInterval;
	    @JsonProperty("RecurrenceDayOfWeekMask") 
	    private String recurrenceDayOfWeekMask;
	    @JsonProperty("RecurrenceDayOfMonth") 
	    private String recurrenceDayOfMonth;
	    @JsonProperty("RecurrenceInstance") 
	    private String recurrenceInstance;
	    @JsonProperty("RecurrenceMonthOfYear") 
	    private String recurrenceMonthOfYear;
	    @JsonProperty("RecurrenceRegeneratedType") 
	    private String recurrenceRegeneratedType;
	    @JsonProperty("TaskSubtype") 
	    private String taskSubtype;
	    @JsonProperty("CompletedDateTime") 
	    private Date completedDateTime;
	    @JsonIgnore
	    private String accName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWhatId() {
		return whatId;
	}

	public void setWhatId(String whatId) {
		this.whatId = whatId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getTaskSubtype() {
		return taskSubtype;
	}

	public void setTaskSubtype(String taskSubtype) {
		this.taskSubtype = taskSubtype;
	}

	public Date getCompletedDateTime() {
		return completedDateTime;
	}

	public void setCompletedDateTime(Date completedDateTime) {
		this.completedDateTime = completedDateTime;
	}

	public Boolean getIsHighPriority() {
		return isHighPriority;
	}

	public void setIsHighPriority(Boolean isHighPriority) {
		this.isHighPriority = isHighPriority;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Boolean getIsClosed() {
		return isClosed;
	}

	public void setIsClosed(Boolean isClosed) {
		this.isClosed = isClosed;
	}

	public Boolean getIsArchived() {
		return isArchived;
	}

	public void setIsArchived(Boolean isArchived) {
		this.isArchived = isArchived;
	}

	public Boolean getIsReminderSet() {
		return isReminderSet;
	}

	public void setIsReminderSet(Boolean isReminderSet) {
		this.isReminderSet = isReminderSet;
	}

	public Boolean getIsRecurrence() {
		return isRecurrence;
	}

	public void setIsRecurrence(Boolean isRecurrence) {
		this.isRecurrence = isRecurrence;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accountName) {
		this.accName = accountName;
	}

	public String getWhoId() {
		return whoId;
	}

	public void setWhoId(String whoId) {
		this.whoId = whoId;
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

	public int getCallDurationInSeconds() {
		return callDurationInSeconds;
	}

	public void setCallDurationInSeconds(int callDurationInSeconds) {
		this.callDurationInSeconds = callDurationInSeconds;
	}

	public String getCallType() {
		return callType;
	}

	public void setCallType(String callType) {
		this.callType = callType;
	}

	public String getCallDisposition() {
		return callDisposition;
	}

	public void setCallDisposition(String callDisposition) {
		this.callDisposition = callDisposition;
	}

	public String getCallObject() {
		return callObject;
	}

	public void setCallObject(String callObject) {
		this.callObject = callObject;
	}

	public Date getReminderDateTime() {
		return reminderDateTime;
	}

	public void setReminderDateTime(Date reminderDateTime) {
		this.reminderDateTime = reminderDateTime;
	}

	public String getRecurrenceActivityId() {
		return recurrenceActivityId;
	}

	public void setRecurrenceActivityId(String recurrenceActivityId) {
		this.recurrenceActivityId = recurrenceActivityId;
	}

	public Boolean getRecurrenceStartDateOnly() {
		return recurrenceStartDateOnly;
	}

	public void setRecurrenceStartDateOnly(Boolean recurrenceStartDateOnly) {
		this.recurrenceStartDateOnly = recurrenceStartDateOnly;
	}

	public Boolean getRecurrenceEndDateOnly() {
		return recurrenceEndDateOnly;
	}

	public void setRecurrenceEndDateOnly(Boolean recurrenceEndDateOnly) {
		this.recurrenceEndDateOnly = recurrenceEndDateOnly;
	}

	public String getRecurrenceTimeZoneSidKey() {
		return recurrenceTimeZoneSidKey;
	}

	public void setRecurrenceTimeZoneSidKey(String recurrenceTimeZoneSidKey) {
		this.recurrenceTimeZoneSidKey = recurrenceTimeZoneSidKey;
	}

	public String getRecurrenceType() {
		return recurrenceType;
	}

	public void setRecurrenceType(String recurrenceType) {
		this.recurrenceType = recurrenceType;
	}

	public String getRecurrenceInterval() {
		return recurrenceInterval;
	}

	public void setRecurrenceInterval(String recurrenceInterval) {
		this.recurrenceInterval = recurrenceInterval;
	}

	public String getRecurrenceDayOfWeekMask() {
		return recurrenceDayOfWeekMask;
	}

	public void setRecurrenceDayOfWeekMask(String recurrenceDayOfWeekMask) {
		this.recurrenceDayOfWeekMask = recurrenceDayOfWeekMask;
	}

	public String getRecurrenceDayOfMonth() {
		return recurrenceDayOfMonth;
	}

	public void setRecurrenceDayOfMonth(String recurrenceDayOfMonth) {
		this.recurrenceDayOfMonth = recurrenceDayOfMonth;
	}

	public String getRecurrenceInstance() {
		return recurrenceInstance;
	}

	public void setRecurrenceInstance(String recurrenceInstance) {
		this.recurrenceInstance = recurrenceInstance;
	}

	public String getRecurrenceMonthOfYear() {
		return recurrenceMonthOfYear;
	}

	public void setRecurrenceMonthOfYear(String recurrenceMonthOfYear) {
		this.recurrenceMonthOfYear = recurrenceMonthOfYear;
	}

	public String getRecurrenceRegeneratedType() {
		return recurrenceRegeneratedType;
	}

	public void setRecurrenceRegeneratedType(String recurrenceRegeneratedType) {
		this.recurrenceRegeneratedType = recurrenceRegeneratedType;
	}

	public String date2Str(Date d) {
		return d==null?"":Opportunity.dateFormat.get().format(d);
	}
	
	@Override
	public String toString() {
		return "Task [id=" + id + ", whoId=" + whoId + ", whatId=" + whatId + ", subject=" + subject + ", activityDate="
				+ date2Str(activityDate) + ", status=" + status + ", priority=" + priority + ", isHighPriority=" + isHighPriority
				+ ", ownerId=" + ownerId + ", description=" + description + ", isDeleted=" + isDeleted + ", accountId="
				+ accountId + ", isClosed=" + isClosed + ", createdDate=" + date2Str(createdDate) + ", createdById=" + createdById
				+ ", lastModifiedDate=" + date2Str(lastModifiedDate) + ", lastModifiedById=" + lastModifiedById
				+ ", systemModstamp=" + systemModstamp + ", isArchived=" + isArchived + ", callDurationInSeconds="
				+ callDurationInSeconds + ", callType=" + callType + ", callDisposition=" + callDisposition
				+ ", callObject=" + callObject + ", reminderDateTime=" +date2Str( reminderDateTime )+ ", isReminderSet="
				+ isReminderSet + ", recurrenceActivityId=" + recurrenceActivityId + ", isRecurrence=" + isRecurrence
				+ ", recurrenceStartDateOnly=" + recurrenceStartDateOnly + ", recurrenceEndDateOnly="
				+ recurrenceEndDateOnly + ", recurrenceTimeZoneSidKey=" + recurrenceTimeZoneSidKey + ", recurrenceType="
				+ recurrenceType + ", recurrenceInterval=" + recurrenceInterval + ", recurrenceDayOfWeekMask="
				+ recurrenceDayOfWeekMask + ", recurrenceDayOfMonth=" + recurrenceDayOfMonth + ", recurrenceInstance="
				+ recurrenceInstance + ", recurrenceMonthOfYear=" + recurrenceMonthOfYear
				+ ", recurrenceRegeneratedType=" + recurrenceRegeneratedType + ", taskSubtype=" + taskSubtype
				+ ", completedDateTime=" + date2Str(completedDateTime) + ", accName=" + accName + "]";
	}


}
