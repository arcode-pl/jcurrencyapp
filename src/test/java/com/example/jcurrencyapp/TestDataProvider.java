package com.example.jcurrencyapp;

import java.time.LocalDate;
import java.util.Optional;

import com.example.jcurrencyapp.data.provider.AppProvider;

public class TestDataProvider implements AppProvider {

	@Override
	public Optional<String> getData(String code, LocalDate date) {
		// TODO Auto-generated method stub
		return Optional.of("USD:3.85:2016-07-12");
	}
	
}
