package main;

import java.util.ArrayList;
import java.util.List;

import differentiation.Differentiable;
import differentiation.impl.NumericalDifferentiator;
import fileService.FileReader;
import fileService.impl.TableFileReader;
import function.AppFunction;
import function.DataPoint;
import function.impl.AnalyticFunction;
import function.impl.TableFunction;

public class FunctionExplorer {

	private FileReader tableFileReader = new TableFileReader();
	private Differentiable numericalDifferentiator = new NumericalDifferentiator();

	public List<Double> differentiateAnalyticFunction(ArrayList<Double> xValues, AppFunction function) {
		return numericalDifferentiator.differentiate(xValues, function);
	}

	public List<Double> differentiateAnalyticFunction(ArrayList<Double> xValues, String function) {

		return numericalDifferentiator.differentiate(xValues, analyticFunctionFromString(function));
	}

	public List<Double> differentiateTableFunction(String filePath) {
		if (filePath == null || filePath.isEmpty())
			throw new IllegalArgumentException("Invalid input for filePath");

		ArrayList<Double> xTableValues = new ArrayList<Double>();
		ArrayList<Double> yValues = new ArrayList<Double>();

		List<DataPoint> coordinates = tableFileReader.read(filePath);

		for (DataPoint point : coordinates) {
			xTableValues.add(point.getX());
			yValues.add(point.getY());
		}

		AppFunction tableFunction = new TableFunction(xTableValues, yValues);

		return numericalDifferentiator.differentiate(xTableValues, tableFunction);
	}

	public List<Double> differentiateTableFunction(ArrayList<Double> xValues, ArrayList<Double> yValues) {
		if (xValues == null || xValues.isEmpty())
			throw new IllegalArgumentException("Invalid input for xValues");
		if (yValues == null || yValues.isEmpty())
			throw new IllegalArgumentException("Invalid input for yValues");

		AppFunction tableFunction = new TableFunction(xValues, yValues);

		return numericalDifferentiator.differentiate(xValues, tableFunction);
	}

	public AppFunction analyticFunctionFromString(String expression) {
		if (expression == null || expression.isEmpty())
			throw new IllegalArgumentException("Invalid input for expression");

		return new AnalyticFunction(expression);
	}
}
