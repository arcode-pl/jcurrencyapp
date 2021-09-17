package com.example.jcurrencyapp.io.webapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.example.jcurrencyapp.exceptions.AppException;

//TODO: Update to given when then

public class NbpWebApiRequestTest {

	@BeforeClass
	public void init() {
		System.out.println("Testing: " + this.getClass().getName());
	}
	
	@Test
	public void getSimpleQueryTest_GivenJsonFormat_WhenCall_ShouldReturnValidQuery() {
		
		String validateQuery = "https://api.nbp.pl/api/exchangerates/rates/c/usd/2016-04-12/?format=json";
		
		String query = new NbpWebApiRequest().getSimpleQuery("USD", LocalDate.of(2016, 4, 12), false);
		
		assertThat(query).isEqualTo(validateQuery);
	}
	
	@Test
	public void getSimpleQueryTest_GivenXmlFormat_WhenCall_ShouldReturnValidQuery() {
		
		String validateQuery = "https://api.nbp.pl/api/exchangerates/rates/c/usd/2016-04-12/?format=xml";
		String query = new NbpWebApiRequest().getSimpleQuery("USD", LocalDate.of(2016, 4, 12), true);
		
		assertThat(query).isEqualTo(validateQuery);
	}

	@Test
	public void getSimpleQueryTest_GivenWrongInputs_WhenCall_ShouldThrownWebApiExceptionAndReturnEmptyString() {
		assertThatThrownBy(() -> {
			assertThat(new NbpWebApiRequest().getSimpleQuery(null, null, true)).isEmpty();
		}).isInstanceOf(AppException.class)
		  .hasMessageContaining("Can't getSimpleQuery:");
	}
}
