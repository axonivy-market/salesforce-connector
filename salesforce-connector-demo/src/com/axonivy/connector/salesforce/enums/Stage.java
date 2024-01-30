package com.axonivy.connector.salesforce.enums;

public enum Stage {
	PROSPECTING("Prospecting"),
	QUALIFICATION("Qualification"),
	NEEDS_ANALYSIS("Needs Analysis"),
	VALUE_PROPOSITION("Value Proposition"),
	DECISION_MAKERS("Id. Decision Makers"),
	PERCEPTION_ANALYSIS("Perception Analysis"),
	PROPOSAL_PRICE_QUOTE("Proposal/Price Quote"),
	NEGOTIATION_REVIEW("Negotiation/Review"),
	CLOSED_WON("Closed Won"),
	CLOSED_LOST("Closed Lost");

	public final String label;

	Stage(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
