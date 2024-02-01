package com.axonivy.connector.salesforce;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.animation.Animation;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;

import com.axonivy.connector.salesforce.model.Opportunity;

import com.axonivy.connector.salesforce.dto.OpportunityDTO;
import com.axonivy.connector.salesforce.enums.Stage;

public class ChartBean {
	List<Opportunity> opportunities;
	List<OpportunityDTO> opps;
	private BarChartModel barModel;

	public ChartBean() {
		opps = new ArrayList<>();
		opportunities = Utils.getAllOpps();

		opps = Utils.convertToOppDTO(opportunities);

		createBarChartModel();
	}

	public void createBarChartModel() {
		barModel = new BarChartModel();
		ChartData data = new ChartData();

		BarChartDataSet dataSet = new BarChartDataSet();
		dataSet.setLabel("Opportunities");

		List<Number> values = new ArrayList<>();
		values.add(countByStage(Stage.PROSPECTING.label));
		values.add(countByStage(Stage.QUALIFICATION.label));
		values.add(countByStage(Stage.NEEDS_ANALYSIS.label));
		values.add(countByStage(Stage.VALUE_PROPOSITION.label));
		values.add(countByStage(Stage.DECISION_MAKERS.label));
		values.add(countByStage(Stage.PERCEPTION_ANALYSIS.label));
		values.add(countByStage(Stage.PROPOSAL_PRICE_QUOTE.label));
		values.add(countByStage(Stage.NEGOTIATION_REVIEW.label));
		values.add(countByStage(Stage.CLOSED_WON.label));
		values.add(countByStage(Stage.CLOSED_LOST.label));
		dataSet.setData(values);

		List<String> bgColors = new ArrayList<>();
		bgColors.add("rgb(255, 99, 132)");
		bgColors.add("rgb(75, 192, 192)");
		bgColors.add("rgb(255, 205, 86)");
		bgColors.add("rgb(201, 203, 207)");
		bgColors.add("rgb(54, 162, 235)");

		bgColors.add("rgb(153, 102, 255)");
		bgColors.add("rgb(153, 102, 51)");
		bgColors.add("rgb(255, 51, 204)");
		bgColors.add("rgb(204, 153, 0)");
		bgColors.add("rgb(51, 153, 51)");
		dataSet.setBackgroundColor(bgColors);

		data.addChartDataSet(dataSet);
		List<String> labels = new ArrayList<>();
		labels.add(Stage.PROSPECTING.label);
		labels.add(Stage.QUALIFICATION.label);
		labels.add(Stage.NEEDS_ANALYSIS.label);
		labels.add(Stage.VALUE_PROPOSITION.label);
		labels.add(Stage.DECISION_MAKERS.label);
		labels.add(Stage.PERCEPTION_ANALYSIS.label);
		labels.add(Stage.PROPOSAL_PRICE_QUOTE.label);
		labels.add(Stage.NEGOTIATION_REVIEW.label);
		labels.add(Stage.CLOSED_WON.label);
		labels.add(Stage.CLOSED_LOST.label);
		data.setLabels(labels);

		barModel.setData(data);

		// Options
		BarChartOptions options = new BarChartOptions();
		CartesianScales cScales = new CartesianScales();
		CartesianLinearAxes linearAxes = new CartesianLinearAxes();
		linearAxes.setOffset(true);
		CartesianLinearTicks ticks = new CartesianLinearTicks();
		linearAxes.setTicks(ticks);
		cScales.addYAxesData(linearAxes);
		options.setScales(cScales);

		Title title = new Title();
		title.setDisplay(true);
		title.setText("Opportunities");
		options.setTitle(title);

		Legend legend = new Legend();
		legend.setDisplay(false);
		legend.setPosition("top");
		LegendLabel legendLabels = new LegendLabel();
		legendLabels.setFontStyle("italic");
		legendLabels.setFontColor("#2980B9");
		legendLabels.setFontSize(24);
		legend.setLabels(legendLabels);
		options.setLegend(legend);

		// disable animation
		Animation animation = new Animation();
		animation.setDuration(0);
		options.setAnimation(animation);

		barModel.setOptions(options);
	}

	private int countByStage(String stageName) {
		return opps.stream().filter(o -> o.getStage().equals(stageName)).collect(Collectors.toList()).size();
	}

	public List<Opportunity> getOpportunities() {
		return opportunities;
	}

	public void setOpportunities(List<Opportunity> opportunities) {
		this.opportunities = opportunities;
	}

	public List<OpportunityDTO> getOpps() {
		return opps;
	}

	public void setOpps(List<OpportunityDTO> opps) {
		this.opps = opps;
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}

}
