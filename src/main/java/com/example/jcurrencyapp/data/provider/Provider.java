package com.example.jcurrencyapp.data.provider;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.jcurrencyapp.model.CurrencyTypes;
import com.example.jcurrencyapp.model.Rate;

//Returns String from selected source
public interface Provider {
	public BigDecimal getRate(CurrencyTypes code, LocalDate date);
	public void saveRate(Rate rate);
}
