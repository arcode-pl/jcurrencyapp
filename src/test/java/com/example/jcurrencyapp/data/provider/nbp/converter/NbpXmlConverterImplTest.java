package com.example.jcurrencyapp.data.provider.nbp.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.math.BigDecimal;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.example.jcurrencyapp.data.provider.nbp.converter.NbpXmlConverterImpl;
import com.example.jcurrencyapp.exceptions.ConverterException;

public class NbpXmlConverterImplTest {

	@BeforeClass
	public void init() {
		System.out.println("Testing: " + this.getClass().getName());
	}

	@Test
	public void shouldReturnAskPrice_WhenGivenSampleXmlStringFromNbp() {
		// Given
		NbpXmlConverterImpl converter = new NbpXmlConverterImpl();
		String data = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"
				+ "<ExchangeRatesSeries xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n"
				+ "	<Table>C</Table>\n" + "	<Currency>dolar ameryka?ski</Currency>\n" + "	<Code>USD</Code>\n"
				+ "	<Rates>\n" + "		<Rate>\n" + "			<No>178/C/NBP/2021</No>\n"
				+ "			<EffectiveDate>2021-09-14</EffectiveDate>\n" + "			<Bid>3.8146</Bid>\n"
				+ "			<Ask>3.8916</Ask>\n" + "		</Rate>\n" + "	</Rates>\n" + "</ExchangeRatesSeries>";

		// When
		BigDecimal rate = converter.getRate(data);

		// Then
		assertThat(rate).isEqualTo(new BigDecimal("3.8916"));
	}

	@Test
	public void shouldThrownException_WhenGivenBrokenXmlStringFromNbp() {
		// Given
		NbpXmlConverterImpl converter = new NbpXmlConverterImpl();
		String data = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"
				+ "<ExchangeRatesSeries xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n"
				+ "	<Table>C</Table>\n" + "	<Code>USD</Code>\n" + "		<Rate>\n"
				+ "			<No>178/C/NBP/2021</No>\n" + "			<EffectiveDate>2021-09-14</EffectiveDate>\n"
				+ "			<Bid>3.8146</Bid>\n" + "			<Ask>3.8916</Ask>\n" + "		</Rate>\n"
				+ "</ExchangeRatesSeries>";

		// When
		Throwable throwable = catchThrowable(() -> converter.getRate(data));

		// Then
		assertThat(throwable).isInstanceOf(ConverterException.class)
				.hasMessageContaining("Can't get rate for input data: ");
	}
	
	@Test
	public void shouldThrownException_WhenGivenNullStringFromNbp() {
		// Given
		NbpXmlConverterImpl converter = new NbpXmlConverterImpl();
		String data = null;

		// When
		Throwable throwable = catchThrowable(() -> converter.getRate(data));

		// Then
		assertThat(throwable).isInstanceOf(ConverterException.class)
				.hasMessageContaining("Can't get rate for input data: ");
	}
}
