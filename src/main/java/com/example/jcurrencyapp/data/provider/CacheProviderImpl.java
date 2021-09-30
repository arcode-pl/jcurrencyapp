package com.example.jcurrencyapp.data.provider;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
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
		BigDecimal rate;
		List<Rate> rates = new ArrayList<Rate>();
		while (startDate.isBefore(endDate)) {
			rate = this.getRate(code, startDate);
			if (rate != null) {
				rates.add(new Rate(code, startDate, rate));
			}

			startDate = startDate.plusDays(1);
		}

		return rates;
	}

	@Override
	public void saveRates(List<Rate> rates) {
		if (rates != null) {
			for (Rate rate : rates) {
				this.saveRate(rate);
			}
		}
	}
}
