package com.example.jcurrencyapp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.example.jcurrencyapp.data.provider.Provider;
import com.example.jcurrencyapp.exceptions.ExceptionHandler;
import com.example.jcurrencyapp.model.CurrencyTypes;
import com.example.jcurrencyapp.model.Rate;

public class JCurrency {

	private Validator validator;
	private Controller ctrl;

	public JCurrency() {
		this.validator = new Validator();
		this.ctrl = new Controller();
	}

	public JCurrency(List<Provider> providers) {
		this.validator = new Validator();
		this.ctrl = new Controller(providers);
	}

	public Config getConfig() {
		return this.ctrl.getConfig();
	}

	public void setConfig(Config config) {
		this.ctrl.setConfig(config);
	}

	public Optional<Rate> tryExchange(CurrencyTypes code, BigDecimal quantity, LocalDate date) {
		Optional<Rate> result = Optional.empty();
		
		try {
			validator.validateInputs(code, quantity);
			Rate rate = ctrl.getRate(code, validator.fixDate(date));
			result = Optional.of(new Rate(rate.getCode(), rate.getDate(), rate.getRate().multiply(quantity)));
		} catch (RuntimeException ex) {
			ExceptionHandler.handleException(ex);
		}
		
		return result;
	}
}
