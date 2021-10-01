package com.example.jcurrencyapp.data.provider;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.testng.annotations.Test;

import com.example.jcurrencyapp.Demo;
import com.example.jcurrencyapp.model.CurrencyTypes;
import com.example.jcurrencyapp.model.Rate;


public class DatabaseProviderImplTest {
	
	@Test
	public void shouldGetRateForGivenCodeAndDateAndRate() {
		//given
		DatabaseProviderImpl provider = new DatabaseProviderImpl();
		CurrencyTypes code = CurrencyTypes.USD;
		LocalDate date = LocalDate.now();
		BigDecimal rate = new BigDecimal("1.23456789");
		
		Rate input = new Rate(code, date, rate);
		
		//when
		Demo.initCountries();
		provider.saveRate(input);
		//BigDecimal result = provider.getRate(code, date);
		
		//then
		//assertThat(result).isEqualTo(input.getRate());
	}

	/*@Test
	public void getRatesTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void saveRateTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void saveRatesTest() {
		throw new RuntimeException("Test not implemented");
	}*/
}
