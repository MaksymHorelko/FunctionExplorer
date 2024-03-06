package generator.impl;

import java.util.ArrayList;

import generator.ValuesGenerator;

public class ValuesGeneratorImpl implements ValuesGenerator {
	@Override
	public ArrayList<Double> generate(double startX, double endX, double step) {
		if(startX >= endX)
			throw new IllegalArgumentException("Invalid input for borders: startX >= endX");
		if(step <= 0)
			throw new IllegalArgumentException("Invalid input for step");
		
		int length = (int) ((endX - startX) / step) + 1;
		ArrayList<Double> values = new ArrayList<>();
		for (int i = 0; i < length; i++) {
			values.add(startX + i * step);
		}
		return values;
	}
}
