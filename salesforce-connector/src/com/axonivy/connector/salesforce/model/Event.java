package com.axonivy.connector.salesforce.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("Id")
	private String id;

	@JsonProperty("WhatId")
	private String whatId;

	@JsonProperty("Subject")
	private String subject;

	@JsonProperty("Location")
	private String location;

	@JsonProperty("IsAllDayEvent")
	private Boolean isAllDayEvent;

	@JsonProperty("ActivityDate")
	private Date activityDate;

	@JsonProperty("DurationInMinutes")
	private Integer durationInMinutes;

	@JsonProperty("StartDateTime")
	private Date startDateTime;

	@JsonProperty("EndDateTime")
	private Date endDateTime;

	@JsonProperty("Description")
	private String description;

	@JsonProperty("AccountId")
	private String accountId;

	@JsonProperty("OwnerId")
	private String ownerId;

	@JsonProperty("IsPrivate")
	private Boolean isPrivate;

	@JsonProperty("ShowAs")
	private String showAs;

	@JsonProperty("IsDeleted")
	private Boolean isDeleted;

	@JsonProperty("IsChild")
	private Boolean isChild;

	@JsonProperty("IsGroupEvent")
	private Boolean isGroupEvent;

	@JsonProperty("IsArchived")
	private Boolean isArchived;

	@JsonProperty("IsRecurrence")
	private Boolean isRecurrence;

	@JsonProperty("EventSubtype")
	private String eventSubtype;

	@JsonProperty("IsReminderSet")
	private Boolean isReminderSet;

	@JsonProperty("IsRecurrence2Exclusion")
	private Boolean isRecurrence2Exclusion;

	@JsonProperty("IsRecurrence2")
	private Boolean isRecurrence2;

	@JsonProperty("IsRecurrence2Exception")
	private Boolean isRecurrence2Exception;

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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Boolean getIsAllDayEvent() {
		return isAllDayEvent;
	}

	public void setIsAllDayEvent(Boolean isAllDayEvent) {
		this.isAllDayEvent = isAllDayEvent;
	}

	public Date getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

	public Integer getDurationInMinutes() {
		return durationInMinutes;
	}

	public void setDurationInMinutes(Integer durationInMinutes) {
		this.durationInMinutes = durationInMinutes;
	}

	public Date getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	public Date getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public Boolean getIsPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(Boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public String getShowAs() {
		return showAs;
	}

	public void setShowAs(String showAs) {
		this.showAs = showAs;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Boolean getIsChild() {
		return isChild;
	}

	public void setIsChild(Boolean isChild) {
		this.isChild = isChild;
	}

	public Boolean getIsGroupEvent() {
		return isGroupEvent;
	}

	public void setIsGroupEvent(Boolean isGroupEvent) {
		this.isGroupEvent = isGroupEvent;
	}

	public Boolean getIsArchived() {
		return isArchived;
	}

	public void setIsArchived(Boolean isArchived) {
		this.isArchived = isArchived;
	}

	public Boolean getIsRecurrence() {
		return isRecurrence;
	}

	public void setIsRecurrence(Boolean isRecurrence) {
		this.isRecurrence = isRecurrence;
	}

	public String getEventSubtype() {
		return eventSubtype;
	}

	public void setEventSubtype(String eventSubtype) {
		this.eventSubtype = eventSubtype;
	}

	public Boolean getIsReminderSet() {
		return isReminderSet;
	}

	public void setIsReminderSet(Boolean isReminderSet) {
		this.isReminderSet = isReminderSet;
	}

	public Boolean getIsRecurrence2Exclusion() {
		return isRecurrence2Exclusion;
	}

	public void setIsRecurrence2Exclusion(Boolean isRecurrence2Exclusion) {
		this.isRecurrence2Exclusion = isRecurrence2Exclusion;
	}

	public Boolean getIsRecurrence2() {
		return isRecurrence2;
	}

	public void setIsRecurrence2(Boolean isRecurrence2) {
		this.isRecurrence2 = isRecurrence2;
	}

	public Boolean getIsRecurrence2Exception() {
		return isRecurrence2Exception;
	}

	public void setIsRecurrence2Exception(Boolean isRecurrence2Exception) {
		this.isRecurrence2Exception = isRecurrence2Exception;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
