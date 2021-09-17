package com.example.jcurrencyapp.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.testng.annotations.Test;

import com.example.jcurrencyapp.data.converter.IConverter;
import com.example.jcurrencyapp.data.converter.impl.NbpXmlConverter;
import com.example.jcurrencyapp.data.provider.IProvider;
import com.example.jcurrencyapp.data.provider.impl.NbpXmlProvider;
import com.example.jcurrencyapp.exceptions.ConverterException;
import com.example.jcurrencyapp.exceptions.ValidatorException;
import com.example.jcurrencyapp.io.webapi.NbpWebApiRequest;
import com.example.jcurrencyapp.io.webapi.WebApiController;
import com.example.jcurrencyapp.io.webapi.model.WebApiResponse;
import com.example.jcurrencyapp.model.CurrencyTypes;

public class LogicTest {

	public class TestProvider implements IProvider {

		int callToReturnData = 30;
		int callIdx = 0;

		@Override
		public String getData(String code, LocalDate date) {

			if (callIdx >= callToReturnData) {
				return "Get data with " + callIdx + " back days";
			}
			callIdx++;

			return null;
		}

		public TestProvider(int callToReturnData) {
			this.callToReturnData = callToReturnData;
		}
	}

	public class TestConverter implements IConverter {

		@Override
		public BigDecimal getRate(String data) {
			return new BigDecimal("1.0");
		}
	}
	
	@Test
	public void getDataWithBackLoopTest_WhenContainsInMaxBackDays_ShouldReturnData() {

		Logic logic = new Logic(new TestProvider(0), new TestConverter());
		assertThat(logic.getDataWithBackLoop(CurrencyTypes.USD, LocalDate.now(), 0))
				.contains("Get data with 0 back days");

		logic = new Logic(new TestProvider(30), new TestConverter());
		assertThat(logic.getDataWithBackLoop(CurrencyTypes.USD, LocalDate.now(), 30))
				.contains("Get data with 30 back days");
	}
	
	@Test
	public void getDataWithBackLoopTest_WhenNotContainsInMaxBackDays_ShouldThrownProviderExceptionAndReturnNull() {

		Logic logic = new Logic(new TestProvider(1), new TestConverter());
		assertThat(logic.getDataWithBackLoop(CurrencyTypes.USD, LocalDate.now(), 0))
				.isNull();

		logic = new Logic(new TestProvider(31), new TestConverter());
		assertThat(logic.getDataWithBackLoop(CurrencyTypes.USD, LocalDate.now(), 30))
				.isNull();
	}

	@Test
	public void getRateTest_GivenDefaultJsonConverter_WhenValidInputData_ShouldReturnValidBigDecimalValue() {
		Logic logic = new Logic();
		BigDecimal validValue = new BigDecimal("3.7695");
		String data = "{\"table\":\"C\",\"currency\":\"dolar amerykański\",\"code\":\"USD\",\"rates\":[{\"no\":\"070/C/NBP/2016\",\"effectiveDate\":\"2016-04-12\",\"bid\":3.6949,\"ask\":3.7695}]}";
		
		assertThat(logic.getRate(data)).isEqualTo(validValue);
	}
	
	@Test
	public void getRateTest_GivenDefaultJsonConverter_WhenInvalidInputData_ShouldThrownConverterExceptionAndReturnNull() {
		Logic logic = new Logic();
		String data = "{\"table\":\"C\",\",\"code\":\"USD\",\"rates\":[/C/NBP/2016\",\"effectiveDate\":\"2016-04-12\",\"bid\":3.6949,\"ask\":3.7695}]}";
		
		assertThatThrownBy(() -> {
			assertThat(logic.getRate(data)).isNull();
		}).isInstanceOf(ConverterException.class)
		  .hasMessageContaining("Can't get rate for input data: ");
		
		assertThatThrownBy(() -> {
			assertThat(logic.getRate(null)).isNull();
		}).isInstanceOf(ConverterException.class)
		  .hasMessageContaining("Can't get rate for input data: ");
	}
	
	@Test
	public void getRateTest_GivenXmlConverter_WhenValidInputData_ShouldReturnValidBigDecimalValue() {
		Logic logic = new Logic(new NbpXmlProvider(), new NbpXmlConverter());
		BigDecimal validValue = new BigDecimal("3.8916");
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
		
		assertThat(logic.getRate(data)).isEqualTo(validValue);
	}
	
	@Test
	public void getRateTest_GivenXmlConverter_WhenInvalidInputData_ShouldThrownConverterExceptionAndReturnNull() {
		Logic logic = new Logic(new NbpXmlProvider(), new NbpXmlConverter());
		String data = "{\"table\":\"C\",\",\"code\":\"USD\",\"rates\":[/C/NBP/2016\",\"effectiveDate\":\"2016-04-12\",\"bid\":3.6949,\"ask\":3.7695}]}";
		
		assertThatThrownBy(() -> {
			assertThat(logic.getRate(data)).isNull();
		}).isInstanceOf(ConverterException.class)
		  .hasMessageContaining("Can't get rate for input data: ");
		
		assertThatThrownBy(() -> {
			assertThat(logic.getRate(null)).isNull();
		}).isInstanceOf(ConverterException.class)
		  .hasMessageContaining("Can't get rate for input data: ");
	}
}
