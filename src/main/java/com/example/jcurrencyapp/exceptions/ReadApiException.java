package com.example.jcurrencyapp.exceptions;

public class ReadApiException extends Exception {
	private static final long serialVersionUID = 1L;
	public ReadApiException(String msg) {
		super("ReadApiException: " + msg);
	}
}
