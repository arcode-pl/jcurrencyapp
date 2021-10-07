package com.example.jcurrencyapp.data.provider.nbp;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.example.jcurrencyapp.BaseTest;
import com.example.jcurrencyapp.model.CurrencyTypes;

public class NbpJsonProviderImplTest extends BaseTest{

	@BeforeClass
	public void setUp() {
		System.out.println("Testing: " + this.getClass().getName());
	}
	
	@Mock 
	NbpJsonProviderImpl mockNbpJsonProvider;
	
	@Test
	public void shouldGiveValidResponse_WhenHappyPathForJson() {
		// Given
		BigDecimal validResult = new BigDecimal("3.7695");
		LocalDate date = LocalDate.of(2016, 4, 12);
		Mockito.when(mockNbpJsonProvider.getPrice(CurrencyTypes.USD, date)).thenReturn(new BigDecimal("3.7695"));
		
		// When
		BigDecimal result = mockNbpJsonProvider.getPrice(CurrencyTypes.USD, date);
		
		// Then
		assertThat(result).isEqualTo(validResult);
	}
}
