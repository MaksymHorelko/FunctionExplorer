package function.impl;

import java.util.ArrayList;

import function.AppFunction;

public class TableFunction implements AppFunction {
	private ArrayList<Double> xValues;
	private ArrayList<Double> yValues;

	public TableFunction(ArrayList<Double> xValues, ArrayList<Double> yValues) {
		if (xValues == null || xValues.isEmpty())
			throw new IllegalArgumentException("Ivalid input for xValues");
		if (yValues == null || yValues.isEmpty())
			throw new IllegalArgumentException("Invalid input for yValue");
		this.xValues = xValues;
		this.yValues = yValues;
	}

	@Override
	public double calculate(double x) {
		double res = 0.0;
		int numData = xValues.size();
		double numer, denom;
		for (int k = 0; k < numData; k++) {
			numer = 1.0;
			denom = 1.0;
			for (int j = 0; j < numData; j++) {
				if (j != k) {
					numer = numer * (x - xValues.get(j));
					denom = denom * (xValues.get(k) - xValues.get(j));
				}
			}
			res = res + yValues.get(k) * numer / denom;
		}
		return res;
	}
}