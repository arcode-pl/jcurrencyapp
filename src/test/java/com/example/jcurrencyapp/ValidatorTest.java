package com.example.jcurrencyapp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.example.jcurrencyapp.exceptions.ValidatorException;
import com.example.jcurrencyapp.model.CurrencyTypes;

public class ValidatorTest {

	@BeforeClass
	public void init() {
		System.out.println("Testing: " + this.getClass().getName());
	}

	@Test
	public void shouldThrowException_WhenCodeNotValid() {

		// Given
		Validator validator = new Validator();

		// When
		Throwable throwable = catchThrowable(() -> validator.validateInputs(null, new BigDecimal("1.0")));

		// Then
		assertThat(throwable).isInstanceOf(ValidatorException.class).hasMessageContaining("Code not valid");
	}

	@Test
	public void shouldThrowException_WhenCountNotValid() {

		// Given
		Validator validator = new Validator();

		// When
		Throwable throwable = catchThrowable(() -> validator.validateInputs(CurrencyTypes.USD, null));

		// Then
		assertThat(throwable).isInstanceOf(ValidatorException.class).hasMessageContaining("Quantity not valid");
	}

	@Test
	public void shouldReturnToday_WhenInputDateIsNull() {
		// Given
		Validator validator = new Validator();
		LocalDate date = null;

		// When
		date = validator.fixDate(date);

		// Then
		assertThat(date).isEqualTo(LocalDate.now());
	}

	@Test
	public void shouldReturnToday_WhenInputDateIsFuture() {
		// Given
		Validator validator = new Validator();
		LocalDate date = LocalDate.now().plusDays(20);

		// When
		date = validator.fixDate(date);

		// Then
		assertThat(date).isEqualTo(LocalDate.now());
	}
}
