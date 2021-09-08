package main;

import java.math.BigDecimal;

import exceptions.WrongCurrencyCodeException;
import exceptions.WrongProtocolException;

public class App {
	
	public static void main(String[] args) throws WrongCurrencyCodeException, WrongProtocolException {

		AppConfig config = new AppConfig();
		AppController control = new AppController(config);
		
		BigDecimal val = control.calculate("EUR", BigDecimal.valueOf(10.123));
		
		System.out.format("value: %s%n", val.toPlainString());
		
        return;
	}
}
