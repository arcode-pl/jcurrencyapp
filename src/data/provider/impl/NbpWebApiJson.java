package data.provider.impl;

import java.time.LocalDate;
import java.util.Optional;

import data.model.Currency;
import data.provider.ProviderInterface;
import data.provider.controller.WebApiController;
import service.ApiResponse;
import service.NbpWebApiRequest;

public class NbpWebApiJson implements ProviderInterface<Currency> {

	@Override
	public Optional<Currency> getRate(String code, LocalDate date) {
		//Currency rate = Currency.testDataModel();
		
		NbpWebApiRequest request = new NbpWebApiRequest();
		
		ApiResponse response = WebApiController.readApi(request.getSimpleQuery(code, date, false));
		
		if(response.getCode() == 200) {
			//TODO: Here need be implemented web api to pojo class for json
			System.out.println(response.getText());
			return Optional.of(Currency.testDataModel());
		}
		
		return Optional.empty();
	}
}
