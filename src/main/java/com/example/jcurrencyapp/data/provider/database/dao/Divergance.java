package com.example.jcurrencyapp.data.provider.database.dao;

import java.math.BigDecimal;

public class Divergance {
	private Currency currency;
	private BigDecimal divergance;

	public Divergance(Currency currency, BigDecimal divergance) {
		super();
		this.currency = currency;
		this.divergance = divergance;
	}

	public Currency getCurrency() {
		return currency;
	}

	public BigDecimal getDivergance() {
		return divergance;
	}

	@Override
	public String toString() {
		return "Divergance [currency=" + currency + ", divergance=" + divergance + "]";
	}
}
