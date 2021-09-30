package com.example.jcurrencyapp.data.provider;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.example.jcurrencyapp.BaseTest;
import com.example.jcurrencyapp.model.CurrencyTypes;

public class NbpXmlProviderImplTest extends BaseTest{

	@BeforeClass
	public void setUp() {
		System.out.println("Testing: " + this.getClass().getName());
	}

	@Mock 
	NbpXmlProviderImpl mockNbpXmlProvider;
	
	@Test
	public void shouldGiveValidResponse_WhenHappyPathForXml() {
		// Given
		BigDecimal validResult = new BigDecimal("3.7695");
		Mockito.when(mockNbpXmlProvider.getRate(CurrencyTypes.USD, LocalDate.of(2016, 4, 12))).thenReturn(new BigDecimal("3.7695"));
		
		// When
		BigDecimal result = mockNbpXmlProvider.getRate(CurrencyTypes.USD, LocalDate.of(2016, 4, 12));
		
		// Then
		assertThat(result).isEqualTo(validResult);
	}
}
