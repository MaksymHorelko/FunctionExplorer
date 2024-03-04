package fileService;

import java.util.List;

public interface ResultSaver {
	public void saveResults(List<Double> x, List<Double> y, List<Double> derivative, String fileName);
}