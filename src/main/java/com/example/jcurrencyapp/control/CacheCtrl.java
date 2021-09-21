package com.example.jcurrencyapp.control;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.example.jcurrencyapp.model.CurrencyTypes;

public class CacheCtrl {

	private Map<String, BigDecimal> cache = new HashMap<String, BigDecimal>();
	
	public void putRate(CurrencyTypes code, LocalDate date, BigDecimal rate) {
		cache.put(createKey(code, date), rate);
	}
	
	public BigDecimal getRate(CurrencyTypes code, LocalDate date) {
		return cache.get(createKey(code, date));
	}
	
	public void clear() {
		this.cache.clear();
	}
	
	private String createKey(CurrencyTypes code, LocalDate date) {
		return code.toString() + date.format(DateTimeFormatter.ISO_LOCAL_DATE);
	}
}
