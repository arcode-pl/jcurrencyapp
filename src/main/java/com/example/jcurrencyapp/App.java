package com.example.jcurrencyapp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import com.example.jcurrencyapp.data.converter.impl.NbpXmlConverter;
import com.example.jcurrencyapp.data.provider.impl.NbpXmlProvider;
import com.example.jcurrencyapp.model.CurrencyTypes;

public class App {

	// Example usage of API
	public static void main(String[] args) {

		Optional<BigDecimal> result;

		// JSON
		Controller control = new Controller();
		result = control.exchange(CurrencyTypes.EUR, new BigDecimal("1.0"), LocalDate.now().minusDays(2));
		result.ifPresentOrElse(p -> System.out.println(p.toString()), () -> System.out.println("empty"));

		// XML, *setCustom - do not create new Controller every time, but replace provider and converter
		control = new Controller(new NbpXmlProvider(), new NbpXmlConverter());
		result = control.exchange(CurrencyTypes.USD, new BigDecimal("2.0"), LocalDate.now());
		result.ifPresentOrElse(p -> System.out.println(p.toString()), () -> System.out.println("empty"));

		return;
	}
}
