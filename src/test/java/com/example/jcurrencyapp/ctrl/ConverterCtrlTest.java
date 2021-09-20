package com.example.jcurrencyapp.ctrl;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.example.jcurrencyapp.data.converter.IConverter;

public class ConverterCtrlTest {

	public class FakeConverter implements IConverter {

		BigDecimal value;

		public FakeConverter(BigDecimal value) {
			this.value = value;
		}

		@Override
		public BigDecimal getRate(String data) {
			return data != null ? value : null;
		}
	}

	@BeforeClass
	public void init() {
		System.out.println("Testing: " + this.getClass().getName());
	}

	@Test
	public void shouldReturnNull_WhenNotValidInputData() {

		// Given
		BigDecimal fakeOutput = new BigDecimal("1.23456789");
		ConverterCtrl controller = new ConverterCtrl(new FakeConverter(fakeOutput));

		// When
		BigDecimal result = controller.getRate(null);

		// Then
		assertThat(result).isNull();
	}

	public void shouldReturnRate_WhenValidInputData() {

		// Given
		BigDecimal fakeOutput = new BigDecimal("1.23456789");
		ConverterCtrl controller = new ConverterCtrl(new FakeConverter(fakeOutput));

		// When
		BigDecimal result = controller.getRate("test data");

		// Then
		assertThat(result).isEqualTo(new BigDecimal("1.23456789"));
	}
}
