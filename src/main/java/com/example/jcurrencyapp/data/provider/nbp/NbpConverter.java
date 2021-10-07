package com.example.jcurrencyapp.data.provider.nbp;

import java.math.BigDecimal;
import java.util.List;

import com.example.jcurrencyapp.model.Rate;

//Returns currency rate selected by code and value
public interface NbpConverter {
	public BigDecimal getPrice(String data);
	public List<Rate> getRates(String data);
}
