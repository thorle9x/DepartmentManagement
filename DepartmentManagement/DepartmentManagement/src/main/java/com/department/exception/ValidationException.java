package com.department.exception;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidationException(String errorCode, String message) {
		this(errorCode, new RuntimeException(message));
	}

}
