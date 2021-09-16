package com.example.jcurrencyapp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import com.example.jcurrencyapp.controller.Logic;
import com.example.jcurrencyapp.controller.Validator;
import com.example.jcurrencyapp.data.converter.IConverter;
import com.example.jcurrencyapp.data.provider.IProvider;
import com.example.jcurrencyapp.exceptions.ExceptionHandler;
import com.example.jcurrencyapp.model.CurrencyTypes;

public class Controller {

	private Config config;
	private Validator validator;
	private Logic logic;
	
	public Controller() {
		this.config = new Config();
		this.validator = new Validator();
		this.logic = new Logic();
	}
	
	public Controller(IProvider provider, IConverter converter) {
		this.config = new Config();
		this.validator = new Validator();
		this.logic = new Logic(provider, converter);
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	public IProvider getProvider() {
		return logic.getProvider();
	}

	public void setProvider(IProvider provider) {
		logic.setProvider(provider);
	}

	public IConverter getConverter() {
		return logic.getConverter();
	}

	public void setConverter(IConverter converter) {
		this.logic.setConverter(converter);
	}

	public void setCustom(IProvider provider, IConverter converter) {
		this.setProvider(provider);
		this.setConverter(converter);
	}

	public Optional<BigDecimal> exchange(CurrencyTypes code, BigDecimal count, LocalDate date) {
		
		try {
			validator.validateInputs(code, count);
			String data = logic.getDataWithBackLoop(
					code, 
					validator.fixDate(date), 
					config.getMaxBackDays());
			BigDecimal rate = logic.getRate(data);				
			return Optional.of(count.multiply(rate));
		} catch (Exception ex) {
			ExceptionHandler.handleException(ex);
		}
		
		return Optional.empty();
	}
}
