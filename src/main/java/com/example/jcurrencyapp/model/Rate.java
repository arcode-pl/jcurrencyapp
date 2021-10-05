package com.example.jcurrencyapp.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Rate {
	BigDecimal price;
	LocalDate date;
	CurrencyTypes currency;

	public Rate(CurrencyTypes currency, LocalDate date, BigDecimal price) {
		this.currency = currency;
		this.date = date;
		this.price = price;
	}

	public Rate(CurrencyTypes currency, LocalDate date) {
		this.currency = currency;
		this.date = date;
	}

	public Rate(String code, String date, Double price) {
		this.currency = CurrencyTypes.getByCode(code);
		this.date = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
		this.price = BigDecimal.valueOf(price);
	}

	public CurrencyTypes getCurrency() {
		return currency;
	}
	
	public LocalDate getDate() {
		return date;
	}

	public BigDecimal getPrice() {
		return price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(currency, date);
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
		return currency.equals(other.currency) && date.equals(other.date);
	}

	@Override
	public String toString() {
		return "Rate [rate=" + price + ", date=" + date + ", currency=" + currency + "]";
	}
}
