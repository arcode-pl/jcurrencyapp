package com.example.jcurrencyapp.controller;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.jcurrencyapp.exceptions.AppException;
import com.example.jcurrencyapp.exceptions.ValidatorException;
import com.example.jcurrencyapp.model.CurrencyTypes;

public class ValidatorTest {

	@Test
	public void validateInputsTest_WhenCodeNotValid_ShouldThrowValidatorException() {
		Validator validator = new Validator();
		
		assertThatThrownBy(() -> {
			validator.validateInputs(null, new BigDecimal("1.00"));
		}).isInstanceOf(ValidatorException.class)
		  .hasMessageContaining("Code not valid");
	}
	
	@Test
	public void validateInputsTest_WhenCountNotValid_ShouldThrowValidatorException() {
		Validator validator = new Validator();
		
		assertThatThrownBy(() -> {
			validator.validateInputs(CurrencyTypes.USD, null);
		}).isInstanceOf(ValidatorException.class)
		  .hasMessageContaining("Count not valid");
	}

	@Test
	public void fixDateTest_WhenDataIsNull_ReturnTodayAsDate() {
		Validator validator = new Validator();
		LocalDate date;
		
		assertThatThrownBy(() -> {
			assertThat(validator.fixDate(null)).isEqualTo(LocalDate.now());
		}).isInstanceOf(ValidatorException.class)
		  .hasMessageContaining("Date fixed to today");
	}
	
	@Test
	public void fixDateTest_WhenDataIsFuture_ReturnTodayAsDate() {
		Validator validator = new Validator();
		LocalDate date;
		
		assertThatThrownBy(() -> {
			assertThat(validator.fixDate(LocalDate.now().plusDays(10))).isEqualTo(LocalDate.now());
		}).isInstanceOf(ValidatorException.class)
		  .hasMessageContaining("Date fixed to today");
	}
}
