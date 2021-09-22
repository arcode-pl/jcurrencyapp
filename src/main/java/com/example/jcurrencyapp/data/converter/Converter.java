package com.example.jcurrencyapp.data.converter;

import java.math.BigDecimal;

//Returns currency rate selected by code and value
public interface Converter {
	public BigDecimal getRate(String data);
}
