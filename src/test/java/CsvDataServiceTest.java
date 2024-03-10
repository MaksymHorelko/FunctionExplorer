import static org.junit.Assert.*;

import java.util.Set;
import java.util.TreeMap;

import org.junit.Test;

import fileService.CsvDataService;
import fileService.impl.CsvDataServiceImpl;
import function.DataPoint;

public class CsvDataServiceTest {

	private final CsvDataService csvDataService = new CsvDataServiceImpl();

	@Test
	public void testReadDataToTreeSet() {
		String csvFilePath = "/home/mint/java-workspace/FunctionExplorer/csvFile.csv";

		Set<DataPoint> treeSetPoints = csvDataService.readDataToTreeSet(csvFilePath);

		StringBuilder result = new StringBuilder();
		result.append("{");
		for (DataPoint point : treeSetPoints) {
			result.append(point.getX() + "=" + point.getY() + " ");
		}
		result.append("}");
		StringBuilder expected = new StringBuilder(
				"{1.0=9.0 2.0=8.0 3.0=7.0 4.0=6.0 5.0=5.0 6.0=4.0 7.0=3.0 8.0=2.0 9.0=1.0 10.0=0.0 }");

		assertEquals(result.toString(), expected.toString());
	}

	@Test
	public void testReadDataToTreeMap() {
		String csvFilePath = "/home/mint/java-workspace/FunctionExplorer/csvFile.csv";

		TreeMap<Double, Double> treeMapPoints = csvDataService.readDataToTreeMap(csvFilePath);

		String result = treeMapPoints.toString();
		String expected = "{1.0=9.0, 2.0=8.0, 3.0=7.0, 4.0=6.0, 5.0=5.0, 6.0=4.0, 7.0=3.0, 8.0=2.0, 9.0=1.0, 10.0=0.0}";

		assertEquals(result, expected);
	}

}
