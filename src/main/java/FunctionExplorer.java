
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

import differentiation.Differentiable;
import differentiation.impl.NumericalDifferentiator;
import fileService.CsvDataService;
import fileService.FileReader;
import fileService.impl.CsvDataServiceImpl;
import fileService.impl.TableFileReader;
import function.AppFunction;
import function.DataPoint;
import function.impl.AnalyticFunction;
import function.impl.TableFunction;

public class FunctionExplorer {

	// file services
	private FileReader tableFileReader = new TableFileReader();
	private CsvDataService csvDataService = new CsvDataServiceImpl();

	// differentiator
	private Differentiable numericalDifferentiator = new NumericalDifferentiator();

	public List<Double> differentiateAnalyticFunction(ArrayList<Double> xValues, AppFunction function) {
		return numericalDifferentiator.differentiate(xValues, function);
	}

	public List<Double> differentiateTableFunction(String filePath) {
		ArrayList<Double> xTableValues = new ArrayList<Double>();
		ArrayList<Double> yValues = new ArrayList<Double>();

		ArrayList<DataPoint> coordinates = tableFileReader.read(filePath);

		for (DataPoint point : coordinates) {
			xTableValues.add(point.getX());
			yValues.add(point.getY());
		}

		AppFunction tableFunction = new TableFunction(xTableValues, yValues);

		return numericalDifferentiator.differentiate(xTableValues, tableFunction);
	}

	public List<Double> differentiateTableFunction(ArrayList<Double> xValues, ArrayList<Double> yValues) {

		AppFunction tableFunction = new TableFunction(xValues, yValues);

		return numericalDifferentiator.differentiate(xValues, tableFunction);
	}

	public TreeSet<DataPoint> tableFunctionAsTreeSet(String csvFilePath) {
		return csvDataService.readDataToTreeSet(csvFilePath);
	}

	public TreeMap<Double, Double> tableFunctionAsTreeMap(String csvFilePath) {
		return csvDataService.readDataToTreeMap(csvFilePath);
	}

	public AppFunction analyticFunctionFromString(String expression) {
		return new AnalyticFunction(expression);
	}
}
