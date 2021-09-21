package com.example.jcurrencyapp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.jcurrencyapp.data.converter.impl.NbpXmlConverter;
import com.example.jcurrencyapp.data.provider.IProvider;
import com.example.jcurrencyapp.data.provider.impl.NbpXmlProvider;
import com.example.jcurrencyapp.model.CurrencyTypes;
import com.example.jcurrencyapp.model.Rate;

public class Demo {

	// Example usage of API
	public static void main(String[] args) {

		Optional<Rate> result;

		// JSON
		JCurrency jcurrency = new JCurrency();
		result = jcurrency.exchange(CurrencyTypes.EUR, new BigDecimal("1.0"), LocalDate.now().minusDays(2));
		result.ifPresentOrElse(p -> System.out.println(p.toString()), () -> System.out.println("empty"));

		// XML, *setCustom - do not create new Controller every time, but replace provider and converter
		List<IProvider> providers = Arrays.asList(new NbpXmlProvider(new NbpXmlConverter()));
		jcurrency = new JCurrency(providers);
		result = jcurrency.exchange(CurrencyTypes.USD, new BigDecimal("2.0"), LocalDate.now());
		result.ifPresentOrElse(p -> System.out.println(p.toString()), () -> System.out.println("empty"));

		return;
	}
}
