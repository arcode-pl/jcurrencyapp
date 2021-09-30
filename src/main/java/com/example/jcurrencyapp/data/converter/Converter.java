package com.example.jcurrencyapp.data.converter;

import java.math.BigDecimal;
import java.util.List;

import com.example.jcurrencyapp.model.Rate;

//Returns currency rate selected by code and value
public interface Converter {
	public BigDecimal getRate(String data);
	public List<Rate> getRates(String data);
}
