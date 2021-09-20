package com.example.jcurrencyapp.exceptions;

@SuppressWarnings("serial")
public class ProviderException extends RuntimeException {
	
	public ProviderException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
