package function.impl;

import java.util.ArrayList;

import function.AppFunction;

public class SinTableFunction implements AppFunction {
	private ArrayList<Double> xValues;
	private ArrayList<Double> sinValues;

	public SinTableFunction(ArrayList<Double> xValues, ArrayList<Double> sinValues) {
		this.xValues = xValues;
		this.sinValues = sinValues;
	}

	@Override
	public double calculate(double x) {
		int index = findIntervalIndex(x);

		double x1 = xValues.get(index);
		double x2 = xValues.get(index + 1);
		double y1 = sinValues.get(index);
		double y2 = sinValues.get(index + 1);

		return y1 + ((y2 - y1) / (x2 - x1)) * (x - x1);
	}

	private int findIntervalIndex(double x) {
		for (int i = 0; i < xValues.size() - 1; i++) {
			if (x >= xValues.get(i) && x <= xValues.get(i + 1)) {
				return i;
			}
		}
		return xValues.size() - 2;
	}
}