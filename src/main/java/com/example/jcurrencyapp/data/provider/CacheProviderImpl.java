package com.example.jcurrencyapp.data.provider;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.example.jcurrencyapp.data.converter.Converter;
import com.example.jcurrencyapp.model.CurrencyTypes;
import com.example.jcurrencyapp.model.Rate;

public class CacheProviderImpl implements Provider {

	static Map<Rate, BigDecimal> memory = new HashMap<Rate, BigDecimal>();

	@Override
	public BigDecimal getRate(CurrencyTypes code, LocalDate date) {
		return memory.get(new Rate(code, date));
	}

	@Override
	public void saveRate(Rate rate) {
		if (rate != null) {
			memory.put(rate, rate.getRate());
		}
	}
}
