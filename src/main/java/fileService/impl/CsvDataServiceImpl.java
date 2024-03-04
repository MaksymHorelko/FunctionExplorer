package fileService.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;
import java.util.TreeSet;

import fileService.CsvDataService;
import function.DataPoint;

public class CsvDataServiceImpl implements CsvDataService {

	@Override
	public TreeSet<DataPoint> readDataToTreeSet(String filePath) {
		TreeSet<DataPoint> dataSet = new TreeSet<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				double[] point = parseLineToPoint(line);
				dataSet.add(new DataPoint(point[0], point[1]));
			}
		} catch (IOException | NumberFormatException e) {
			e.printStackTrace();
		}

		return dataSet;
	}

	@Override
	public TreeMap<Double, Double> readDataToTreeMap(String filePath) {
		TreeMap<Double, Double> dataMap = new TreeMap<Double, Double>();

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				double[] point = parseLineToPoint(line);
				dataMap.put(point[0], point[1]);
			}
		} catch (IOException | NumberFormatException e) {
			e.printStackTrace();
		}

		return dataMap;
	}

	private double[] parseLineToPoint(String line) {
		String[] parts = line.split(",");

		double x = Double.parseDouble(parts[0]);
		double y = Double.parseDouble(parts[1]);
		return new double[] { x, y };
	}


}