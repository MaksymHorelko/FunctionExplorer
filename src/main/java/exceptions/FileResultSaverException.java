package exceptions;

public class FileResultSaverException extends RuntimeException{
	public FileResultSaverException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileResultSaverException(String message) {
		super(message);
	}

	public FileResultSaverException(Throwable cause) {
		super(cause);
	}
}
