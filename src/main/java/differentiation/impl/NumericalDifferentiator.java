package differentiation.impl;

import java.util.ArrayList;
import java.util.List;

import differentiation.Differentiable;
import function.AppFunction;

public class NumericalDifferentiator implements Differentiable {
	@Override
	public List<Double> differentiate(List<Double> xValues, AppFunction function) {
		List<Double> result = new ArrayList<>();
		for (double x : xValues) {
			result.add(differentiate(x, function));
		}
		return result;
	}

	private double differentiate(double x, AppFunction function) {
		double h = 1e-6;
		return (function.calculate(x + h) - function.calculate(x - h)) / (2 * h);
	}
}
