package com.example.jcurrencyapp.data.converter.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.example.jcurrencyapp.exceptions.ConverterException;

public class NbpXmlConverterTest {

	@BeforeClass
	public void init() {
		System.out.println("Testing: " + this.getClass().getName());
	}
	
	@Test
	public void getRateTest_GivenSampleXmlStringFromNbp_WhenCall_ReturnAskPrice() {
		String data = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"
				+ "<ExchangeRatesSeries xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n"
				+ "	<Table>C</Table>\n"
				+ "	<Currency>dolar ameryka?ski</Currency>\n"
				+ "	<Code>USD</Code>\n"
				+ "	<Rates>\n"
				+ "		<Rate>\n"
				+ "			<No>178/C/NBP/2021</No>\n"
				+ "			<EffectiveDate>2021-09-14</EffectiveDate>\n"
				+ "			<Bid>3.8146</Bid>\n"
				+ "			<Ask>3.8916</Ask>\n"
				+ "		</Rate>\n"
				+ "	</Rates>\n"
				+ "</ExchangeRatesSeries>";
		
		NbpXmlConverter converter = new NbpXmlConverter();
		BigDecimal rate = converter.getRate(data);
		
		assertThat(rate).isEqualTo(new BigDecimal("3.8916"));
	}
	
	@Test
	public void getRateTest_GivenBrokenXmlStringFromNbp_WhenCall_ShouldThrownConverterException() {
		String data = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"
				+ "<ExchangeRatesSeries xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n"
				+ "	<Table>C</Table>\n"
				+ "	<Code>USD</Code>\n"
				+ "		<Rate>\n"
				+ "			<No>178/C/NBP/2021</No>\n"
				+ "			<EffectiveDate>2021-09-14</EffectiveDate>\n"
				+ "			<Bid>3.8146</Bid>\n"
				+ "			<Ask>3.8916</Ask>\n"
				+ "		</Rate>\n"
				+ "</ExchangeRatesSeries>";
		
		assertThatThrownBy(() -> {
			new NbpJsonConverter().getRate(data);
		}).isInstanceOf(ConverterException.class)
		  .hasMessageContaining("Can't get rate for input data: ");
		
		assertThatThrownBy(() -> {
			new NbpJsonConverter().getRate(null);
		}).isInstanceOf(ConverterException.class)
		  .hasMessageContaining("Can't get rate for input data: ");
	}
}
