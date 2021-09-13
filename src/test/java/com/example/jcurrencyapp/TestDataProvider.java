package com.example.jcurrencyapp;

import java.time.LocalDate;
import java.util.Optional;

import com.example.jcurrencyapp.data.model.Currency;
import com.example.jcurrencyapp.data.provider.ProviderInterface;

public class TestDataProvider implements ProviderInterface {

	@Override
	public Optional<Currency> getRate(String code, LocalDate date) {
		// TODO Auto-generated method stub
		return Optional.of(Currency.fakeDataModel());
	}
	
}
