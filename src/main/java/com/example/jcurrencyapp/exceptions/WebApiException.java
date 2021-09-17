package com.example.jcurrencyapp.exceptions;

public class WebApiException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public WebApiException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
