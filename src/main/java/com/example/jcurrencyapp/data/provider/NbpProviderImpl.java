package com.example.jcurrencyapp.data.provider;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.jcurrencyapp.data.converter.Converter;
import com.example.jcurrencyapp.data.converter.nbp.NbpJsonConverterImpl;
import com.example.jcurrencyapp.data.converter.nbp.NbpXmlConverterImpl;
import com.example.jcurrencyapp.exceptions.UnsupportedConverterException;
import com.example.jcurrencyapp.io.webapi.NbpWebApiRequest;
import com.example.jcurrencyapp.io.webapi.WebApiController;
import com.example.jcurrencyapp.io.webapi.model.WebApiResponse;
import com.example.jcurrencyapp.model.CurrencyTypes;
import com.example.jcurrencyapp.model.Rate;

public class NbpProviderImpl implements Provider { // is implements need here?

	Converter converter;

	public NbpProviderImpl(Converter converter) {
		this.converter = converter;
	}

	@Override
	public BigDecimal getRate(CurrencyTypes code, LocalDate date) {
		NbpWebApiRequest request = new NbpWebApiRequest(code, date);
		String query = converter instanceof NbpJsonConverterImpl ? request.getJsonQuery()
				: converter instanceof NbpXmlConverterImpl ? request.getXmlQuery() : null;
		if (query == null) {
			throw new UnsupportedConverterException("NbpProvider not support this converter yet", new Throwable());
		}

		WebApiResponse response = WebApiController.readApi(query);
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
