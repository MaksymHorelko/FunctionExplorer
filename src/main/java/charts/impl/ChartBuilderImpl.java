package charts.impl;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import charts.ChartBuilder;
import function.AppFunction;

public class ChartBuilderImpl implements ChartBuilder {
	private final XYSeriesCollection dataset = new XYSeriesCollection();

	@Override
	public void addFunction(List<Double> xValues, AppFunction function, String functionName) {
		if (xValues == null || xValues.isEmpty())
			throw new IllegalArgumentException("Ivalid input for xValues");
		if (function == null)
			throw new IllegalArgumentException("Invalid input for function");
		if (functionName == null || functionName.isEmpty())
			throw new IllegalArgumentException("Ivalid input for functionName");

		XYSeries series = new XYSeries(functionName);
		for (double x : xValues) {
			series.add(x, function.calculate(x));
		}
		dataset.addSeries(series);
	}

	@Override
	public void displayChart(String chartTitle, String xAxisLabel, String yAxisLabel) {
		if (chartTitle == null || chartTitle.isEmpty())
			throw new IllegalArgumentException("Invalid input for chartTitle");
		if (xAxisLabel == null || xAxisLabel.isEmpty())
			throw new IllegalArgumentException("Invalid input for xAxisLabel");
		if (yAxisLabel == null || yAxisLabel.isEmpty())
			throw new IllegalArgumentException("Invalid input for yAxisLabel");

		JFreeChart chart = ChartFactory.createXYLineChart(chartTitle, xAxisLabel, yAxisLabel, dataset,
				PlotOrientation.VERTICAL, true, true, false);

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(800, 600));

		JFrame frame = new JFrame(chartTitle);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(chartPanel);
		frame.pack();
		frame.setVisible(true);
	}

}
