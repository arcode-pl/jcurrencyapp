package strategy.api;

import java.util.Map;

import exceptions.WrongApiException;
import strategy.api.impl.NbpWebApi;

public class ApiFactory {
	
	private static final Map<Integer, ApiStrategy> APIS = Map.ofEntries(
			Map.entry(ApiTypes.NBP, new NbpWebApi())//,
			//Map.entry(SupportedApiTypes.ONET, new OnetApi())
			);

	public static ApiStrategy getApi(int type) throws WrongApiException {
		ApiStrategy api = APIS.get(type);
		if (api == null) {
			throw new WrongApiException();
		}
		
		return api;
	}
	
	public static void exist(int api) throws WrongApiException {
		if (APIS.get(api) == null) {
			throw new WrongApiException();
		}
	}
}

	
