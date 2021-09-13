package com.example.jcurrencyapp;

import com.example.jcurrencyapp.data.provider.ProviderTypes;

public class AppConfig {
	private String provider;
	private int maxBackDays;
	
	public AppConfig() {
		this.provider = ProviderTypes.NBP_WEB_API_JSON;
		this.setMaxBackDays(10);
	}
	
	public AppConfig(String provider) {
		setProvider(provider);
	}
	
	public String getProvider() {
		return provider;
	}
	
	public void setProvider(String provider) {
		this.provider = provider;
	}

	public int getMaxBackDays() {
		return maxBackDays;
	}

	public void setMaxBackDays(int backDays) {
		this.maxBackDays = backDays;
	}
	
	
}
