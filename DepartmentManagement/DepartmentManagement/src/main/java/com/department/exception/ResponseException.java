package com.department.exception;

public class ResponseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResponseException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResponseException(String errorCode, String message) {
		this(errorCode, new RuntimeException(message));
	}

}
