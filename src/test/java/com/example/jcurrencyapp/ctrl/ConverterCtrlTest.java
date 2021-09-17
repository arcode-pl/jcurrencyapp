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
	public void shouldGetRate_WhenNotValidInputData_ReturnNull() {

		// Given fake converter with fake value of "1.23456789";
		BigDecimal fakeOutput = new BigDecimal("1.23456789");
		ConverterCtrl controller = new ConverterCtrl(new FakeConverter(fakeOutput));

		// When call with null string
		BigDecimal result = controller.getRate(null);

		// Then should get null on output
		assertThat(result).isNull();
	}

	public void shouldGetRate_WhenValidInputData_ReturnValidBigDecimalValue() {

		// Given fake converter with fake value of "1.23456789";
		BigDecimal fakeOutput = new BigDecimal("1.23456789");
		ConverterCtrl controller = new ConverterCtrl(new FakeConverter(fakeOutput));

		// When call controller.getRate with not null string
		BigDecimal result = controller.getRate("test data");

		// Then should get same fake output
		assertThat(result).isEqualTo(new BigDecimal("1.23456789"));
	}
}
