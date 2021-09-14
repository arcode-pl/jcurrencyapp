package com.example.jcurrencyapp;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import com.example.jcurrencyapp.data.model.CurrencyTypes;

public class AppTest {
	
	//TODO: 1. Add custom provider "TestProvider" loading known test data and check with known answers
	//		2. ThrowWrongProtocolExceptionWhenNotAvailable
	
	/*public Optional<Currency> calculate(AppController control, String code, BigDecimal count, LocalDate date) 
			throws WrongCurrencyCodeException {
		
		Optional<Currency> val = control.calculate(code, count, date);
		if(val.isPresent()) {
			System.out.format(date + " : %s " + val.get().getCode() + " => " + "%s PLN%n", count, val.get().getValue());
		} else {
		    System.out.println(date + " : not available within " + control.getConfig().getMaxBackDays());
		}
		assertThat(val.isPresent());
		
		return Optional.of(val.get());
	}

	@Test
	public void DefaultProviderTest() throws WrongCurrencyCodeException {
		System.out.println("DefaultProviderTest");
		
	    AppController control = new AppController();
	   
	    String code = CurrencyTypes.GBP;
	    BigDecimal count = new BigDecimal("10.1234");
	    LocalDate date = LocalDate.parse("2016-06-06");
	    
	    for (int i = 0 ; i < 10 ; i++) {
		    this.calculate(control, code, count, date);
		   
		    date = date.minusMonths(1);
		    count = count.subtract(new BigDecimal("0.1"));
	    }
   	}
	
	@Test
	public void CustomProviderTest() throws WrongCurrencyCodeException {
	    System.out.println("CustomProviderTest");
		
	    AppController control = new AppController();
	    TestDataProvider provider = new TestDataProvider();
	   
	    control.setCustomDataProvider("TestProvider", provider);
	   
	    String code = CurrencyTypes.USD;
	    BigDecimal count = new BigDecimal("10.1234");
	    LocalDate date = LocalDate.parse("2016-06-06");
	   
	    for (int i = 0 ; i < 10 ; i++) {
		    this.calculate(control, code, count, date);
		   
		    date = date.minusMonths(1);
		    count = count.subtract(new BigDecimal("0.1"));
	    }
   	}*/
}