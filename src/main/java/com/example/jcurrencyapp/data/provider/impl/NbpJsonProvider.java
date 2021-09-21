package com.example.jcurrencyapp.data.provider.impl;

import java.time.LocalDate;

import com.example.jcurrencyapp.data.converter.IConverter;
import com.example.jcurrencyapp.data.provider.IProvider;
import com.example.jcurrencyapp.io.webapi.NbpWebApiRequest;
import com.example.jcurrencyapp.io.webapi.WebApiController;
import com.example.jcurrencyapp.io.webapi.model.WebApiResponse;
import com.example.jcurrencyapp.model.CurrencyTypes;
import com.example.jcurrencyapp.model.Rate;

public class NbpJsonProvider implements IProvider {

	IConverter converter;
	
	public NbpJsonProvider(IConverter converter) {
		this.converter = converter;
	}

	@Override
	public String getData(CurrencyTypes code, LocalDate date) {
		String result = null;
		
		NbpWebApiRequest request = new NbpWebApiRequest();
		WebApiResponse response = WebApiController.readApi(request.getSimpleQuery(code, date, false));
		if (response.getCode() == 200) {
			result = response.getText();
		}
		
		return result;
	}

	@Override
	public IConverter getConverter() {
		return this.converter;
	}

	@Override
	public void setData(Rate rate) {
		// TODO Auto-generated method stub
		
	}
}
