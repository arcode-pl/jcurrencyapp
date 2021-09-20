package com.example.jcurrencyapp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import com.example.jcurrencyapp.ctrl.ConverterCtrl;
import com.example.jcurrencyapp.ctrl.ProviderCtrl;
import com.example.jcurrencyapp.ctrl.Validator;
import com.example.jcurrencyapp.data.converter.IConverter;
import com.example.jcurrencyapp.data.provider.IProvider;
import com.example.jcurrencyapp.exceptions.ExceptionHandler;
import com.example.jcurrencyapp.model.CurrencyTypes;

public class Controller {

	private Config config;
	private Validator validator;
	private ProviderCtrl provider;
	private ConverterCtrl converter;
	
	public Controller() {
		this.config = new Config();
		this.validator = new Validator();
		this.provider = new ProviderCtrl();
		this.converter = new ConverterCtrl();
	}
	
	public Controller(IProvider provider, IConverter converter) {
		this.config = new Config();
		this.validator = new Validator();
		this.provider = new ProviderCtrl(provider);
		this.converter = new ConverterCtrl(converter);
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	public Optional<BigDecimal> exchange(CurrencyTypes code, BigDecimal quantity, LocalDate date) {
		
		try {
			validator.validateInputs(code, quantity);
			String data = provider.getData(
					code, 
					validator.fixDate(date), 
					config.getMaxBackDays());
			BigDecimal rate = converter.getRate(data);				
			return Optional.of(quantity.multiply(rate));
		} catch (RuntimeException ex) {
			ExceptionHandler.handleException(ex);
		}
		
		return Optional.empty();
	}
}
