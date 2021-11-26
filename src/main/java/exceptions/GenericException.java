package exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

@Getter
@SuppressWarnings({"unused"})
public class GenericException extends Exception {
	private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

	public GenericException() {
		super();
	}

	public GenericException(String message) {
		super(message);
	}

	public GenericException(String message, @Nullable Throwable cause) {
		super(message, cause);
	}

	public GenericException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public GenericException(String message, HttpStatus httpStatus, @Nullable Throwable cause) {
		super(message, cause);
		this.httpStatus = httpStatus;
	}

	public GenericException(String message, int statusCode) {
		super(message);
		this.httpStatus = HttpStatus.valueOf(statusCode);
	}

	public GenericException(String message, int statusCode, @Nullable Throwable cause) {
		super(message, cause);
		this.httpStatus = HttpStatus.valueOf(statusCode);
	}
}
