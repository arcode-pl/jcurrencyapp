package com.example.jcurrencyapp.data.provider.impl;

import java.time.LocalDate;

import com.example.jcurrencyapp.data.provider.IProvider;
import com.example.jcurrencyapp.io.webapi.ApiResponse;
import com.example.jcurrencyapp.io.webapi.NbpWebApiRequest;
import com.example.jcurrencyapp.io.webapi.WebApiController;

public class NbpXmlProvider implements IProvider {

	@Override
	public String getData(String code, LocalDate date) {
		String result = null;
		NbpWebApiRequest request = new NbpWebApiRequest();
		
		ApiResponse response = WebApiController.readApi(request.getSimpleQuery(code, date, true));
		if (response.getCode() == 200) {
			result = response.getText();
		}
		
		return result;
	}

}
