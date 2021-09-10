package pl.arcode.jcurrencyapp;

import pl.arcode.jcurrencyapp.data.provider.ProviderFactory;
import pl.arcode.jcurrencyapp.data.provider.ProviderTypes;
import pl.arcode.jcurrencyapp.exceptions.WrongProtocolException;
import pl.arcode.jcurrencyapp.exceptions.WrongProviderException;

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
