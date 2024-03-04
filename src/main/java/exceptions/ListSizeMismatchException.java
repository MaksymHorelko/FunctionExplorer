package exceptions;

public class ListSizeMismatchException extends RuntimeException{
	public ListSizeMismatchException(String message, Throwable cause) {
		super(message, cause);
	}

	public ListSizeMismatchException(String message) {
		super(message);
	}

	public ListSizeMismatchException(Throwable cause) {
		super(cause);
	}
}