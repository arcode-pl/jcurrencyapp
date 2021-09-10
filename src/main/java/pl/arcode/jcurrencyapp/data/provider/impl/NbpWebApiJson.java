package pl.arcode.jcurrencyapp.data.provider.impl;

import java.time.LocalDate;
import java.util.Optional;

import pl.arcode.jcurrencyapp.data.mode.Currency;
import pl.arcode.jcurrencyapp.data.provider.ProviderInterface;
import pl.arcode.jcurrencyapp.data.provider.controller.WebApiController;
import pl.arcode.jcurrencyapp.data.provider.pojo.nbp.NbpCurrency;
import pl.arcode.jcurrencyapp.data.provider.pojo.nbp.NbpParser;
import pl.arcode.jcurrencyapp.service.ApiResponse;
import pl.arcode.jcurrencyapp.service.NbpWebApiRequest;

public class NbpWebApiJson implements ProviderInterface<Currency> {

	@Override
	public Optional<Currency> getRate(String code, LocalDate date) {
		//Currency rate = Currency.testDataModel();
		
		NbpWebApiRequest request = new NbpWebApiRequest();
		
		ApiResponse response = WebApiController.readApi(request.getSimpleQuery(code, date, false));
		
		if(response.getCode() == 200) {
			NbpCurrency currency = NbpParser.deserialize(response.getText(), false);
			System.out.println("ASK PRICE: " + currency.getRates().get(0).getAsk());
			
			System.out.println(response.getText());
			return Optional.of(Currency.testDataModel());
		}
		
		return Optional.empty();
	}
}
