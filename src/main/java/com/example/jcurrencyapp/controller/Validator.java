package com.example.jcurrencyapp.controller;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.jcurrencyapp.exceptions.ValidatorException;
import com.example.jcurrencyapp.model.CurrencyTypes;

public class Validator {
	
	public void validateInputs(CurrencyTypes code, BigDecimal count) {
		if (!isCodeValid(code)) {
			throw new ValidatorException("Code not valid");
		}
		
		if (!isCountValid(count)) {
			throw new ValidatorException("Count not valid");
		}
	}
	
	public boolean isCodeValid(CurrencyTypes code) {
		return code instanceof CurrencyTypes;
	}
	
	public boolean isCountValid(BigDecimal count) {
		return count instanceof BigDecimal;
	}
	
	public LocalDate fixDate(LocalDate date) {
		
		// Set date to today when ask for future or null
		if ( (date == null) || date.isAfter(LocalDate.now())) {
			date = LocalDate.now();
		}
		
		return date;
	}
}
