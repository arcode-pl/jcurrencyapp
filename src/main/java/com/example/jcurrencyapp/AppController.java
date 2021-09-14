package com.example.jcurrencyapp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import com.example.jcurrencyapp.data.converter.AppConverter;
import com.example.jcurrencyapp.data.converter.impl.NbpJsonConverter;
import com.example.jcurrencyapp.data.model.CurrencyTypes;
import com.example.jcurrencyapp.data.provider.AppProvider;
import com.example.jcurrencyapp.data.provider.impl.NbpJsonProvider;
import com.example.jcurrencyapp.exceptions.ConverterException;
import com.example.jcurrencyapp.exceptions.InputsNotValidException;
import com.example.jcurrencyapp.exceptions.ProviderException;
import com.example.jcurrencyapp.exceptions.ReadApiException;

public class AppController {
	
	AppConfig config;
	AppProvider provider;
	AppConverter converter;
	
	public AppController() {
		this.config = new AppConfig();
		this.provider = new NbpJsonProvider();
		this.converter = new NbpJsonConverter();
	}
	
	public AppConfig getConfig() {
		return config;
	}

	public void setConfig(AppConfig config) {
		this.config = config;
	}
	
	public AppProvider getProvider() {
		return provider;
	}

	public void setProvider(AppProvider provider) {
		this.provider = provider;
	}

	public AppConverter getConverter() {
		return converter;
	}

	public void setConverter(AppConverter converter) {
		this.converter = converter;
	}

	public void setCustom(AppProvider provider, AppConverter converter) {
		this.setProvider(provider);
		this.setConverter(converter);
	}

	private void validateInputs(CurrencyTypes code, BigDecimal count, LocalDate date) throws InputsNotValidException {
		if (code == null || count == null || date == null) {
			throw new InputsNotValidException("in function validateInputs");
		}
		
		// Set date to today when ask for future
		if (date.isAfter(LocalDate.now())) {
			date = LocalDate.now();
		}
	}
	
	private Optional<String> getDataWithBackLoop(CurrencyTypes code, LocalDate date, int maxBackDays) throws ReadApiException {
		int retryCnt = 0;
		boolean valid = false;
		Optional<String> data;
		
		//Loop back if not tables
		while (!valid && retryCnt < maxBackDays)
		{
			data = provider.getData(code.toString(), date);
			if (data.isPresent()) {
				return data;
			}
			
			date = date.minusDays(1);
			retryCnt++;
		}
				
		return Optional.empty();
	}
	
	private Optional<BigDecimal> calculate(BigDecimal count, BigDecimal rate) {
		return Optional.of(count.multiply(rate));
	}
	
	public Optional<BigDecimal> exchange(CurrencyTypes code, BigDecimal count, LocalDate date) 
			throws ProviderException, ConverterException, InputsNotValidException, ReadApiException {
		Optional<BigDecimal> rate = Optional.empty();
		
		// Validate inputs
		validateInputs(code, count, date);
		
		// Get data
		Optional<String> data = getDataWithBackLoop(code, date, config.getMaxBackDays());
		
		// Get rate
		if (data.isPresent()) {
			rate = converter.getRate(data.get());
		}
		
		// Calculate
		if (rate.isPresent()) {
			return calculate(count, rate.get());
		}
		
		return rate;
	}
}
