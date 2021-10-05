package com.example.jcurrencyapp.data.provider.cache;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.example.jcurrencyapp.data.provider.cache.CacheProviderImpl;
import com.example.jcurrencyapp.model.CurrencyTypes;
import com.example.jcurrencyapp.model.Rate;

public class CacheProviderImplTest {

	@BeforeClass
	public void init() {
		System.out.println("Testing: " + this.getClass().getName());
	}

	@Test
	public void shouldReadWritedValueFromCache() {
		// Given
		
		CacheProviderImpl cache = new CacheProviderImpl();
		Rate rate = new Rate(CurrencyTypes.USD, LocalDate.of(2016, 4, 12), new BigDecimal("1.23456789"));
		BigDecimal response;
		
		// When
		cache.saveRate(rate);
		response = cache.getPrice(rate.getCurrency(), rate.getDate());

		// Then
		assertThat(response).isEqualTo(rate.getPrice());
	}
}
