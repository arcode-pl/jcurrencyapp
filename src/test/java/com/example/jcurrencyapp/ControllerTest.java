package com.example.jcurrencyapp;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.example.jcurrencyapp.data.converter.Converter;
import com.example.jcurrencyapp.data.provider.Provider;
import com.example.jcurrencyapp.model.CurrencyTypes;
import com.example.jcurrencyapp.model.Rate;

public class ControllerTest {

	public class FakeConverter implements Converter {

		BigDecimal value;

		public FakeConverter(BigDecimal value) {
			this.value = value;
		}

		@Override
		public BigDecimal getRate(String data) {
			return data != null ? value : null;
		}
	}

	public class FakeProvider implements Provider {

		int callToReturnData = 10;
		int callIdx = 0;

		public FakeProvider(int callToReturnData) {
			this.callToReturnData = callToReturnData;
		}

		@Override
		public BigDecimal getRate(CurrencyTypes code, LocalDate date) {
			if (callIdx >= callToReturnData) {
				return BigDecimal.valueOf(callIdx);
			}
			callIdx++;

			return null;
		}

		@Override
		public void saveRate(Rate rate) {
			// TODO Auto-generated method stub
			
		}
	}

	@BeforeClass
	public void init() {
		System.out.println("Testing: " + this.getClass().getName());
	}

	@Test
	public void shouldReturnCallsNumberToAccessData_WhenDataIsAccessiebleWithinMaxBackDays() {

		// Given
		Controller ctrl;

		// When
		int maxCallsToAccessData = 0;
		int callsToAccessData = 0;

		ctrl = new Controller(
				Arrays.asList(
						new FakeProvider(callsToAccessData + 1), // after first call from first provider
						new FakeProvider(callsToAccessData + 0))); // immediately from second provider

		Rate result = ctrl.getRate(CurrencyTypes.USD, LocalDate.now());

		// Then
		assertThat(result.getDate()).isEqualTo(LocalDate.now());

		// When
		maxCallsToAccessData = 10;
		callsToAccessData = 10;
		ctrl = new Controller(Arrays.asList(
				new FakeProvider(callsToAccessData*2), 
				new FakeProvider(callsToAccessData)));
		ctrl.getConfig().setMaxBackDays(maxCallsToAccessData);
		
		result = ctrl.getRate(CurrencyTypes.USD, LocalDate.now());

		// Then
		assertThat(result.getDate()).isEqualTo(LocalDate.now().minusDays(callsToAccessData));
	}

	@Test
	public void shouldReturnNull_WhenDataIsNotAccessiebleWithinMaxBackDays() {
		// Given fake provider
		Controller ctrl;

		// When
		int maxCallsToAccessData = 0;
		int callsToAccessData = 1;
		ctrl = new Controller(Arrays.asList(
				new FakeProvider(callsToAccessData + 1), 
				new FakeProvider(callsToAccessData + 0)));
		ctrl.getConfig().setMaxBackDays(maxCallsToAccessData);;

		Rate result = ctrl.getRate(CurrencyTypes.USD, LocalDate.now());
		
		// Then
		assertThat(result).isNull();

		// When
		maxCallsToAccessData = 10;
		callsToAccessData = 11;
		ctrl = new Controller(Arrays.asList(
				new FakeProvider(callsToAccessData + 1), 
				new FakeProvider(callsToAccessData + 0)));
		ctrl.getConfig().setMaxBackDays(maxCallsToAccessData);;
		result = ctrl.getRate(CurrencyTypes.USD, LocalDate.now());

		// Then
		assertThat(result).isNull();
	}
}
