package com.example.jcurrencyapp;

import static org.assertj.core.api.Assertions.assertThat;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.testng.annotations.Test;

import com.example.jcurrencyapp.model.CurrencyTypes;


public class CacheTest {

	@Test
	public void shouldReturnRate_WhenPutToTheCache() {
		// Given
		Cache cache = new Cache();
		CurrencyTypes code = CurrencyTypes.USD;
		LocalDate date = LocalDate.now();
		BigDecimal rate = new BigDecimal("1.23456789");

		// When
		cache.putRate(code, date, rate);
		BigDecimal result = cache.getRate(code, date);
		
		// Then
		assertThat(result).isEqualTo(rate);
	}

	@Test
	public void shouldReturnNull_WhenPutToTheCacheAndClear() {
		// Given
		Cache cache = new Cache();
		CurrencyTypes code = CurrencyTypes.USD;
		LocalDate date = LocalDate.now();
		BigDecimal rate = new BigDecimal("1.23456789");

		// When
		cache.putRate(code, date, rate);
		cache.clear();
		BigDecimal result = cache.getRate(code, date);
		
		// Then
		assertThat(result).isNull();
	}
}
