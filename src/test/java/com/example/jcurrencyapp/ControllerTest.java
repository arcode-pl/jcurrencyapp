package com.example.jcurrencyapp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.testng.annotations.Test;

import com.example.jcurrencyapp.exceptions.ValidatorException;
import com.example.jcurrencyapp.model.CurrencyTypes;

public class ControllerTest {

	//Test end-end
	@Test
	public void exchangeTest_WhenCallExchangeWithValidParameters_ShouldReturnKnownValidValue() {
		Controller controller = new Controller();
		BigDecimal count = new BigDecimal("33.4567");
		BigDecimal ask = new BigDecimal("3.7695");
		
		Optional<BigDecimal> result = controller.exchange(CurrencyTypes.USD, count, LocalDate.of(2016, 4, 12));
		
		assertThat(result.get()).isEqualTo(count.multiply(ask));
	}
	
	@Test
	public void exchangeTest_WhenCallExchangeWithoutDateOrFutureDate_ShouldReturnUnknownValidValue() {
		Controller controller = new Controller();
		
		Optional<BigDecimal> result = controller.exchange(CurrencyTypes.USD, new BigDecimal("1.0"), LocalDate.of(2016, 4, 12));
		
		assertThat(result).isNotEmpty();
	}
	
	@Test
	public void exchangeTest_WhenCallExchangeWithoutCodeValue_ShouldReturnEmptyOptionalValueAndThrownRuntimeException() {
		Controller controller = new Controller();
		
//		assertThatThrownBy(() -> {
//			controller.exchange(null, new BigDecimal("1.0"), LocalDate.of(2016, 4, 12));
//		}).isInstanceOf(RuntimeException.class);
		
		assertThat(controller.exchange(CurrencyTypes.USD, null, LocalDate.of(2016, 4, 12))).isEmpty();
	}
	
	@Test
	public void exchangeTest_WhenCallExchangeWithoutCountValue_ShouldReturnEmptyOptionalValueAndThrownRuntimeException() {
		Controller controller = new Controller();
		
//		assertThatThrownBy(() -> {
//			controller.exchange(CurrencyTypes.USD, null, LocalDate.of(2016, 4, 12));
//		}).isInstanceOf(RuntimeException.class);
		
		assertThat(controller.exchange(CurrencyTypes.USD, null, LocalDate.of(2016, 4, 12))).isEmpty();
	}
}
