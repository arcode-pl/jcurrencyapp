package com.example.jcurrencyapp.exceptions;

public class ConverterException extends Exception {
	private static final long serialVersionUID = 1L;
	public ConverterException(String msg) {
		super("ConverterException: " + msg);
	}
}
