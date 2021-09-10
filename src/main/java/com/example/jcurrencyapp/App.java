package com.example.jcurrencyapp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import com.example.jcurrencyapp.data.mode.Currency;
import com.example.jcurrencyapp.data.provider.ProviderInterface;
import com.example.jcurrencyapp.exceptions.WrongCurrencyCodeException;
import com.example.jcurrencyapp.exceptions.WrongProtocolException;

public class App {
	
	//TODO: App object -> has controller -> has config
	//		App object -> selectCustomProvider(ProviderInterface<Currency>);
	//		App object -> selectProvider(ProviderTypes.xxx);
	
	AppConfig config = new AppConfig();
	AppController control = new AppController(config);
	
	public static void main(String[] args) throws WrongCurrencyCodeException, WrongProtocolException {

		AppConfig config = new AppConfig();
		AppController control = new AppController(config);
		
		Optional<BigDecimal> val = control.calculate("EUR", BigDecimal.valueOf(10.1234), LocalDate.now());
		if(val.isPresent()) {
			System.out.format("iter 1 value: %s%n", val.get().toPlainString());
		} else {
			System.out.println("iter 1 not present");
		}
		
		
		val = control.calculate("EUR", BigDecimal.valueOf(230.123), LocalDate.now());
		if(val.isPresent()) {
			System.out.format("iter 2 value: %s%n", val.get().toPlainString());
		} else {
			System.out.println("iter 2 not present");
		}
		
		
        return;
	}
}
