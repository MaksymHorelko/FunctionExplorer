package generator;

import java.util.ArrayList;

//Інтерфейс для реалізації генерації значень
public interface ValuesGenerator {
	public ArrayList<Double> generate(double startX, double endX, double step);
}
