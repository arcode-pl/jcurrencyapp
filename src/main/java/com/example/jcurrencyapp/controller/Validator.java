package com.example.jcurrencyapp.controller;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.jcurrencyapp.model.CurrencyTypes;

public class Validator {
	
	public boolean inputsValid(CurrencyTypes code, BigDecimal count, LocalDate date) {
		if (code == null || count == null || date == null) {
			return false;
		}
		
		return true;
	}
	
	public LocalDate dateValid(LocalDate date) {
		// Set date to today when ask for future
		if (date.isAfter(LocalDate.now())) {
			date = LocalDate.now();
		}
		
		return date;
	}
}
