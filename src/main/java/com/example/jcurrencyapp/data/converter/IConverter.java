package com.example.jcurrencyapp.data.converter;

import java.math.BigDecimal;

//Returns currency rate selected by code and value
public interface IConverter {
	public BigDecimal getRate(String data);
}
