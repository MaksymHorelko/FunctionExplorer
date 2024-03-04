package function.impl;

import org.matheclipse.core.eval.ExprEvaluator;
import org.matheclipse.core.interfaces.IExpr;

import function.AppFunction;

public class AnalyticFunction implements AppFunction {
	private final String expression;
	private final ExprEvaluator evaluator;

	public AnalyticFunction(String expression) {
		this.expression = expression;
		this.evaluator = new ExprEvaluator();
	}

	@Override
	public double calculate(double x) {
		evaluator.defineVariable("x", x);
		IExpr result = evaluator.evaluate(expression);
		return result.evalDouble();
	}

}
