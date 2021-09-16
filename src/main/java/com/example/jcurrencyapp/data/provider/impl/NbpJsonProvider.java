package com.example.jcurrencyapp.data.provider.impl;

import java.time.LocalDate;

import com.example.jcurrencyapp.data.provider.IProvider;
import com.example.jcurrencyapp.io.webapi.NbpWebApiRequest;
import com.example.jcurrencyapp.io.webapi.WebApiController;
import com.example.jcurrencyapp.io.webapi.model.WebApiResponse;

public class NbpJsonProvider implements IProvider {

	@Override
	public String getData(String code, LocalDate date) {
		String result = null;
		
		NbpWebApiRequest request = new NbpWebApiRequest();
		WebApiResponse response = WebApiController.readApi(request.getSimpleQuery(code, date, false));
		if (response.getCode() == 200) {
			result = response.getText();
		}
		
		return result;
	}
}
