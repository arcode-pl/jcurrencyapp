package com.example.jcurrencyapp.data.converter;

import java.math.BigDecimal;

import com.example.jcurrencyapp.model.FileTypes;

//Returns currency rate selected by code and value
public interface Converter {
	public BigDecimal getRate(String data);
}
