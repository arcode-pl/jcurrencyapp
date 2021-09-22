package com.example.jcurrencyapp.data.converter.nbp;

import java.math.BigDecimal;
import java.util.Optional;

import com.example.jcurrencyapp.data.converter.Converter;
import com.example.jcurrencyapp.data.converter.nbp.model.NbpCurrency;
import com.example.jcurrencyapp.data.parser.JsonParser;
import com.example.jcurrencyapp.data.parser.Parser;
import com.example.jcurrencyapp.exceptions.ConverterException;
import com.example.jcurrencyapp.model.FileTypes;

public class NbpJsonConverterImpl implements Converter {
	
	@Override
	public BigDecimal getRate(String data) {
		JsonParser<NbpCurrency> parser = new JsonParser<NbpCurrency>(NbpCurrency.class);

		try {
			Optional<NbpCurrency> currency = parser.deserialize(data);
			if (currency.isPresent()) {
				return BigDecimal.valueOf(currency.get().getSimpleAskRate());
			}
		} catch (Exception e) {
			throw new ConverterException("Can't get rate for input data: " + e.getMessage(), e);
		}

		return null;
	}
}
 