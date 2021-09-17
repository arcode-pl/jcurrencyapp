package com.example.jcurrencyapp.data.converter.impl;

import java.math.BigDecimal;
import java.util.Optional;

import com.example.jcurrencyapp.data.converter.IConverter;
import com.example.jcurrencyapp.data.converter.model.NbpCurrency;
import com.example.jcurrencyapp.data.parser.impl.XmlParser;
import com.example.jcurrencyapp.exceptions.ConverterException;

public class NbpXmlConverter implements IConverter {

	@Override
	public BigDecimal getRate(String data) {
		XmlParser<NbpCurrency> parser = new XmlParser<NbpCurrency>(NbpCurrency.class);

		try {
			Optional<NbpCurrency> currency = parser.deserialize(data);
			if (currency.isPresent()) {
				return BigDecimal.valueOf(currency.get().getSimpleAskRate());
			}
		} catch (Exception e) {
			throw new ConverterException("Can't get rate for input data: " + e.getMessage(), e.getCause());
		}
		
		return null;
	}
}
