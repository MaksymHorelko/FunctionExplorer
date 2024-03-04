package function.impl;

import org.matheclipse.core.eval.ExprEvaluator;
import org.matheclipse.core.interfaces.IExpr;

import function.AppFunction;

public class AnalyticFunction implements AppFunction {
	private final String expression;
	private final ExprEvaluator evaluator = new ExprEvaluator();

	public AnalyticFunction(String expression) {
		if (expression == null || expression.trim().isEmpty()) {
			throw new IllegalArgumentException("Invalid expression");
		}
		this.expression = expression;
	}

	@Override
	public double calculate(double x) {
		try {
			evaluator.defineVariable("x", x);
			IExpr result = evaluator.evaluate(expression);

			if (result.isNumber()) {
				return result.evalDouble();
			} else {
				throw new IllegalArgumentException("Invalid result for expression: " + expression);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid expression or calculation error", e);
		}
	}

	public String getExpression() {
		return expression;
	}
}
