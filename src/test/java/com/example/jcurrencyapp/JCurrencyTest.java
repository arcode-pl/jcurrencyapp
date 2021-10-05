package com.example.jcurrencyapp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Optional;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.example.jcurrencyapp.exceptions.AppException;
import com.example.jcurrencyapp.model.CurrencyTypes;
import com.example.jcurrencyapp.model.Rate;

public class JCurrencyTest {

	@BeforeClass
	public void init() {
		System.out.println("Testing: " + this.getClass().getName());
	}

	// Test end-end
	@Test
	public void shouldReturnValidExchangeRate_WhenGivenValidInputs() {

		// Given
		JCurrency controller = new JCurrency();
		CurrencyTypes currency = CurrencyTypes.USD;
		BigDecimal quantity = new BigDecimal("33.45670");
		LocalDate date = LocalDate.of(2016, 04, 12);
		BigDecimal usdMidPriceForDay20160412 = new BigDecimal("3.7490");

		// When
		Optional<Rate> result = controller.tryExchange(currency, quantity, date);
		BigDecimal validResult = quantity.multiply(usdMidPriceForDay20160412).setScale(8, RoundingMode.HALF_EVEN);
		
		// Then
		assertThat(result.get().getPrice()).isEqualTo(validResult);
	}

	@Test
	public void shouldReturnTodayExchangeRate_WhenDateIsNullOrFuture() {
		// Given
		JCurrency controller = new JCurrency();
		CurrencyTypes currency = CurrencyTypes.USD;
		BigDecimal quantity = new BigDecimal("33.4567");

		// When
		LocalDate date = null;
		Optional<Rate> response = controller.tryExchange(currency, quantity, date);

		// Then
		assertThat(response).isNotNull();

		// When
		date = LocalDate.now().plusDays(30);
		response = controller.tryExchange(currency, quantity, date);

		// Then
		assertThat(response).isNotNull();
	}

	@Test
	public void shouldThrownException_WhenCurrencyIsNull() {
		// Given
		JCurrency controller = new JCurrency();
		CurrencyTypes currency = null;
		BigDecimal quantity = new BigDecimal("33.4567");
		LocalDate date = LocalDate.of(2016, 04, 12);

		// When
		Throwable throwable = catchThrowable(() -> controller.tryExchange(currency, quantity, date));

		// Then
		assertThat(throwable).isInstanceOf(AppException.class).hasMessageContaining("Code not valid");
	}

	@Test
	public void shouldThrownException_WhenQuantityIsNull() {
		// Given
		JCurrency controller = new JCurrency();
		CurrencyTypes currency = CurrencyTypes.USD;
		BigDecimal quantity = null;
		LocalDate date = LocalDate.of(2016, 04, 12);

		// When
		Throwable throwable = catchThrowable(() -> controller.tryExchange(currency, quantity, date));

		// Then
		assertThat(throwable).isInstanceOf(AppException.class).hasMessageContaining("Quantity not valid");
	}
	
	@Test
	public void sampleTest() {
		JCurrency app = mock(JCurrency.class);
		
		when(app.tryExchange(null, null, null))
				.thenReturn(Optional.empty());
		
		assertThat(app.tryExchange(null,null,null)).isEmpty();
	}
}
