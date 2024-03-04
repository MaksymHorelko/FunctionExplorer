package exceptions;

public class CsvFileReaderException extends RuntimeException{
	public CsvFileReaderException(String message, Throwable cause) {
		super(message, cause);
	}

	public CsvFileReaderException(String message) {
		super(message);
	}

	public CsvFileReaderException(Throwable cause) {
		super(cause);
	}
}
