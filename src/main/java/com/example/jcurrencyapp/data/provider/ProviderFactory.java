package com.example.jcurrencyapp.data.provider;

import java.util.Map;

import com.example.jcurrencyapp.data.provider.impl.NbpWebApiJson;
import com.example.jcurrencyapp.data.provider.impl.NbpWebApiXml;

public class ProviderFactory {
	
	private static final Map<String, ProviderInterface> PROVIDERS = Map.ofEntries(
			Map.entry(ProviderTypes.NBP_WEB_API_JSON, new NbpWebApiJson()),
			Map.entry(ProviderTypes.NBP_WEB_API_XML, new NbpWebApiXml())
			);

	private ProviderInterface customProvider = null;
	
	public void addCustomProvider(String name, ProviderInterface provider) {
		//TODO: How to put to static map from dynamic context? Good practice in it?
		//ProviderFactory.PROVIDERS.put(name, provider);
		customProvider = provider;
	}
	
	public ProviderInterface getProvider(String provider) {
		
		//When custom provider is set, overwrite
		if(this.customProvider != null) {
			return customProvider;
		}
		
		return PROVIDERS.get(provider);
	}
	
	public boolean exist(String provider) {
		if(this.customProvider != null) {
			return true;
		}
		
		return PROVIDERS.get(provider) != null;
	}
}
