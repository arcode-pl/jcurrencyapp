package com.example.jcurrencyapp;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.jcurrencyapp.exceptions.ValidatorException;
import com.example.jcurrencyapp.model.CurrencyTypes;

public class Validator {
	
	public void validateInputs(CurrencyTypes code, BigDecimal quantity) {
		if (!isCodeValid(code)) {
			throw new ValidatorException("Code not valid");
		}
		
		if (!isCountValid(quantity)) {
			throw new ValidatorException("Quantity not valid");
		}
	}
	
	public boolean isCodeValid(CurrencyTypes code) {
		return code instanceof CurrencyTypes;
	}
	
	public boolean isCountValid(BigDecimal quantity) {
		return quantity instanceof BigDecimal;
	}
	
	public LocalDate fixDate(LocalDate date) {
		
		// Set date to today when ask for future or null
		if ( (date == null) || date.isAfter(LocalDate.now())) {
			date = LocalDate.now();
			//throw new ValidatorException("Date fixed to today");
		}
		
		return date;
	}
}
