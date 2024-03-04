package function.impl;

import java.util.ArrayList;
import java.util.List;

import function.AppFunction;

public class AppFunctionService {
	public List<Double> getYValues(List<Double> xValues, AppFunction function) {
		List<Double> yValues = new ArrayList<>();
		for (double x : xValues) {
			double result = function.calculate(x);
			yValues.add(result);
		}
		return yValues;
	}
}
