package com.example.jcurrencyapp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import com.example.jcurrencyapp.data.model.Currency;
import com.example.jcurrencyapp.data.model.CurrencyTypes;
import com.example.jcurrencyapp.exceptions.WrongCurrencyCodeException;
import com.example.jcurrencyapp.exceptions.WrongProtocolException;

public class App {
	
	//TODO: App object -> has controller -> has config
	//		App object -> selectCustomProvider(ProviderInterface<Currency>);
	//		App object -> selectProvider(ProviderTypes.xxx);
	
	AppConfig config = new AppConfig();
	AppController control = new AppController(config);
	
	//Example usage of API
	public static void main(String[] args) throws WrongCurrencyCodeException, WrongProtocolException {

		AppController control = new AppController();
		
		Optional<Currency> val = control.calculate(CurrencyTypes.EUR, BigDecimal.valueOf(10.1234), LocalDate.now().minusDays(1));
		val.ifPresentOrElse(p -> p.toString(), () -> System.out.println("empty"));
		
		val = control.calculate(CurrencyTypes.GBP, BigDecimal.valueOf(230.123), LocalDate.now());
		val.ifPresentOrElse(p -> p.toString(), () -> System.out.println("empty"));
		
        return;
	}
}
