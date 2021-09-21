package com.example.jcurrencyapp.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Rate {
	BigDecimal rate;
	LocalDate date;
	CurrencyTypes code;

	public Rate(CurrencyTypes code, LocalDate date, BigDecimal rate) {
		this.code = code;
		this.date = date;
		this.rate = rate;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public LocalDate getDate() {
		return date;
	}

	public CurrencyTypes getCode() {
		return code;
	}
}
