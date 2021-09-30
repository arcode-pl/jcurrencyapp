package com.example.jcurrencyapp.model;

public enum CurrencyTypes {
	THB, USD, AUD, HKD, CAD, NZD, SGD, EUR,
	HUF, CHF, GBP, UAH, JPY, CZK, DKK, ISK,
	NOK, SEK, HRK, RON, BGN, TRY, ILS, CLP,
	PHP, MXN, ZAR, BRL, MYR, RUB, IDR, INR,
	KRW, CNY, XDR;
	
	public static CurrencyTypes get(String code) {
		for (CurrencyTypes var : CurrencyTypes.values()) {
			if (code.equals(var.toString())) {
				return var;
			}
		}
		
		return null;
	}
}
