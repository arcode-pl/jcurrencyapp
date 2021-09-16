package com.example.jcurrencyapp.io.webapi.model;

public class WebApiResponse {
	int code = 0;
	String text;
	
	public WebApiResponse() {
		super();
		this.code = 0;
		this.text = "";
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
