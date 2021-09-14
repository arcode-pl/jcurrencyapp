package com.example.jcurrencyapp.controller;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

class WebApiControllerTest {

	@Test
	void testReadApi() {
		String test = "This is test";
		
		assertThat(test).isEqualTo("This is test");
	}

}
