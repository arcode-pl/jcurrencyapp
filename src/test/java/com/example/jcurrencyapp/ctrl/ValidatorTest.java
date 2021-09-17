package com.example.jcurrencyapp.ctrl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.example.jcurrencyapp.ctrl.Validator;
import com.example.jcurrencyapp.exceptions.ValidatorException;
import com.example.jcurrencyapp.model.CurrencyTypes;

public class ValidatorTest {

	@BeforeClass
	public void init() {
		System.out.println("Testing: " + this.getClass().getName());
	}

	@Test
	public void shouldValidateInputs_WhenCodeNotValid_ThenThrowException() {

		// Given validator
		Validator validator = new Validator();

		// When validate inputs with invalid code
		Throwable throwable = catchThrowable(() -> validator.validateInputs(null, new BigDecimal("1.0")));

		// Then should throw ValidatorException
		assertThat(throwable).isInstanceOf(ValidatorException.class).hasMessageContaining("Code not valid");
	}

	@Test
	public void shouldValidateInputs_WhenCountNotValid_ThenThrowException() {

		// Given validator
		Validator validator = new Validator();

		// When validate inputs with invalid code
		Throwable throwable = catchThrowable(() -> validator.validateInputs(CurrencyTypes.USD, null));

		// Then should throw ValidatorException
		assertThat(throwable).isInstanceOf(ValidatorException.class).hasMessageContaining("Count not valid");
	}

	@Test
	public void shouldfixDate_WhenInputDataIsNull_ThenThrowExceptionAndReturnTodayAsDate() {
		// Given validator
		Validator validator = new Validator();
		LocalDate date;

		// When call fixDate with future
		date = validator.fixDate(null);

		// Then return today as date and thrown exception
		assertThat(date).isEqualTo(LocalDate.now());
	}

	@Test
	public void shouldfixDate_WhenInputDataIsFuture_ThenThrowExceptionAndReturnTodayAsDate() {
		// Given validator
		Validator validator = new Validator();
		LocalDate date = null;

		// When call fixDate with future
		date = validator.fixDate(LocalDate.now().plusDays(20));

		// Then return today as date and thrown exception
		assertThat(date).isEqualTo(LocalDate.now());
	}
}
