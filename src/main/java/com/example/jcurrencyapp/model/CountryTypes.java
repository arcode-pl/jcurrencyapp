package com.example.jcurrencyapp.model;

import java.util.Arrays;
import java.util.List;

public enum CountryTypes {
	
	//Country with more than two currencies is very rare, so i think relation to currencies can be related on application side only.
	PL("Niemcy", Arrays.asList(CurrencyTypes.EUR)), 
	EN("Anglia", Arrays.asList(CurrencyTypes.GBP)), 
	US("Stany Zjednoczone", Arrays.asList(CurrencyTypes.USD)), 
	JP("Japonia", Arrays.asList(CurrencyTypes.JPY)), 
	CH("Chiny", Arrays.asList(CurrencyTypes.CNY)), 
	CZ("Czechy", Arrays.asList(CurrencyTypes.EUR)),
	SV("Salwador", Arrays.asList(CurrencyTypes.USD, CurrencyTypes.BTC));
	
	private String name;
	private List<CurrencyTypes> currencies;

	CountryTypes(String name, List<CurrencyTypes> currencies) {
		this.name = name;
		this.currencies = currencies;
	}
	
	public static CountryTypes getByCode(String code) {
		for (CountryTypes var : values()) {
			if (code.equals(var.toString())) {
				return var;
			}
		}

		return null;
	}
	
	public String getCode() {
		return this.toString();
	}
	
	public String getName() {
		return name;
	}
	
	public List<CurrencyTypes> getCurrencies() {
		return currencies;
	}
}
