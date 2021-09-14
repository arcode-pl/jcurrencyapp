package com.example.jcurrencyapp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import com.example.jcurrencyapp.data.converter.impl.NbpJsonConverter;
import com.example.jcurrencyapp.data.converter.impl.NbpXmlConverter;
import com.example.jcurrencyapp.data.model.CurrencyTypes;
import com.example.jcurrencyapp.data.provider.impl.NbpJsonProvider;
import com.example.jcurrencyapp.data.provider.impl.NbpXmlProvider;
import com.example.jcurrencyapp.exceptions.ConverterException;
import com.example.jcurrencyapp.exceptions.InputsNotValidException;
import com.example.jcurrencyapp.exceptions.ProviderException;
import com.example.jcurrencyapp.exceptions.ReadApiException;

public class App {

	// Example usage of API
	public static void main(String[] args)
			throws ProviderException, ConverterException, InputsNotValidException, ReadApiException {

		Optional<BigDecimal> result;

		AppController control = new AppController();

		// JSON
		control.setCustom(new NbpJsonProvider(), new NbpJsonConverter());
		result = control.exchange(CurrencyTypes.EUR, new BigDecimal("1.0"), LocalDate.now().minusDays(2));
		result.ifPresentOrElse(p -> System.out.println(p.toString()), () -> System.out.println("empty"));

		// XML
		control.setCustom(new NbpXmlProvider(), new NbpXmlConverter());
		result = control.exchange(CurrencyTypes.USD, new BigDecimal("2.0"), LocalDate.now());
		result.ifPresentOrElse(p -> System.out.println(p.toString()), () -> System.out.println("empty"));

		return;
	}
}
