package main;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import exceptions.WrongCurrencyCodeException;
import exceptions.WrongProtocolException;

public class App {
	
	public static void main(String[] args) throws WrongCurrencyCodeException, WrongProtocolException {

		AppConfig config = new AppConfig();
		AppController control = new AppController(config);
		
		Optional<BigDecimal> val = control.calculate("EUR", BigDecimal.valueOf(10.123), LocalDate.now());
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
