package com.example.jcurrencyapp.exceptions;

@SuppressWarnings("serial")
public class DatabaseProviderException extends RuntimeException {
	
	public DatabaseProviderException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
