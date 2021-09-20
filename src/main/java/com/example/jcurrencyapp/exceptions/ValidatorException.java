package com.example.jcurrencyapp.exceptions;

@SuppressWarnings("serial")
public class ValidatorException extends RuntimeException {
	
	public ValidatorException(String msg) {
		super(msg);
	}
	
	public ValidatorException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
