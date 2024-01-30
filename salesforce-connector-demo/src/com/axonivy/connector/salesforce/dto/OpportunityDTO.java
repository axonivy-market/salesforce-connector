package com.axonivy.connector.salesforce.dto;

import java.util.Date;

public class OpportunityDTO {
	private String id;
	private String oppName;
	private String accName;
	private Double amount;
	private Date closeDate;
	private String stage;

	public OpportunityDTO(String id, String oppName, String accName, Double amount, Date closeDate, String stage) {
		super();
		this.id = id;
		this.oppName = oppName;
		this.accName = accName;
		this.amount = amount;
		this.closeDate = closeDate;
		this.stage = stage;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOppName() {
		return oppName;
	}

	public void setOppName(String oppName) {
		this.oppName = oppName;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

}
