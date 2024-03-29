package fileService.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exceptions.FileReaderException;
import fileService.FileReader;
import function.DataPoint;

public class TableFileReader implements FileReader {

	@Override
	public List<DataPoint> read(String filePath) {

		List<DataPoint> coordinates = new ArrayList<>();

		try (Scanner scanner = new Scanner(new File(filePath))) {
			while (scanner.hasNextDouble()) {
				coordinates.add(new DataPoint(scanner.nextDouble(), scanner.nextDouble()));
			}
		} catch (Exception e) {
			throw new FileReaderException(e);
		}
		return coordinates;
	}

}
