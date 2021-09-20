package com.example.jcurrencyapp.ctrl;

import java.math.BigDecimal;

import com.example.jcurrencyapp.data.converter.IConverter;
import com.example.jcurrencyapp.data.converter.impl.NbpJsonConverter;

public class ConverterCtrl {
	private IConverter converter;
	
	public ConverterCtrl() {
		this.converter = new NbpJsonConverter();
	}
	
	public ConverterCtrl(IConverter converter) {
		this.converter = converter;
	}

	public IConverter getConverter() {
		return converter;
	}

	public void setConverter(IConverter converter) {
		this.converter = converter;
	}

	public BigDecimal getRate(String data) {
		return converter.getRate(data);		
	}
}
