package com.example.jcurrencyapp.io.webapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.example.jcurrencyapp.exceptions.WebApiException;
import com.example.jcurrencyapp.io.webapi.model.WebApiResponse;

public class WebApiControllerTest {

	@BeforeClass
	public void init() {
		System.out.println("Testing: " + this.getClass().getName());
	}
	
	@Test
	public void shoulReturnStatusCode200_WhenCallValidWebApiUrl() {
		// Given
		String apiUrl = "https://api.nbp.pl/api/exchangerates/rates/c/usd/2016-04-12/?format=json";
		
		// When
		WebApiResponse response = WebApiController.tryReadApi(apiUrl);

		// Then
		assertThat(response.getCode()).isEqualTo(200);		
	}
	
	@Test
	public void shoulReturnStatusCodeDifferentThan200_WhenCallWrongWebApiUrl() {
		// Given
		String apiUrl = "https://api.nbp.pl/api/";
		
		// When
		WebApiResponse response = WebApiController.tryReadApi(apiUrl);

		// Then
		assertThat(response.getCode()).isNotEqualTo(200);		
	}
	
	@Test
	public void shoulThrownException_WhenCallWebApiUrlWithoutUrl() {
		// Given
		String apiUrl = null;
		
		// When
		Throwable throwable = catchThrowable(() -> WebApiController.tryReadApi(apiUrl));

		// Then
		assertThat(throwable).isInstanceOf(WebApiException.class).hasMessageContaining("Can't read api:");
	}
}
