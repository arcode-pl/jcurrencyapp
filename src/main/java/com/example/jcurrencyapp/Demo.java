package com.example.jcurrencyapp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.jcurrencyapp.data.converter.nbp.NbpJsonConverterImpl;
import com.example.jcurrencyapp.data.converter.nbp.NbpXmlConverterImpl;
import com.example.jcurrencyapp.data.provider.CacheProviderImpl;
import com.example.jcurrencyapp.data.provider.NbpProviderImpl;
import com.example.jcurrencyapp.data.provider.Provider;
import com.example.jcurrencyapp.model.CurrencyTypes;
import com.example.jcurrencyapp.model.Rate;

public class Demo {

	// Example usage of API
	public static void main(String[] args) {

		Optional<Rate> result;

		// JSON
		JCurrency jcurrency = new JCurrency();
		result = jcurrency.tryExchange(CurrencyTypes.EUR, new BigDecimal("1.0"), LocalDate.now().minusDays(2));
		result.ifPresentOrElse(p -> System.out.println(p.toString()), () -> System.out.println("empty"));

		List<Provider> providers = Arrays.asList(
				new CacheProviderImpl(), 
				new NbpProviderImpl(new NbpJsonConverterImpl()),
				new NbpProviderImpl(new NbpXmlConverterImpl()));
		jcurrency = new JCurrency(providers);
		result = jcurrency.tryExchange(CurrencyTypes.USD, new BigDecimal("2.0"), LocalDate.now());
		result.ifPresentOrElse(p -> System.out.println(p.toString()), () -> System.out.println("empty"));

		return;
	}
}
