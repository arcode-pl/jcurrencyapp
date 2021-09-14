package com.example.jcurrencyapp.data.converter.impl;

import java.math.BigDecimal;
import java.util.Optional;

import com.example.jcurrencyapp.data.converter.AppConverter;
import com.example.jcurrencyapp.data.model.pojo.nbp.NbpCurrency;
import com.example.jcurrencyapp.data.parser.impl.JsonParser;
import com.example.jcurrencyapp.exceptions.ConverterException;

public class NbpJsonConverter implements AppConverter {

	@Override
	public Optional<BigDecimal> getRate(String data) throws ConverterException {
		JsonParser<NbpCurrency> parser = new JsonParser<NbpCurrency>(NbpCurrency.class);
		
		try {
			Optional<NbpCurrency> currency = parser.deserialize(data);
			if (currency.isPresent()) {
				return Optional.of(BigDecimal.valueOf(currency.get().getSimpleAskRate()));
			}
		} catch (Exception e) {
			throw new ConverterException("getRate: " + e.getMessage());
		}

		return Optional.empty();
	}
	
}
 