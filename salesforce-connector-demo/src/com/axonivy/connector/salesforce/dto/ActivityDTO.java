package com.axonivy.connector.salesforce.dto;

import java.util.List;

import com.axonivy.connector.salesforce.model.Event;
import com.axonivy.connector.salesforce.model.Task;

public class ActivityDTO {

	private List<Task> tasks;
	private List<Event> events;

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

}
