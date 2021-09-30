package com.example.jcurrencyapp.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Rate {
	BigDecimal rate;
	LocalDate date;
	CurrencyTypes code;

	public Rate(CurrencyTypes code, LocalDate date, BigDecimal rate) {
		this.code = code;
		this.date = date;
		this.rate = rate;
	}
	
	public Rate(String code, LocalDate date, BigDecimal rate) {
		this.code = CurrencyTypes.get(code);
		this.date = date;
		this.rate = rate;
	}

	public Rate(CurrencyTypes code, LocalDate date) {
		this.code = code;
		this.date = date;
	}

	public Rate(String code, String date, Double rate) {
		this.code = CurrencyTypes.get(code);
		this.date = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
		this.rate = BigDecimal.valueOf(rate);
	}

	public CurrencyTypes getCode() {
		return code;
	}
	
	public LocalDate getDate() {
		return date;
	}

	public BigDecimal getRate() {
		return rate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, date);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rate other = (Rate) obj;
		return code.equals(other.code) && date.equals(other.date);
	}

	@Override
	public String toString() {
		return "Rate [rate=" + rate + ", date=" + date + ", code=" + code + "]";
	}
}
