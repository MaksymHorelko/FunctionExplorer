package testApplication;

import java.util.ArrayList;
import java.util.List;

import charts.ChartBuilder;
import charts.impl.ChartBuilderImpl;
import function.AppFunction;
import generator.ValuesGenerator;
import generator.impl.ValuesGeneratorImpl;
import main.FunctionExplorer;

public class ChartProcessor {

	private final ValuesGenerator valuesGenerator = new ValuesGeneratorImpl();

	private final double xMin = 1.5;
	private final double xMax = 6.5;
	private final double xStep = 0.05;
	private final ArrayList<Double> xValues = valuesGenerator.generate(xMin, xMax, xStep);

	private final double aMin = 0.5;
	private final double aMax = 1.5;
	private final double aStep = 0.5;
	private final ArrayList<Double> aValues = valuesGenerator.generate(aMin, aMax, aStep);

	private final FunctionExplorer functionExplorer = new FunctionExplorer();
	private ChartBuilder chartBuilder;

	private void showFunction(List<Double> xValues, AppFunction function, String functionName) {
		chartBuilder = new ChartBuilderImpl();
		chartBuilder.addFunction(xValues, function, functionName);
		chartBuilder.displayChart("Графіки функцій", "x", "y");
	}

	public void showFirstFunction() { /* f(x) = exp(-x^2) * sin(x) */
		showFunction(xValues, x -> Math.exp(-x * x) * Math.sin(x), "f(x) = exp(-x^2) * sin(x)");
	}

	public void showSecondFunction() { /* f(x) = exp(-a * x^2) * sin(x) */
		for (double a : aValues) {
			showFunction(xValues, x -> Math.exp(-a * x * x) * Math.sin(x), "f(x) = exp(-" + a + " * x^2) * sin(x)");
		}
	}

	public void showThirdFunction() {

	}
	
	public void showCustomFunction(String customFunction) {
		showFunction(xValues, functionExplorer.analyticFunctionFromString(customFunction), customFunction);
	}
}
