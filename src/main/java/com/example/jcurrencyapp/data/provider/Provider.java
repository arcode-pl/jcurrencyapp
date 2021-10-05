package com.example.jcurrencyapp.data.provider;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.example.jcurrencyapp.model.CurrencyTypes;
import com.example.jcurrencyapp.model.Rate;

//Returns String from selected source
public interface Provider {
	public BigDecimal getPrice(CurrencyTypes code, LocalDate date);
	public void saveRate(Rate rate);
	
	public List<Rate> getRates(CurrencyTypes code, LocalDate startDate, LocalDate endDate);
	public void saveRates(List<Rate> rates);
}
