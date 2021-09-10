package com.example.jcurrencyapp;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.testng.annotations.Test;

import com.example.jcurrencyapp.exceptions.WrongCurrencyCodeException;
import com.example.jcurrencyapp.exceptions.WrongProtocolException;

public class AppTest {
	
	//TODO: 1. Add custom provider "TestProvider" loading known test data and check with known answers
	//		2. ThrowWrongProtocolExceptionWhenNotAvailable
	
	@Test
	public void AppMainTest() throws WrongCurrencyCodeException, WrongProtocolException {
	   String str = "TestNG is working fine";  
	   
	   AppConfig config = new AppConfig();
	   config.setProvider("TestProvider");
	   
	   
	   AppController control = new AppController(config);
	   
	   Optional<BigDecimal> val = control.calculate("EUR", BigDecimal.valueOf(10.1234), LocalDate.now());
		if(val.isPresent()) {
			System.out.format("iter 1 value: %s%n", val.get().toPlainString());
		} else {
			System.out.println("iter 1 not present");
		}
	   
		assertThat(str).isEqualTo("TestNG is working fine");
   	}
}