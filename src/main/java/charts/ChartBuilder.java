package charts;

import java.util.List;

import function.AppFunction;

public interface ChartBuilder {
	public void addFunction(String name, AppFunction function, List<Double> xValues);

	public void displayChart(String title, String xAxisLabel, String yAxisLabel);
}
