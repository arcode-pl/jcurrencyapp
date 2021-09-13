package com.example.jcurrencyapp.data.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Currency {
	BigDecimal value;
	String code;
	LocalDate date;
	
	public Currency() {
		super();
	}
	
	public Currency(BigDecimal rate, String code, LocalDate date) {
		super();
		this.value = rate;
		this.code = code;
		this.date = date;
	}
	
	public Currency(double rate, String code, LocalDate date) {
		super();
		this.value = BigDecimal.valueOf(rate);
		this.code = code;
		this.date = date;
	}

	//TODO: use only for development, remove it in the future
	public static Currency fakeDataModel() {
		return new Currency(new BigDecimal("3.84"), "USD", LocalDate.now());
	}
	
	public BigDecimal getValue() {
		return value;
	}
	
	public void setValue(BigDecimal value) {
		this.value = value;
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

	@Override
	public String toString() {
		return "Currency [value=" + value + ", code=" + code + ", date=" + date + "]";
	}

	
}
