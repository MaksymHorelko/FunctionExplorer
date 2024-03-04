
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import charts.ChartBuilder;
import charts.impl.ChartBuilderImpl;
import differentiation.Differentiable;
import differentiation.impl.NumericalDifferentiator;
import fileService.CsvDataService;
import fileService.FileReader;
import fileService.ResultSaver;
import fileService.impl.CsvDataServiceImpl;
import fileService.impl.FileResultSaver;
import fileService.impl.TableFileReader;
import function.AppFunction;
import function.DataPoint;
import function.impl.AppFunctionService;
import function.impl.SinTableFunction;
import generator.ValuesGenerator;
import generator.impl.ValuesGeneratorImpl;

public class Application {

	// values
	private List<Double> aValues;
	private List<Double> xValues;

	// functions
	private AppFunction firstFunction = x -> Math.exp(-x * x) * Math.sin(x); /* f(x) = exp(-x^2) * sin(x) */
	private AppFunction secondFunction; /* f(x) = exp(-a * x^2) * sin(x) */

	// service
	private AppFunctionService appFunctionService = new AppFunctionService();
	private ChartBuilder chartBuilder = new ChartBuilderImpl();

	// file services
	private FileReader tableFileReader = new TableFileReader();
	private CsvDataService csvDataService = new CsvDataServiceImpl();
	private ResultSaver fileResultSaver = new FileResultSaver();

	// utility
	private ValuesGenerator valuesGenerator = new ValuesGeneratorImpl();
	private Differentiable numericalDifferentiator = new NumericalDifferentiator();

	public Application() {
		xValues = valuesGenerator.generate(1.5, 6.5, 0.05);
		aValues = Arrays.asList(0.5, 1.0, 1.5);
	}

	public Application(List<Double> xValues, List<Double> aValues) {
		this.xValues = xValues;
		this.aValues = aValues;
	}

	public void processFirstFunction() {
		List<Double> yValues = appFunctionService.getYValues(xValues, firstFunction);
		List<Double> differentiate = numericalDifferentiator.differentiate(xValues, firstFunction);

		fileResultSaver.saveResults(xValues, yValues, differentiate, "output_first_fun");
	}

	public void processSecondFunction() {
		List<Double> yValues = appFunctionService.getYValues(xValues, firstFunction);

		for (double a : aValues) {
			secondFunction = x -> Math.exp(-a * x * x) * Math.sin(x);
			List<Double> differentiate = numericalDifferentiator.differentiate(xValues, secondFunction);
			fileResultSaver.saveResults(xValues, yValues, differentiate, "output_second_fun_" + a);
		}
	}

	public void processThirdFunction(String filePath) {
		ArrayList<Double> xTableValues = new ArrayList<Double>();
		ArrayList<Double> sinValues = new ArrayList<Double>();

		ArrayList<DataPoint> coordinates = tableFileReader.read(filePath);

		for (DataPoint point : coordinates) {
			xTableValues.add(point.getX());
			sinValues.add(point.getY());
		}

		AppFunction sinTableFunction = new SinTableFunction(xTableValues, sinValues);

		List<Double> differentiate = numericalDifferentiator.differentiate(xTableValues, sinTableFunction);

		fileResultSaver.saveResults(xTableValues, sinValues, differentiate, "output_third_fun");
	}

	public void processCsvFile(String csvFilePath) {
		Set<DataPoint> treeSetPoints = csvDataService.readDataToTreeSet(csvFilePath);

		TreeMap<Double, Double> treeMapPoints = csvDataService.readDataToTreeMap(csvFilePath);

	}

	public void createCharts(List<Double> xValues, ChartBuilder chartBuilder, List<Double> aValues) {

		chartBuilder.addFunction("f(x) = exp(-x^2) * sin(x)", firstFunction, xValues);

		for (double a : aValues) {
			AppFunction function = x -> Math.exp(-a * x * x) * Math.sin(x);
			chartBuilder.addFunction("f(x) = exp(-" + a + " * x^2) * sin(x)", function, xValues);
		}
	}

	public void showCharts(ChartBuilder chartBuilder) {
		chartBuilder.displayChart("Графіки функцій", "x", "y");
	}

	public void testFirstFunction() {
		try {
			processFirstFunction();
		} catch (Exception e) {
			throw new RuntimeException("First function error: ", e);
		}

		System.out.println("First function processed");
	}

	public void testSecondFunction() {
		try {
			processSecondFunction();
		} catch (Exception e) {
			throw new RuntimeException("Second function error: ", e);
		}

		System.out.println("Second function processed");
	}

	public void testThirdFunction(String filePath) {
		try {
			processThirdFunction(filePath);
		} catch (Exception e) {
			throw new RuntimeException("Third function error: ", e);
		}

		System.out.println("Thrid function processed");
	}

	public void testCsvFile(String filePath) {
		try {
			processCsvFile(filePath);
		} catch (Exception e) {
			throw new RuntimeException("CSV function error: ", e);
		}

		System.out.println("CSV function processed");
	}

	public void testCharts() {
		createCharts(xValues, chartBuilder, aValues);

		showCharts(chartBuilder);

		System.out.println("Chart function processed");
	}

	public void testAll() {
		testFirstFunction();

		testSecondFunction();

		testThirdFunction("/home/gexlq/java-workspace/FunctionExplorer/third_function");

		testCsvFile("/home/gexlq/java-workspace/FunctionExplorer/csvFile.csv");

		testCharts();

		System.out.println("All tests done");
	}
}
