package exceptions;

public class FileReaderException extends RuntimeException{
	public FileReaderException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileReaderException(String message) {
		super(message);
	}

	public FileReaderException(Throwable cause) {
		super(cause);
	}
}
