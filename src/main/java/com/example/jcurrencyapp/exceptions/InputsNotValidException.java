package com.example.jcurrencyapp.exceptions;

public class InputsNotValidException extends Exception {
	private static final long serialVersionUID = 1L;
	public InputsNotValidException(String msg) {
		super("InputsNotValidException: " + msg);
	}
}
