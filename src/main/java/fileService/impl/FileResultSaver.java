package fileService.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import exceptions.FileResultSaverException;
import exceptions.ListSizeMismatchException;
import fileService.ResultSaver;

public class FileResultSaver implements ResultSaver {
	@Override
	public void saveResults(List<Double> x, List<Double> y, List<Double> derivative, String fileName) {
		if (x.size() != y.size() || y.size() != derivative.size())
			throw new ListSizeMismatchException("Mismatch list's size");

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

			for (int i = 0; i < x.size(); i++) {
				String result = String.format("x=%-10.3f |  y=%-10.3f | dy/dx=%-10.3f\n", x.get(i), y.get(i),
						derivative.get(i));
				writer.write(result);
			}

			writer.close();

		} catch (IOException e) {
			throw new FileResultSaverException("Problem with saving file: " + e.getMessage());
		} catch (Exception e) {
			throw new RuntimeException("FileResultSaver problem", e);
		}
	}
}
