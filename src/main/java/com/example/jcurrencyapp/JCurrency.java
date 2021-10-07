package com.example.jcurrencyapp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.example.jcurrencyapp.data.provider.Provider;
import com.example.jcurrencyapp.exceptions.ExceptionHandler;
import com.example.jcurrencyapp.model.CountryTypes;
import com.example.jcurrencyapp.model.CurrencyTypes;
import com.example.jcurrencyapp.model.Rate;

public class JCurrency {

	private Validator validator;
	private Controller controller;

	public JCurrency() {
		this.validator = new Validator();
		this.controller = new Controller();
	}

	public JCurrency(List<Provider> providers) {
		this.validator = new Validator();
		this.controller = new Controller(providers);
	}

	public Config getConfig() {
		return this.controller.getConfig();
	}

	public void setConfig(Config config) {
		this.controller.setConfig(config);
	}

	public Optional<Rate> tryExchange(CurrencyTypes currency, BigDecimal quantity, LocalDate date) {
		Optional<Rate> result = Optional.empty();
		
		try {
			validator.validateInputs(currency, quantity);
			Rate rate = controller.getPrice(currency, validator.fixDate(date));
			result = Optional.of(new Rate(rate.getCurrency(), rate.getDate(), rate.getPrice().multiply(quantity)));
		} catch (RuntimeException ex) {
			ExceptionHandler.handleException(ex);
		}
		
		return result;
	}
	
	public Optional<CurrencyTypes> getMostUnstableCurrency(LocalDate startDate, LocalDate endDate) {
		return Optional.ofNullable(controller.getMostUnstableCurrency(startDate, endDate));
	}
	
	public Optional<Rate> getMaxRateInDateRange(CurrencyTypes currency, LocalDate startDate, LocalDate endDate) {
		return Optional.ofNullable(controller.getMaxRateInDateRange(currency, startDate, endDate));
	}
	
	public Optional<Rate> getMinRateInDateRange(CurrencyTypes currency, LocalDate startDate, LocalDate endDate) {
		return Optional.ofNullable(controller.getMinRateInDateRange(currency, startDate, endDate));
	}
	
	public Optional<List<Rate>> getBestAskRates(CurrencyTypes currency, long quantity) {
		return Optional.ofNullable(controller.getBestAskRates(currency, quantity));
	}
	
	public Optional<List<Rate>> getBestBidRates(CurrencyTypes currency, long quantity) {
		return Optional.ofNullable(controller.getBestBidRates(currency, quantity));
	}
	
	public Optional<CountryTypes> findCountriesWithMultipleCurrency() {
		return Optional.ofNullable(controller.findCountriesWithMultipleCurrency());
	}
}
