package com.example.jcurrencyapp.data.provider.nbp.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.math.BigDecimal;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.example.jcurrencyapp.data.provider.nbp.converter.NbpJsonConverterImpl;
import com.example.jcurrencyapp.exceptions.ConverterException;

public class NbpJsonConverterImplTest {

	@BeforeClass
	public void init() {
		System.out.println("Testing: " + this.getClass().getName());
	}

	@Test
	public void shouldReturnMidPrice_WhenGivenSampleJsonStringFromNbp() {

		// Given
		NbpJsonConverterImpl converter = new NbpJsonConverterImpl();
		String data = "{\n" + "	\"table\":\"C\",\n" + "	\"currency\":\"euro\",\n" + "	\"code\":\"EUR\",\n"
				+ "	\"rates\":\n" + "	[{\n" + "		\"no\":\"178/C/NBP/2021\",\n" + "		\"effectiveDate\":\n"
				+ "		\"2021-09-14\",\n" + "		\"mid\":4.5922\n" + "	}]\n" + "}";

		// When
		BigDecimal rate = converter.getPrice(data);

		// Then
		assertThat(rate).isEqualTo(new BigDecimal("4.5922"));
	}

	@Test
	public void shouldThrownException_WhenGivenBrokenJsonStringFromNbp() {

		// Given
		NbpJsonConverterImpl converter = new NbpJsonConverterImpl();
		String data = "{\n" + "	\"table\":\"C\",\n" + "	\"currency\":\"euro\",\n" + "		\"bi*d\":4.5012,\n"
				+ "		\"ask\":4.5922\n" + "	}]\n" + "}";

		// When
		Throwable throwable = catchThrowable(() -> converter.getPrice(data));

		// Then
		assertThat(throwable).isInstanceOf(ConverterException.class)
				.hasMessageContaining("Can't get rate for input data: ");
	}

	@Test
	public void shouldThrownException_WhenGivenNullString() {
		// Given
		NbpJsonConverterImpl converter = new NbpJsonConverterImpl();
		String data = null;

		// When
		Throwable throwable = catchThrowable(() -> converter.getPrice(data));

		// Then
		assertThat(throwable).isInstanceOf(ConverterException.class)
				.hasMessageContaining("Can't get rate for input data: ");
	}
}
