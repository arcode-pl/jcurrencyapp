package com.example.jcurrencyapp.exceptions;

public class AppException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public AppException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
