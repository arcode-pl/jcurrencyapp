package com.example.jcurrencyapp;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.example.jcurrencyapp.data.converter.Converter;
import com.example.jcurrencyapp.data.provider.CacheProviderImpl;
import com.example.jcurrencyapp.data.provider.NbpJsonProviderImpl;
import com.example.jcurrencyapp.data.provider.Provider;
import com.example.jcurrencyapp.model.CurrencyTypes;
import com.example.jcurrencyapp.model.Rate;

public class ControllerTest {

	private class FakeProviderImpl implements Provider {

		public int callsToGetData;
		public int getCallsCounter;
		@SuppressWarnings("unused")
		public int saveCallsCounter;

		public FakeProviderImpl(int callsToGetData) {
			this.callsToGetData = callsToGetData;
			this.getCallsCounter = 0;
			this.saveCallsCounter = 0;
		}

		@Override
		public BigDecimal getRate(CurrencyTypes code, LocalDate date) {
			BigDecimal result = null;

			if (getCallsCounter >= callsToGetData) {
				result = new BigDecimal("1.23456789");
			}
			getCallsCounter++;

			return result;
		}

		@Override
		public void saveRate(Rate rate) {
			this.saveCallsCounter++;
		}
	}

	@Mock
	FakeProviderImpl provider0, provider1, provider2;

	@BeforeClass
	public void init() {
		System.out.println("Testing: " + this.getClass().getName());
	}

	@Test
	public void shouldSaveToPreviousProviders_WhenDataIsAvailableOnActualProvider() {
		// Given							 // I	II	III
		provider0 = new FakeProviderImpl(3); // get get save
		provider1 = new FakeProviderImpl(1); // get get
		provider2 = new FakeProviderImpl(2); // get 
		
		Controller ctrl = new Controller(List.of(provider0,	provider1, provider2));

		// When
		Rate result = ctrl.getRate(CurrencyTypes.USD, LocalDate.now());
		
		// Then
		assertThat(provider0.getCallsCounter).isEqualTo(2);
		assertThat(provider0.saveCallsCounter).isEqualTo(1);
		assertThat(provider1.getCallsCounter).isEqualTo(2);
		assertThat(provider1.saveCallsCounter).isEqualTo(0);
		assertThat(provider2.getCallsCounter).isEqualTo(1);
		assertThat(provider2.saveCallsCounter).isEqualTo(0);
		assertThat(result).isNotNull();
	}
	
	@Test
	public void shouldReturnNull_WhenDataIsNotAvailableWithinMaxBackDays() {
		// Given							 // I	II	III
		provider0 = new FakeProviderImpl(4); // get get max days reached
		provider1 = new FakeProviderImpl(2); // get get
		provider2 = new FakeProviderImpl(3); // get get
		
		Controller ctrl = new Controller(List.of(provider0,	provider1, provider2));
		ctrl.getConfig().setMaxBackDays(1);
		
		// When
		Rate result = ctrl.getRate(CurrencyTypes.USD, LocalDate.now());
		
		// Then
		assertThat(provider0.getCallsCounter).isEqualTo(2);
		assertThat(provider0.saveCallsCounter).isEqualTo(0);
		assertThat(provider1.getCallsCounter).isEqualTo(2);
		assertThat(provider1.saveCallsCounter).isEqualTo(0);
		assertThat(provider2.getCallsCounter).isEqualTo(2);
		assertThat(provider2.saveCallsCounter).isEqualTo(0);
		assertThat(result).isNull();
	}
}
