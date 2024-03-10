//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//import charts.ChartBuilder;
//import charts.impl.ChartBuilderImpl;
//import fileService.CsvDataService;
//import fileService.ResultSaver;
//import fileService.impl.CsvDataServiceImpl;
//import fileService.impl.FileResultSaver;
//import function.AppFunction;
//import function.DataPoint;
//import function.impl.AppFunctionService;
//import function.impl.TableFunction;
//import generator.ValuesGenerator;
//import generator.impl.ValuesGeneratorImpl;
//
//public class TestApplication {
//	// values
//	private List<Double> aValues;
//	private List<Double> xValues;
//
//	// functions
//	private AppFunction firstFunction = x -> Math.exp(-x * x) * Math.sin(x); /* f(x) = exp(-x^2) * sin(x) */
//	private AppFunction secondFunction; /* f(x) = exp(-a * x^2) * sin(x) */
//
//	// service
//	private AppFunctionService appFunctionService = new AppFunctionService();
//	private CsvDataService csvDataService = new CsvDataServiceImpl();
//	private ChartBuilder chartBuilder = new ChartBuilderImpl();
//	private ResultSaver fileResultSaver = new FileResultSaver();
//
//	// utility
//	private ValuesGenerator valuesGenerator = new ValuesGeneratorImpl();
//
//
//	public void testCsvFile(String filePath) {
//		try {
//			processCsvFile(filePath);
//		} catch (Exception e) {
//			throw new RuntimeException("CSV function error: ", e);
//		}
//
//		System.out.println("CSV function processed");
//	}
//
//	public void testCharts() {
//		createCharts(xValues, chartBuilder, aValues);
//
//		showCharts(chartBuilder);
//
//		System.out.println("Chart function processed");
//	}
//
//	public void testAll() {
//		testFirstFunction();
//
//		testSecondFunction();
//
//		testThirdFunction("/home/gexlq/java-workspace/FunctionExplorer/third_function");
//
//		testCsvFile("/home/gexlq/java-workspace/FunctionExplorer/csvFile.csv");
//
//		testCharts();
//
//		System.out.println("All tests done");
//	}
//
//	private static void processChoise(String choise) {
//
//		if (choise.equals("1")) {
//
//		} else if (choise.equals("2")) {
//
//		}
//
//		else if (choise.equals("3")) {
//
//		} else if (choise.equals("4")) {
//
//		} else if (choise.equals("5")) {
//
//		}
//
//	}
//*/
//	public static void main(String[] args) {
//
//		FunctionExplorer functionExplorer = new FunctionExplorer();
//
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("Введіть що вам потрібно:\n" + "ВСІ. Протестувати всі функції\n"
//				+ "1. Протестувати функцію f(x) = exp(-x^2) * sin(x)\n"
//				+ "2. Протестувати функцію f(x) = exp(-a * x^2) * sin(x)\n"
//				+ "3. Протестувати третю табличну функцію з файлу third_function\n" + "4. Протестувати CSV файл"
//				+ "5. Відобразити графік функцій"
//
//		);
//
//		while (true) {
//			String choise = scanner.nextLine();
//
////			processChoise(choise);
//		}
////		scanner.close();
//
//	}
//}
