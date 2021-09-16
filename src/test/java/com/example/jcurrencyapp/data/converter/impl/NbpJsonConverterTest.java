package com.example.jcurrencyapp.data.converter.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;

import org.testng.annotations.Test;

import com.example.jcurrencyapp.exceptions.ConverterException;

public class NbpJsonConverterTest {

	@Test
	public void getRateTest_GivenSampleJsonStringFromNbp_WhenCall_ReturnAskPrice() {
		String data = "{\n"
				+ "	\"table\":\"C\",\n"
				+ "	\"currency\":\"euro\",\n"
				+ "	\"code\":\"EUR\",\n"
				+ "	\"rates\":\n"
				+ "	[{\n"
				+ "		\"no\":\"178/C/NBP/2021\",\n"
				+ "		\"effectiveDate\":\n"
				+ "		\"2021-09-14\",\n"
				+ "		\"bid\":4.5012,\n"
				+ "		\"ask\":4.5922\n"
				+ "	}]\n"
				+ "}";
		
		NbpJsonConverter converter = new NbpJsonConverter();
		BigDecimal rate = converter.getRate(data);
		
		assertThat(rate).isEqualTo(new BigDecimal("4.5922"));
	}
	
	@Test
	public void getRateTest_GivenBrokenJsonStringFromNbp_WhenCall_ShouldThrownConverterExceptionAndReturnNull() {
		String data = "{\n"
				+ "	\"table\":\"C\",\n"
				+ "	\"currency\":\"euro\",\n"
				+ "		\"bi*d\":4.5012,\n"
				+ "		\"ask\":4.5922\n"
				+ "	}]\n"
				+ "}";
		
		assertThatThrownBy(() -> {
			assertThat(new NbpJsonConverter().getRate(data)).isNull();
		}).isInstanceOf(ConverterException.class)
		  .hasMessageContaining("Can't get rate for input data: ");
		
		assertThatThrownBy(() -> {
			assertThat(new NbpJsonConverter().getRate(null)).isNull();
		}).isInstanceOf(ConverterException.class)
		  .hasMessageContaining("Can't get rate for input data: ");
	}
}
