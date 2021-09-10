package com.example.jcurrencyapp;

import com.example.jcurrencyapp.data.provider.ProviderFactory;
import com.example.jcurrencyapp.data.provider.ProviderTypes;
import com.example.jcurrencyapp.exceptions.WrongProtocolException;
import com.example.jcurrencyapp.exceptions.WrongProviderException;

public class AppConfig {
	private String provider;
	
	public AppConfig() {
		this.provider = ProviderTypes.NBP_WEB_API_JSON;
	}
	
	public AppConfig(String provider) throws WrongProtocolException, WrongProviderException {
		setProvider(provider);
	}
	
	public String getProvider() {
		return provider;
	}
	
	public void setProvider(String provider) throws WrongProtocolException {
		if (!ProviderFactory.exist(provider)) {
			throw new WrongProtocolException();
		}
		
		this.provider = provider;
	}
	
	
}
