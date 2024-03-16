package fileService;

import java.util.ArrayList;
import java.util.List;

import function.DataPoint;

public interface FileReader {
	public List<DataPoint> read(String filePath);

}
