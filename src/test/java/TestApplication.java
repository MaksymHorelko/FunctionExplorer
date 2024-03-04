
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import charts.ChartBuilder;
import charts.impl.ChartBuilderImpl;
import fileService.ResultSaver;
import fileService.impl.FileResultSaver;
import function.AppFunction;
import function.DataPoint;
import function.impl.AppFunctionService;
import function.impl.TableFunction;
import generator.ValuesGenerator;
import generator.impl.ValuesGeneratorImpl;

public class TestApplication {
	// values
	private List<Double> aValues;
	private List<Double> xValues;

	// functions
	private AppFunction firstFunction = x -> Math.exp(-x * x) * Math.sin(x); /* f(x) = exp(-x^2) * sin(x) */
	private AppFunction secondFunction; /* f(x) = exp(-a * x^2) * sin(x) */

	// service
	private AppFunctionService appFunctionService = new AppFunctionService();
	private ChartBuilder chartBuilder = new ChartBuilderImpl();
	private ResultSaver fileResultSaver = new FileResultSaver();

	// utility
	private ValuesGenerator valuesGenerator = new ValuesGeneratorImpl();

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

		AppFunction sinTableFunction = new TableFunction(xTableValues, sinValues);

		List<Double> differentiate = numericalDifferentiator.differentiate(xTableValues, sinTableFunction);

		fileResultSaver.saveResults(xTableValues, sinValues, differentiate, "output_third_fun");
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
	
	
	private static void processChoise(String choise) {

		if (choise.equals("1")) {

		} else if (choise.equals("2")) {

		}

		else if (choise.equals("3")) {

		} else if (choise.equals("4")) {

		} else if (choise.equals("5")) {

		}

	}

	public static void main(String[] args) {

		FunctionExplorer functionExplorer = new FunctionExplorer();
		testAll();

		Scanner scanner = new Scanner(System.in);
		System.out.println("Введіть що вам потрібно" + "ВСІ. Протестувати всі функції" + "1. Протестувати першу функцію"
				+ "2. Протестувати другу функцію" + "3. Протестувати третю функцію" + "4. Протестувати CSV файл"
				+ "5. Відобразити графік функцій"

		);

		while (true) {
			String choise = scanner.nextLine();

			processChoise(choise);
		}
//		scanner.close();

	}
}
