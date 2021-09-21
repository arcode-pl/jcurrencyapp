package com.example.jcurrencyapp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.example.jcurrencyapp.data.provider.IProvider;
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

	public JCurrency(List<IProvider> providers) {
		this.validator = new Validator();
		this.ctrl = new Controller(providers);
	}

	public Config getConfig() {
		return this.ctrl.getConfig();
	}

	public void setConfig(Config config) {
		this.ctrl.setConfig(config);
	}
	
	//clear cache? force read?

	public Optional<Rate> exchange(CurrencyTypes code, BigDecimal quantity, LocalDate date) {

		try {
			validator.validateInputs(code, quantity);
			Rate rate = ctrl.getRate(code, validator.fixDate(date));
			rate.setRate(quantity.multiply(rate.getRate()));
			return Optional.of(rate);
		} catch (RuntimeException ex) {
			ExceptionHandler.handleException(ex);
		}

		return Optional.empty();
	}
}
