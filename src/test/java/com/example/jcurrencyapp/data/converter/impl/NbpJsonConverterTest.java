package com.example.jcurrencyapp.data.converter.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.math.BigDecimal;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.example.jcurrencyapp.exceptions.ConverterException;

public class NbpJsonConverterTest {

	@BeforeClass
	public void init() {
		System.out.println("Testing: " + this.getClass().getName());
	}

	@Test
	public void shouldReturnAskPrice_WhenGivenSampleJsonStringFromNbp() {

		// Given
		NbpJsonConverter converter = new NbpJsonConverter();
		String data = "{\n" + "	\"table\":\"C\",\n" + "	\"currency\":\"euro\",\n" + "	\"code\":\"EUR\",\n"
				+ "	\"rates\":\n" + "	[{\n" + "		\"no\":\"178/C/NBP/2021\",\n" + "		\"effectiveDate\":\n"
				+ "		\"2021-09-14\",\n" + "		\"bid\":4.5012,\n" + "		\"ask\":4.5922\n" + "	}]\n" + "}";

		// When
		BigDecimal rate = converter.getRate(data);

		// Then
		assertThat(rate).isEqualTo(new BigDecimal("4.5922"));
	}

	@Test
	public void shouldThrownException_WhenGivenBrokenJsonStringFromNbp() {

		// Given
		NbpJsonConverter converter = new NbpJsonConverter();
		String data = "{\n" + "	\"table\":\"C\",\n" + "	\"currency\":\"euro\",\n" + "		\"bi*d\":4.5012,\n"
				+ "		\"ask\":4.5922\n" + "	}]\n" + "}";

		// When
		Throwable throwable = catchThrowable(() -> converter.getRate(data));

		// Then
		assertThat(throwable).isInstanceOf(ConverterException.class)
				.hasMessageContaining("Can't get rate for input data: ");
	}

	@Test
	public void shouldThrownException_WhenGivenNullString() {
		// Given
		NbpJsonConverter converter = new NbpJsonConverter();
		String data = null;

		// When
		Throwable throwable = catchThrowable(() -> converter.getRate(data));

		// Then
		assertThat(throwable).isInstanceOf(ConverterException.class)
				.hasMessageContaining("Can't get rate for input data: ");
	}
}
