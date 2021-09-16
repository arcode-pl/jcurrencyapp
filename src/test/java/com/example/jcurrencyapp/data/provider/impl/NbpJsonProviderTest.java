package com.example.jcurrencyapp.data.provider.impl;

import java.time.LocalDate;
import static org.assertj.core.api.Assertions.*;

import org.testng.annotations.Test;

public class NbpJsonProviderTest {

	@Test
	public void getDataTest_GivenNbpJsonProvider_WhenCall_ShouldGiveValidResponse() {
		String validResponse = "{\"table\":\"C\",\"currency\":\"dolar amerykański\",\"code\":\"USD\",\"rates\":[{\"no\":\"070/C/NBP/2016\",\"effectiveDate\":\"2016-04-12\",\"bid\":3.6949,\"ask\":3.7695}]}";
		NbpJsonProvider provider = new NbpJsonProvider();
		
		assertThat(provider.getData("USD", LocalDate.of(2016, 4, 12))).isEqualTo(validResponse);
	}
}
