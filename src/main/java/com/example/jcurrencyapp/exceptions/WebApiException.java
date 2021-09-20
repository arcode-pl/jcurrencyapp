package com.example.jcurrencyapp.exceptions;

@SuppressWarnings("serial")
public class WebApiException extends RuntimeException{

	public WebApiException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
