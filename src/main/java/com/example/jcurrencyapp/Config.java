package com.example.jcurrencyapp;

public class Config {
	public static final int MAX_BACK_DAYS = 365;
	private int maxBackDays;
	
	public Config() {
		this.setMaxBackDays(10);
	}

	public int getMaxBackDays() {
		return maxBackDays;
	}

	public void setMaxBackDays(int backDays) {
		if (backDays >= 0 && backDays <= MAX_BACK_DAYS) {
			this.maxBackDays = backDays;
		}
	}
}
