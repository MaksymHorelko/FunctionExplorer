package generator.impl;

import java.util.ArrayList;
import java.util.List;

import generator.ValuesGenerator;

public class ValuesGeneratorImpl implements ValuesGenerator {
	@Override
	public List<Double> generate(double startX, double endX, double step) {
		if(startX >= endX)
			throw new IllegalArgumentException("Invalid input for borders: startX >= endX");
		if(step <= 0)
			throw new IllegalArgumentException("Invalid input for step");
		
		int length = (int) ((endX - startX) / step) + 1;
		List<Double> values = new ArrayList<>();
		for (int i = 0; i < length; i++) {
			values.add(startX + i * step);
		}
		return values;
	}
}
