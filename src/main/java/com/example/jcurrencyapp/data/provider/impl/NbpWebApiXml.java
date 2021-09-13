package com.example.jcurrencyapp.data.provider.impl;

import java.time.LocalDate;
import java.util.Optional;

import com.example.jcurrencyapp.data.model.Currency;
import com.example.jcurrencyapp.data.provider.ProviderInterface;

public class NbpWebApiXml implements ProviderInterface {

	@Override
	public Optional<Currency> getRate(String code, LocalDate date) {
		// TODO Implement get currency from XML
		return Optional.of(Currency.fakeDataModel());
	}

}
