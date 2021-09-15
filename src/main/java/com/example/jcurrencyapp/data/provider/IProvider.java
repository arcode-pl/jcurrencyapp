package com.example.jcurrencyapp.data.provider;

import java.time.LocalDate;

//Returns String from selected source
public interface IProvider {
	public String getData(String code, LocalDate date);
}
