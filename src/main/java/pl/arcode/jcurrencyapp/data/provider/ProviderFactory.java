package pl.arcode.jcurrencyapp.data.provider;

import java.util.Map;

import pl.arcode.jcurrencyapp.data.mode.Currency;
import pl.arcode.jcurrencyapp.data.provider.impl.NbpWebApiJson;

public class ProviderFactory {
	
	private static final Map<String, ProviderInterface<Currency>> PROVIDERS = Map.ofEntries(
			Map.entry(ProviderTypes.NBP_WEB_API_JSON, new NbpWebApiJson()),
			Map.entry(ProviderTypes.NBP_WEB_API_XML, new NbpWebApiJson())
			);
	
	public static void addCustomProvider(String name, ProviderInterface<Currency> provider) {
		ProviderFactory.PROVIDERS.put(name, provider);
	}
	
	public static ProviderInterface<Currency> getProvider(String name) {
		return ProviderFactory.PROVIDERS.get(name);
	}
	
	public static boolean exist(String provider) {
		return PROVIDERS.get(provider) != null;
	}
}
