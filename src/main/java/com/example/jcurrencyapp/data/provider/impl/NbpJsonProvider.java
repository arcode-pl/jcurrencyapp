package com.example.jcurrencyapp.data.provider.impl;

import java.time.LocalDate;
import java.util.Optional;

import com.example.jcurrencyapp.controller.WebApiController;
import com.example.jcurrencyapp.data.provider.AppProvider;
import com.example.jcurrencyapp.exceptions.ReadApiException;
import com.example.jcurrencyapp.service.ApiResponse;
import com.example.jcurrencyapp.service.NbpWebApiRequest;

public class NbpJsonProvider implements AppProvider {

	@Override
	public Optional<String> getData(String code, LocalDate date) throws ReadApiException {
		NbpWebApiRequest request = new NbpWebApiRequest();
		ApiResponse response = WebApiController.readApi(request.getSimpleQuery(code, date, false));
		
		if(response.getCode() == 200) {
			return Optional.of(response.getText());
		}
		
		return Optional.empty();
	}
}
