package generator;

import java.util.List;

//Інтерфейс для реалізації генерації значень
public interface ValuesGenerator {
	public List<Double> generate(double startX, double endX, double step);
}
