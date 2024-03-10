package testApplication;

import java.util.ArrayList;
import java.util.List;

import differentiation.impl.NumericalDifferentiator;
import fileService.ResultSaver;
import fileService.impl.FileResultSaver;
import fileService.impl.TableFileReader;
import function.AppFunction;
import function.DataPoint;
import function.impl.AppFunctionService;
import function.impl.TableFunction;
import generator.ValuesGenerator;
import generator.impl.ValuesGeneratorImpl;
import main.FunctionExplorer;

public class ConcreteFunctionProcessor {
	private final ValuesGenerator valuesGenerator = new ValuesGeneratorImpl();
	private final AppFunctionService appFunctionService = new AppFunctionService();
	private final NumericalDifferentiator numericalDifferentiator = new NumericalDifferentiator();
	private final TableFileReader tableFileReader = new TableFileReader();
	private final ResultSaver fileResultSaver = new FileResultSaver();
	private final FunctionExplorer functionExplorer = new FunctionExplorer();

	private final double xMin = 1.5;
	private final double xMax = 6.5;
	private final double xStep = 0.05;
	private final ArrayList<Double> xValues = valuesGenerator.generate(xMin, xMax, xStep);

	private final double aMin = 0.5;
	private final double aMax = 1.5;
	private final double aStep = 0.5;
	private final ArrayList<Double> aValues = valuesGenerator.generate(aMin, aMax, aStep);

	private AppFunction firstFunction = x -> Math.exp(-x * x) * Math.sin(x);
	private AppFunction secondFunction;

	public void processFirstFunction(String outputFileName) {
		List<Double> yValues = appFunctionService.getYValues(xValues, firstFunction);
		List<Double> differentiate = functionExplorer.differentiateAnalyticFunction(xValues, firstFunction);
		fileResultSaver.saveResults(xValues, yValues, differentiate, outputFileName);
	}

	public void processSecondFunction(String outputFileName) {
		List<Double> yValues = appFunctionService.getYValues(xValues, firstFunction);
		for (double a : aValues) {
			secondFunction = x -> Math.exp(-a * x * x) * Math.sin(x);
			List<Double> differentiate = functionExplorer.differentiateAnalyticFunction(xValues, secondFunction);
			fileResultSaver.saveResults(xValues, yValues, differentiate, outputFileName + a);
		}
	}

	public void processThirdFunction(String filePath, String outputFileName) {
		ArrayList<Double> xTableValues = new ArrayList<>();
		ArrayList<Double> sinValues = new ArrayList<>();

		ArrayList<DataPoint> coordinates = tableFileReader.read(filePath);

		for (DataPoint point : coordinates) {
			xTableValues.add(point.getX());
			sinValues.add(point.getY());
		}

		AppFunction sinTableFunction = new TableFunction(xTableValues, sinValues);

		List<Double> differentiate = numericalDifferentiator.differentiate(xTableValues, sinTableFunction);

		fileResultSaver.saveResults(xTableValues, sinValues, differentiate, outputFileName);
	}

	public void processCustomUserFunction(String customFunction, String outputFileName) {

		List<Double> yValues = appFunctionService.getYValues(xValues,
				functionExplorer.analyticFunctionFromString(customFunction));
		List<Double> differentiate = functionExplorer.differentiateAnalyticFunction(xValues, customFunction);
		fileResultSaver.saveResults(xValues, yValues, differentiate, outputFileName);
	}

}
