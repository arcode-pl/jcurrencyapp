package com.example.jcurrencyapp.io.webapi;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.example.jcurrencyapp.io.webapi.model.WebApiResponse;

//TODO: Update to given when then

public class WebApiControllerTest {

	@BeforeClass
	public void init() {
		System.out.println("Testing: " + this.getClass().getName());
	}
	
	@Test
	public void shoulReturnStatusCode200_WhenCallWebApi() {
		// Given
		WebApiController webApi = new WebApiController();
		String apiUrl = "https://api.nbp.pl/api/exchangerates/rates/c/usd/2016-04-12/?format=json";
		
		// When
		WebApiResponse response = webApi.readApi(apiUrl);

		// Then
		assertThat(response.getCode()).isEqualTo(200);
	}
}
