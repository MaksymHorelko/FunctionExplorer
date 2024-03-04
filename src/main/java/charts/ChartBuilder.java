package charts;

import java.util.List;

import function.AppFunction;

public interface ChartBuilder {
	public void addFunction(List<Double> xValues, AppFunction function, String name);

	public void displayChart(String title, String xAxisLabel, String yAxisLabel);
}
