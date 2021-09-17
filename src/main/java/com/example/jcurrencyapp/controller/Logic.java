package com.example.jcurrencyapp.controller;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.jcurrencyapp.data.converter.IConverter;
import com.example.jcurrencyapp.data.converter.impl.NbpJsonConverter;
import com.example.jcurrencyapp.data.provider.IProvider;
import com.example.jcurrencyapp.data.provider.impl.NbpJsonProvider;
import com.example.jcurrencyapp.model.CurrencyTypes;

/**
 * @author emil.arkita
 *
 */
public class Logic {
	private IProvider provider;
	private IConverter converter;
	
	public Logic() {
		this.provider = new NbpJsonProvider();
		this.converter = new NbpJsonConverter();
	}
	
	public Logic(IProvider provider, IConverter converter) {
		this.provider = provider;
		this.converter = converter;
	}
	
	public IProvider getProvider() {
		return provider;
	}

	public void setProvider(IProvider provider) {
		this.provider = provider;
	}

	public IConverter getConverter() {
		return converter;
	}

	public void setConverter(IConverter converter) {
		this.converter = converter;
	}

	public String getDataWithBackLoop(CurrencyTypes code, LocalDate date, int maxBackDays) {
		int retryCnt = 0;
		String data = null;

		// Loop until valid data or max days reached
		while ( (( data = provider.getData(code.toString(), date) ) == null) && 
				retryCnt < maxBackDays) {
			date = date.minusDays(1);
			retryCnt++;
		}

		return data;
	}

	public BigDecimal getRate(String data) {
		return converter.getRate(data);		
	}

}
