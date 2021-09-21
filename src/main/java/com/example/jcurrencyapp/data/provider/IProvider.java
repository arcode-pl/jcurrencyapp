package com.example.jcurrencyapp.data.provider;

import java.time.LocalDate;

import com.example.jcurrencyapp.data.converter.IConverter;
import com.example.jcurrencyapp.model.CurrencyTypes;
import com.example.jcurrencyapp.model.Rate;

//Returns String from selected source
public interface IProvider {
	public String getData(CurrencyTypes code, LocalDate date);

	public IConverter getConverter();
	
	public void setData(Rate rate);
}
