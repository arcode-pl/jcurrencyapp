package com.example.jcurrencyapp.data.provider.database;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.example.jcurrencyapp.Config;
import com.example.jcurrencyapp.Demo;
import com.example.jcurrencyapp.data.provider.database.DatabaseProviderImpl;
import com.example.jcurrencyapp.data.provider.database.dao.DaoImpl;
import com.example.jcurrencyapp.model.CurrencyTypes;
import com.example.jcurrencyapp.model.Rate;

public class DatabaseProviderImplTest {

	MathContext mc;

	@BeforeClass
	public void setUp() {
		// I think that this need to be implemented in SQL initialization script of
		// database or hard-coded in source for currency and country code.
		// Load country and currency codes to database.
		//Demo.initCurrencies();
	}

	@Test //data provider / data source
	public void shouldTestPrecisionAndScale() {
		
	}
	
	/* Integration tests */
	
	@Test
	public void shouldSaveRate_And_GetRate() {
		// given
		DatabaseProviderImpl provider = new DatabaseProviderImpl(new DaoImpl());
		CurrencyTypes code = CurrencyTypes.USD;
		LocalDate date = LocalDate.now();
		BigDecimal rate = new BigDecimal(Math.random()).setScale(Config.RATE_SCALE, RoundingMode.HALF_EVEN);
		Rate input = new Rate(code, date, rate);

		// when save & get
		provider.saveRate(input);
		BigDecimal result = provider.getPrice(code, date);

		// then
		assertThat(result).isEqualTo(input.getPrice());
	}

	@Test
	public void shouldSaveRates_And_GetRates() {
		// given
		DatabaseProviderImpl provider = new DatabaseProviderImpl(new DaoImpl());
		CurrencyTypes code = CurrencyTypes.USD;
		LocalDate startDate = LocalDate.of(2016, 7, 10);
		LocalDate endDate = LocalDate.of(2016, 7, 20);
		List<Rate> inputRates = new ArrayList<Rate>();

		// when save and get
		LocalDate tmpDate = startDate;
		while (tmpDate.isBefore(endDate)) {
			inputRates.add(new Rate(code, tmpDate,
					BigDecimal.valueOf(Math.random()).setScale(Config.RATE_SCALE, RoundingMode.HALF_EVEN)));
			tmpDate = tmpDate.plusDays(1);
		}

		provider.saveRates(inputRates);
		List<Rate> outputRates = provider.getRates(code, startDate, endDate);

		// then
		assertThat(outputRates).isNotNull();
		assertThat(outputRates.size()).isEqualTo(inputRates.size());
		for (int i = 0; i < inputRates.size(); i++) {
			assertThat(outputRates.get(i)).isEqualTo(inputRates.get(i));
		}
	}
}
