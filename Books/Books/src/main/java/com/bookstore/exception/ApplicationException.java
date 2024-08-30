package com.bookstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ApplicationException extends RuntimeException {

	// Default constructor
	public ApplicationException() {
		super();
	}

	// Constructor with a custom error message
	public ApplicationException(String message) {
		super(message);
	}

	// Constructor with a custom error message and cause
	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	// Constructor with a cause
	public ApplicationException(Throwable cause) {
		super(cause);
	}
}
