package fileService;

import java.util.ArrayList;

import function.DataPoint;

public interface FileReader {
	public ArrayList<DataPoint> read(String filePath);

}
