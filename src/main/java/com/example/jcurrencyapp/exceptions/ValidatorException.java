package com.example.jcurrencyapp.exceptions;

public class ValidatorException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ValidatorException(String msg) {
		super(msg);
	}
	
	public ValidatorException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
