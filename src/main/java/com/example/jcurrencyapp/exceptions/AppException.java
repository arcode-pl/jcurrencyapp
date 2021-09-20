package com.example.jcurrencyapp.exceptions;

@SuppressWarnings("serial")
public class AppException extends RuntimeException {
	
	public AppException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
