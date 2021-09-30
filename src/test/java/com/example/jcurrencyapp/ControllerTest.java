package com.example.jcurrencyapp;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.example.jcurrencyapp.data.provider.Provider;
import com.example.jcurrencyapp.model.CurrencyTypes;
import com.example.jcurrencyapp.model.Rate;

public class ControllerTest {

	private class FakeProviderImpl implements Provider {

		public int callsToGetData;
		public int getRateTimes;
		public int saveRateTimes;

		public FakeProviderImpl(int callsToGetData) {
			this.callsToGetData = callsToGetData;
			this.getRateTimes = 0;
			this.saveRateTimes = 0;
		}

		@Override
		public BigDecimal getRate(CurrencyTypes code, LocalDate date) {
			BigDecimal result = null;

			if (getRateTimes >= callsToGetData) {
				result = new BigDecimal("1.23456789");
			}
			getRateTimes++;

			return result;
		}

		@Override
		public void saveRate(Rate rate) {
			this.saveRateTimes++;
		}

		@Override
		public List<Rate> getRates(CurrencyTypes code, LocalDate startDate, LocalDate endDate) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void saveRates(List<Rate> rates) {
			// TODO Auto-generated method stub
			
		}
	}

	@Mock
	FakeProviderImpl provider0, provider1, provider2;

	@BeforeClass
	public void init() {
		System.out.println("Testing: " + this.getClass().getName());
	}

	@Test
	public void mockitoTest() {
		CurrencyTypes code = CurrencyTypes.USD;
		LocalDate date = LocalDate.now();

		// given
		Provider provider = Mockito.spy(Provider.class);
		// when
		provider.getRate(code, date);
		// then
		Mockito.verify(provider, Mockito.times(1)).getRate(code, date);

	}

	@Test
	public void shouldSaveToPreviousProviders_WhenDataIsAvailableOnActualProvider() {
		// Given
		// Logic goes through providers, when can't get rate with wanted date, try
		// second time with day before until max back days reached
		//	provider: (days to data)
		//	0: (3) -> get -> (2) -> get -> (1) -> set
		//  1: (1) -> get -> (0) -> get -> 
		//  2: (2) -> get -> (1)

		provider0 = new FakeProviderImpl(3); // data available when go back 3 days
		provider1 = new FakeProviderImpl(1); // data available when go back 1 day
		provider2 = new FakeProviderImpl(2); // data available when go back 2 days

		Controller ctrl = new Controller(List.of(provider0, provider1, provider2));

		// When
		Rate result = ctrl.getRate(CurrencyTypes.USD, LocalDate.now());

		// Then
		assertThat(provider0.getRateTimes).isEqualTo(2);
		assertThat(provider0.saveRateTimes).isEqualTo(1);
		assertThat(provider1.getRateTimes).isEqualTo(2);
		assertThat(provider1.saveRateTimes).isEqualTo(0);
		assertThat(provider2.getRateTimes).isEqualTo(1);
		assertThat(provider2.saveRateTimes).isEqualTo(0);
		assertThat(result).isNotNull();
	}

	@Test
	public void shouldReturnNull_WhenDataIsNotAvailableWithinMaxBackDays() {
		// Given // I II III
		provider0 = new FakeProviderImpl(2);

		Controller ctrl = new Controller(List.of(provider0));
		ctrl.getConfig().setMaxBackDays(1);

		// When
		Rate result = ctrl.getRate(CurrencyTypes.USD, LocalDate.now());

		// Then
		assertThat(result).isNull();
	}
}
