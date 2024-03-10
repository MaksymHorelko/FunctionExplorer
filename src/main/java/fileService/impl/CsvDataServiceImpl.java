package fileService.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.TreeMap;
import java.util.TreeSet;

import exceptions.FileReaderException;
import fileService.CsvDataService;
import function.DataPoint;

public class CsvDataServiceImpl implements CsvDataService {

	@Override
	public TreeSet<DataPoint> readDataToTreeSet(String csvFilePath) {
		if (csvFilePath == null || csvFilePath.isEmpty())
			throw new IllegalArgumentException("Invalid input for csvcsvFilePath");

		TreeSet<DataPoint> dataSet = new TreeSet<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				dataSet.add(parseLineToPoint(line));
			}
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Invalid number format in CSV file");
		} catch (Exception e) {
			throw new FileReaderException(e);
		}
		return dataSet;
	}

	@Override
	public TreeMap<Double, Double> readDataToTreeMap(String csvFilePath) {
		if (csvFilePath == null || csvFilePath.isEmpty())
			throw new IllegalArgumentException("Invalid input for csvcsvFilePath");

		TreeMap<Double, Double> dataMap = new TreeMap<Double, Double>();

		try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				DataPoint point = parseLineToPoint(line);
				dataMap.put(point.getX(), point.getY());
			}
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Invalid number format in CSV file");
		} catch (Exception e) {
			throw new FileReaderException(e);
		}

		return dataMap;
	}

	private DataPoint parseLineToPoint(String line) {
		String[] parts = line.split(",");
		double x = Double.parseDouble(parts[0]);
		double y = Double.parseDouble(parts[1]);
		return new DataPoint(x, y);
	}

}