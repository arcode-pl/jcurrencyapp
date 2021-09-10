package com.example.jcurrencyapp.exceptions;

public class WrongDateException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public WrongDateException() {
	   super("Wrong Date Exception");
	}
}
