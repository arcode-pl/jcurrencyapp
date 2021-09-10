package com.example.jcurrencyapp.data.mode;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Currency {
	BigDecimal rate;
	String code;
	LocalDate date;
	
	public Currency() {
		super();
	}
	
	public Currency(BigDecimal rate, String code, LocalDate date) {
		super();
		this.rate = rate;
		this.code = code;
		this.date = date;
	}

	public static Currency testDataModel() {
		return new Currency(new BigDecimal("3.84"), "USD", LocalDate.now());
	}
	
	public BigDecimal getRate() {
		return rate;
	}
	
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}

}
