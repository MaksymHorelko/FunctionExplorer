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
	private final XYSeriesCollection dataset;

	public ChartBuilderImpl() {
		this.dataset = new XYSeriesCollection();
	}

	@Override
	public void addFunction(String name, AppFunction function, List<Double> xValues) {
		XYSeries series = new XYSeries(name);
		for (double x : xValues) {
			series.add(x, function.calculate(x));
		}
		dataset.addSeries(series);
	}

	@Override
	public void displayChart(String title, String xAxisLabel, String yAxisLabel) {
		JFreeChart chart = ChartFactory.createXYLineChart(title, xAxisLabel, yAxisLabel, dataset,
				PlotOrientation.VERTICAL, true, true, false);

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(800, 600));

		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(chartPanel);
		frame.pack();
		frame.setVisible(true);
	}

}
