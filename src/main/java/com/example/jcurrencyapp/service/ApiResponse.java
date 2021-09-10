package com.example.jcurrencyapp.service;

public class ApiResponse {
	int code = 0;
	String text;
	
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
