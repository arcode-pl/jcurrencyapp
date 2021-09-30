package com.example.jcurrencyapp.data.converter.nbp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.jcurrencyapp.data.converter.Converter;
import com.example.jcurrencyapp.data.converter.nbp.model.NbpCurrency;
import com.example.jcurrencyapp.data.converter.nbp.model.NbpRate;
import com.example.jcurrencyapp.data.parser.JsonParser;
import com.example.jcurrencyapp.exceptions.ConverterException;
import com.example.jcurrencyapp.model.Rate;

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

	@Override
	public List<Rate> getRates(String data) {
		List<Rate> rates = new ArrayList<Rate>();

		JsonParser<NbpCurrency> parser = new JsonParser<NbpCurrency>(NbpCurrency.class);

		try {
			Optional<NbpCurrency> currency = parser.deserialize(data);
			if (currency.isPresent()) {
				for (NbpRate rate : currency.get().getRates()) {
					rates.add(new Rate(currency.get().getCode(), rate.getEffectiveDate(), rate.getAsk()));
				}
			}
		} catch (Exception e) {
			throw new ConverterException("Can't get rate for input data: " + e.getMessage(), e);
		}

		return rates;
	}
}
