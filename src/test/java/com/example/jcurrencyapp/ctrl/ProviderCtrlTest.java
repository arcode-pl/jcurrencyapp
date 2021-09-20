package com.example.jcurrencyapp.ctrl;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.jcurrencyapp.ctrl.ProviderCtrl;
import com.example.jcurrencyapp.ctrl.ConverterCtrlTest.FakeConverter;
import com.example.jcurrencyapp.data.converter.IConverter;
import com.example.jcurrencyapp.data.converter.impl.NbpXmlConverter;
import com.example.jcurrencyapp.data.provider.IProvider;
import com.example.jcurrencyapp.data.provider.impl.NbpXmlProvider;
import com.example.jcurrencyapp.exceptions.ConverterException;
import com.example.jcurrencyapp.model.CurrencyTypes;

public class ProviderCtrlTest {

	public class FakeProvider implements IProvider {

		int callToReturnData = 10;
		int callIdx = 0;

		@Override
		public String getData(String code, LocalDate date) {

			if (callIdx >= callToReturnData) {
				return String.valueOf(callIdx);
			}
			callIdx++;

			return null;
		}

		public FakeProvider(int callToReturnData) {
			this.callToReturnData = callToReturnData;
		}
	}

	@BeforeClass
	public void init() {
		System.out.println("Testing: " + this.getClass().getName());
	}

	@Test
	public void shouldReturnCallsNumberToAccessData_WhenDataIsAccessiebleWithinMaxBackDays() {

		// Given
		ProviderCtrl provider;
		
		// When
		int maxCallsToAccessData = 0;
		int callsToAccessData = 0;
		provider = new ProviderCtrl(new FakeProvider(callsToAccessData));
		String result = provider.getData(CurrencyTypes.USD, LocalDate.now(), maxCallsToAccessData);
		
		// Then
		assertThat(result).isEqualTo(String.valueOf(callsToAccessData));
		
		// When
		maxCallsToAccessData = 10;
		callsToAccessData = 10;
		provider = new ProviderCtrl(new FakeProvider(callsToAccessData));
		result = provider.getData(CurrencyTypes.USD, LocalDate.now(), maxCallsToAccessData);
		
		// Then
		assertThat(result).isEqualTo(String.valueOf(callsToAccessData));
	}
	

	@Test
	public void shouldReturnNull_WhenDataIsNotAccessiebleWithinMaxBackDays() {
		// Given fake provider
		ProviderCtrl provider;
		
		// When want data without looping back and data is not accessible with first call
		int maxCallsToAccessData = 0;
		int callsToAccessData = 1;
		provider = new ProviderCtrl(new FakeProvider(callsToAccessData));
		String result = provider.getData(CurrencyTypes.USD, LocalDate.now(), maxCallsToAccessData);
		
		// Then return null
		assertThat(result).isNull();
		
		// When want data with up to 10 calls and data is accessible with 11 call
		maxCallsToAccessData = 10;
		callsToAccessData = 11;
		provider = new ProviderCtrl(new FakeProvider(callsToAccessData));
		result = provider.getData(CurrencyTypes.USD, LocalDate.now(), maxCallsToAccessData);
		
		// Then return null
		assertThat(result).isNull();
	}
}
