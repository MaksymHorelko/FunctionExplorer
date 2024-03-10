package testApplication;

import java.util.Set;
import java.util.TreeMap;

import fileService.CsvDataService;
import fileService.impl.CsvDataServiceImpl;
import function.DataPoint;

public class ConreteCsvFileProcessor {
	
	private CsvDataService csvDataService = new CsvDataServiceImpl();
	
	public Set<DataPoint> saveDataToTreeSet(String filePathToCsvFile) {
		return csvDataService.readDataToTreeSet(filePathToCsvFile);
	}

	public TreeMap<Double, Double> saveDataToTreeMap(String filePathToCsvFile) {
		return csvDataService.readDataToTreeMap(filePathToCsvFile);
	}
}
