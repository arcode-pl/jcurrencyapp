package com.example.jcurrencyapp.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Rate {
	BigDecimal rate;
	LocalDate date;

	public Rate(BigDecimal rate, LocalDate date) {
		this.rate = rate;
		this.date = date;
	}
	
	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

}
