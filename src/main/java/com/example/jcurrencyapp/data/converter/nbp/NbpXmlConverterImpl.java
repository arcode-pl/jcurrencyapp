package com.example.jcurrencyapp.data.converter.nbp;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.example.jcurrencyapp.data.converter.Converter;
import com.example.jcurrencyapp.data.converter.nbp.model.NbpCurrency;
import com.example.jcurrencyapp.data.parser.XmlParser;
import com.example.jcurrencyapp.exceptions.ConverterException;
import com.example.jcurrencyapp.model.Rate;

public class NbpXmlConverterImpl implements Converter {

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

	@Override
	public List<Rate> getRates(String data) {
		// TODO Auto-generated method stub
		return null;
	}
}
