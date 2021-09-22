package com.example.jcurrencyapp.io.webapi;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NbpWebApiRequestTest {

	@BeforeClass
	public void init() {
		System.out.println("Testing: " + this.getClass().getName());
	}

	@Test
	public void shouldReturnValidQuery_WhenGivenJsonFormat() {
//		// Given
//		String validateQuery = "https://api.nbp.pl/api/exchangerates/rates/c/usd/2016-04-12/?format=json";
//		NbpWebApiRequest request = new NbpWebApiRequest();
//
//		// When
//		String query = request.getSimpleQuery(CurrencyTypes.USD, LocalDate.of(2016, 4, 12), false);
//
//		// Then
//		assertThat(query).isEqualTo(validateQuery);
	}

	@Test
	public void getSimpleQueryTest_GivenXmlFormat_WhenCall_ShouldReturnValidQuery() {
//		// Given
//		String validateQuery = "https://api.nbp.pl/api/exchangerates/rates/c/usd/2016-04-12/?format=xml";
//		NbpWebApiRequest request = new NbpWebApiRequest();
//
//		// When
//		String query = request.getSimpleQuery(CurrencyTypes.USD, LocalDate.of(2016, 4, 12), true);
//
//		// Then
//		assertThat(query).isEqualTo(validateQuery);
	}

	@Test
	public void shouldThrownWebApiException_WhenGivenWrongInputs() {
//		// Given
//		NbpWebApiRequest request = new NbpWebApiRequest();
//		CurrencyTypes code = CurrencyTypes.USD;
//		LocalDate date = null;
//		
//		// When 
//		Throwable throwable = catchThrowable(() -> request.getSimpleQuery(code, date, false));
//		
//		// Then
//		assertThat(throwable).isInstanceOf(AppException.class).hasMessageContaining("Can't getSimpleQuery:");
	}
}
