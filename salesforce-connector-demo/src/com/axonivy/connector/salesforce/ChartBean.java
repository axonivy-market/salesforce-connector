package com.axonivy.connector.salesforce;

import java.util.Arrays;
import java.util.List;

import software.xdev.chartjs.model.charts.BarChart;
import software.xdev.chartjs.model.data.BarData;
import software.xdev.chartjs.model.dataset.BarDataset;
import software.xdev.chartjs.model.options.BarOptions;
import software.xdev.chartjs.model.options.LegendOptions;
import software.xdev.chartjs.model.options.Plugins;
import software.xdev.chartjs.model.options.Title;
import software.xdev.chartjs.model.options.animation.DefaultAnimation;
import software.xdev.chartjs.model.options.scale.Scales;
import software.xdev.chartjs.model.options.scale.cartesian.linear.LinearScaleOptions;

import com.axonivy.connector.salesforce.dto.OpportunityDTO;
import com.axonivy.connector.salesforce.enums.Stage;
import com.axonivy.connector.salesforce.model.Opportunity;

public class ChartBean {

	// Index-aligned with Stage.values()
	private static final List<Object> STAGE_COLORS = List.of(
			"rgb(255, 99, 132)", "rgb(75, 192, 192)", "rgb(255, 205, 86)", "rgb(201, 203, 207)",
			"rgb(54, 162, 235)", "rgb(153, 102, 255)", "rgb(153, 102, 51)", "rgb(255, 51, 204)",
			"rgb(204, 153, 0)", "rgb(51, 153, 51)");

	private List<Opportunity> opportunities;
	private List<OpportunityDTO> opps;
	private String barChartJson;

	public ChartBean() {
		opportunities = Utils.getAllOpps();
		opps = Utils.convertToOppDTO(opportunities);
		barChartJson = createBarChartJson();
	}

	// PrimeFaces 15 dropped the typed chart models -> p:chart consumes a raw Chart.js config string
	private String createBarChartJson() {
		List<String> labels = Arrays.stream(Stage.values()).map(Stage::getLabel).toList();
		List<Number> values = labels.stream().<Number>map(this::countByStage).toList();

		BarDataset dataSet = new BarDataset()
				.setLabel("Opportunities")
				.setBackgroundColor(STAGE_COLORS);
		dataSet.setData(values);

		BarData data = new BarData().addDataset(dataSet).setLabels(labels);

		Plugins plugins = new Plugins()
				.setTitle(new Title().setDisplay(true).setText("Opportunities"))
				.setLegend(new LegendOptions().setDisplay(false));

		// Chart.js 4 only honours the container height when the aspect ratio is not kept
		BarOptions options = new BarOptions()
				.setResponsive(true)
				.setMaintainAspectRatio(false)
				.setAnimation(new DefaultAnimation().setDuration(0))
				.setPlugins(plugins)
				.setScales(new Scales().addScale(Scales.ScaleAxis.Y, new LinearScaleOptions().setOffset(true)));

		return new BarChart(data, options).toJson();
	}

	private int countByStage(String stageName) {
		return (int) opps.stream().filter(o -> stageName.equals(o.getStage())).count();
	}

	public String getBarChartJson() {
		return barChartJson;
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

}
