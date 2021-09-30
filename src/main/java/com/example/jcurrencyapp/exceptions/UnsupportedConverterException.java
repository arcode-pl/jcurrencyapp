package com.example.jcurrencyapp.exceptions;

@SuppressWarnings("serial")
public class UnsupportedConverterException extends RuntimeException {
	public UnsupportedConverterException(String msg, Throwable cause) {
		super(msg, cause);
	}
}