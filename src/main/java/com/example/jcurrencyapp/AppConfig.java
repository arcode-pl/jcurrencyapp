package com.example.jcurrencyapp;

public class AppConfig {
	private int maxBackDays;
	
	public AppConfig() {
		this.setMaxBackDays(10);
	}

	public int getMaxBackDays() {
		return maxBackDays;
	}

	public void setMaxBackDays(int backDays) {
		this.maxBackDays = backDays;
	}
	
}
