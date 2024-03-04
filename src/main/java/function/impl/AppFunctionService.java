package function.impl;

import java.util.ArrayList;
import java.util.List;

import function.AppFunction;

public class AppFunctionService {
	public List<Double> getYValues(List<Double> xValues, AppFunction function) {
		if(xValues == null || xValues.isEmpty())
			throw new IllegalArgumentException("Ivalid input for xValues");
		if(function == null)
			throw new IllegalArgumentException("Invalid input for function: function is null");
		List<Double> yValues = new ArrayList<>();
		for (double x : xValues) {
			double result = function.calculate(x);
			yValues.add(result);
		}
		return yValues;
	}
}
