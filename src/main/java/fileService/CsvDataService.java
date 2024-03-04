package fileService;

import java.util.TreeMap;
import java.util.TreeSet;

import function.DataPoint;

public interface CsvDataService {
	public TreeSet<DataPoint> readDataToTreeSet(String filePath);

	public TreeMap<Double, Double> readDataToTreeMap(String filePath);
}
