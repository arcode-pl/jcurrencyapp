package com.example.jcurrencyapp.data.provider;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.jcurrencyapp.model.CurrencyTypes;
import com.example.jcurrencyapp.model.Rate;

public class CacheProviderImpl implements Provider {

	Map<Rate, BigDecimal> memory = new HashMap<Rate, BigDecimal>();

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

	@Override
	public List<Rate> getRates(CurrencyTypes code, LocalDate startDate, LocalDate endDate) {
		// TODO Auto-generated method stub
		return null;
	}
}
