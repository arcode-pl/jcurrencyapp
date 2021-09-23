package com.example.jcurrencyapp.data.provider;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.jcurrencyapp.data.converter.Converter;
import com.example.jcurrencyapp.data.converter.nbp.NbpXmlConverterImpl;
import com.example.jcurrencyapp.io.webapi.NbpWebApiRequest;
import com.example.jcurrencyapp.io.webapi.WebApiController;
import com.example.jcurrencyapp.io.webapi.model.WebApiResponse;
import com.example.jcurrencyapp.model.CurrencyTypes;
import com.example.jcurrencyapp.model.Rate;

public class NbpXmlProviderImpl implements Provider { // is implements need here?

	Converter converter;

	public NbpXmlProviderImpl() {
		this.converter = new NbpXmlConverterImpl();
	}
	
	@Override
	public BigDecimal getRate(CurrencyTypes code, LocalDate date) {

		WebApiResponse response = WebApiController.tryReadApi(NbpWebApiRequest.getXmlQuery(code, date));
		if (response.getCode() == 200) {
			return converter.getRate(response.getText());
		}

		return null;
	}

	@Override
	public void saveRate(Rate rate) {
		// TODO Auto-generated method stub
		
	}

}
