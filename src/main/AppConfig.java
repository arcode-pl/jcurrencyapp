package main;

import data.provider.ProviderFactory;
import data.provider.ProviderTypes;
import exceptions.WrongProviderException;
import exceptions.WrongProtocolException;

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
