package com.example.jcurrencyapp.io.webapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.time.LocalDate;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.example.jcurrencyapp.exceptions.WebApiException;
import com.example.jcurrencyapp.model.CurrencyTypes;

public class NbpWebApiRequestTest {

	@BeforeClass
	public void init() {
		System.out.println("Testing: " + this.getClass().getName());
	}

	@Test
	public void shouldReturnValidQuery_WhenGivenJsonOrXmlFormat() {
		// Given
		String jsonQueryValidate = "https://api.nbp.pl/api/exchangerates/rates/a/usd/2016-04-12/?format=json";
		String xmlQueryValidate = "https://api.nbp.pl/api/exchangerates/rates/a/usd/2016-04-12/?format=xml";

		// When
		String query = NbpWebApiRequest.getJsonQuery(CurrencyTypes.USD, LocalDate.of(2016, 4, 12));

		// Then
		assertThat(query).isEqualTo(jsonQueryValidate);
		
		// When
		query = NbpWebApiRequest.getXmlQuery(CurrencyTypes.USD, LocalDate.of(2016, 4, 12));

		// Then
		assertThat(query).isEqualTo(xmlQueryValidate);
	}

	@Test
	public void shouldThrownWebApiException_WhenGivenWrongInputs() {
		// Given
		
		// When 
		Throwable throwable = catchThrowable(() -> NbpWebApiRequest.getJsonQuery(null, null));
		
		// Then
		assertThat(throwable).isInstanceOf(WebApiException.class).hasMessageContaining("Can't get query:");
		
		// When 
		throwable = catchThrowable(() -> NbpWebApiRequest.getXmlQuery(null, null));
		
		// Then
		assertThat(throwable).isInstanceOf(WebApiException.class).hasMessageContaining("Can't get query:");
	}
}
