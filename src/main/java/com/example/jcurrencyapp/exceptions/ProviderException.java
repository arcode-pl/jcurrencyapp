package com.example.jcurrencyapp.exceptions;

public class ProviderException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public ProviderException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
