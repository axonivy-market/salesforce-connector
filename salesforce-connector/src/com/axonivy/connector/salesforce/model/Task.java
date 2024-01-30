package com.axonivy.connector.salesforce.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Task implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("Id")
	private String id;

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

	@JsonProperty("AccountId")
	private String accountId;

	@JsonProperty("Description")
	private String description;

	@JsonProperty("IsDeleted")
	private Boolean isDeleted;

	@JsonProperty("IsClosed")
	private Boolean isClosed;

	@JsonProperty("IsArchived")
	private Boolean isArchived;

	@JsonProperty("TaskSubtype")
	private String taskSubtype;

	@JsonProperty("CompletedDateTime")
	private Date completedDateTime;

	@JsonProperty("IsReminderSet")
	private Boolean isReminderSet;

	@JsonProperty("IsRecurrence")
	private Boolean isRecurrence;

	@JsonProperty("Name")
	private String name;

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

	public Boolean isHighPriority() {
		return isHighPriority;
	}

	public void setHighPriority(Boolean isHighPriority) {
		this.isHighPriority = isHighPriority;
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

	public Boolean isClosed() {
		return isClosed;
	}

	public void setClosed(Boolean isClosed) {
		this.isClosed = isClosed;
	}

	public Boolean isArchived() {
		return isArchived;
	}

	public void setArchived(Boolean isArchived) {
		this.isArchived = isArchived;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
