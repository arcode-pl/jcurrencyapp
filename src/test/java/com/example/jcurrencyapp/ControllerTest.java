package com.example.jcurrencyapp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.example.jcurrencyapp.exceptions.AppException;
import com.example.jcurrencyapp.model.CurrencyTypes;

//TODO: Update to given when then

public class ControllerTest {

	@BeforeClass
	public void init() {
		System.out.println("Testing: " + this.getClass().getName());
	}

	// Test end-end
	@Test
	public void shouldReturnValidExchangeRate_WhenGivenValidInputs() {

		// Given
		Controller controller = new Controller();
		CurrencyTypes currency = CurrencyTypes.USD;
		BigDecimal quantity = new BigDecimal("33.4567");
		LocalDate date = LocalDate.of(2016, 04, 12);
		BigDecimal usdAskPriceForDay20160412 = new BigDecimal("3.7695");

		// When
		Optional<BigDecimal> result = controller.exchange(currency, quantity, date);

		// Then
		assertThat(result.get()).isEqualTo(quantity.multiply(usdAskPriceForDay20160412));
	}

	@Test
	public void shouldReturnTodayExchangeRate_WhenDateIsNullOrFuture() {
		// Given
		Controller controller = new Controller();
		CurrencyTypes currency = CurrencyTypes.USD;
		BigDecimal quantity = new BigDecimal("33.4567");

		// When
		LocalDate date = null;
		Optional<BigDecimal> response = controller.exchange(currency, quantity, date);

		// Then
		assertThat(response).isNotNull();

		// When
		date = LocalDate.now().plusDays(30);
		response = controller.exchange(currency, quantity, date);

		// Then
		assertThat(response).isNotNull();
	}

	@Test
	public void shouldThrownException_WhenCurrencyIsNull() {
		// Given
		Controller controller = new Controller();
		CurrencyTypes currency = null;
		BigDecimal quantity = new BigDecimal("33.4567");
		LocalDate date = LocalDate.of(2016, 04, 12);

		// When
		Throwable throwable = catchThrowable(() -> controller.exchange(currency, quantity, date));

		// Then
		assertThat(throwable).isInstanceOf(AppException.class).hasMessageContaining("Code not valid");
	}

	@Test
	public void shouldThrownException_WhenQuantityIsNull() {
		// Given
		Controller controller = new Controller();
		CurrencyTypes currency = CurrencyTypes.USD;
		BigDecimal quantity = null;
		LocalDate date = LocalDate.of(2016, 04, 12);

		// When
		Throwable throwable = catchThrowable(() -> controller.exchange(currency, quantity, date));

		// Then
		assertThat(throwable).isInstanceOf(AppException.class).hasMessageContaining("Quantity not valid");
	}
}
