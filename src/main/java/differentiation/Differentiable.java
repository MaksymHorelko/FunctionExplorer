package differentiation;

import java.util.List;

import function.AppFunction;

public interface Differentiable {
	public List<Double> differentiate(List<Double> xValues, AppFunction appFunction);
}
