package com.example.jcurrencyapp.data.provider;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.jcurrencyapp.model.CurrencyTypes;
import com.example.jcurrencyapp.model.Rate;

public class DatabaseProviderImpl implements Provider {

	@Override
	public BigDecimal getRate(CurrencyTypes code, LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveRate(Rate rate) {
		// TODO Auto-generated method stub
		
	}

}
