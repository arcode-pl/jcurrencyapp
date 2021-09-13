package com.example.jcurrencyapp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import com.example.jcurrencyapp.data.model.Currency;
import com.example.jcurrencyapp.data.model.CurrencyCodes;
import com.example.jcurrencyapp.data.provider.ProviderFactory;
import com.example.jcurrencyapp.data.provider.ProviderInterface;
import com.example.jcurrencyapp.exceptions.WrongCurrencyCodeException;
import com.example.jcurrencyapp.exceptions.WrongProtocolException;
import com.example.jcurrencyapp.exceptions.WrongProviderException;

public class AppController {
	
	AppConfig config;
	ProviderFactory factory;
	
	public AppController() {
		this.config = new AppConfig();
		this.factory = new ProviderFactory();
	}
	
	public AppController(AppConfig config) {
		this.config = config;
		this.factory = new ProviderFactory();
	}
	
	public AppConfig getConfig() {
		return config;
	}

	public void setConfig(AppConfig config) {
		this.config = config;
	}
	
	public void setCustomDataProvider(String name, ProviderInterface iface) {
		factory.addCustomProvider(name, iface);
		config.setProvider(name);
	}
	
	private boolean inputsValid(String code, BigDecimal count, LocalDate date) throws WrongCurrencyCodeException {
		return CurrencyCodes.exist(code) && count != null && date != null;
	}
	
	//Ask for today exchange rates by default
	public Optional<Currency> calculate(String code, BigDecimal count) throws WrongCurrencyCodeException
	{
		return this.calculate(code, count, LocalDate.now());
	}
	
	public Optional<Currency> calculate(String code, BigDecimal count, LocalDate date) throws WrongCurrencyCodeException 
	{
		Optional<Currency> currency;
		
		// Check for null in input
		if (!inputsValid(code, count, date)) {
			return Optional.empty();
		}
		
		// Set date to today when ask for future
		if (date.isAfter(LocalDate.now())) {
			date = LocalDate.now();
		}
		
		// In this step we get currency model (rate, date, code) from provider.
		ProviderInterface provider = factory.getProvider(config.getProvider());
		if (provider != null) {
			
			// Try get previous day until maxBackDays
			int retryCnt = 0;
			while ((currency = provider.getRate(code, date)).isEmpty() && retryCnt < config.getMaxBackDays()) {
				date = date.minusDays(1);
				retryCnt++;
			}
			
			if (currency.isPresent()) {
				//System.out.println(currency.toString());
				count = count.multiply(currency.get().getValue());
				currency.get().setValue(count);
				return currency;
			}
		}
		
		return Optional.empty();
	}
}
