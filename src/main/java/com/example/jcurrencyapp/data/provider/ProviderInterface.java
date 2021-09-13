package com.example.jcurrencyapp.data.provider;

import java.time.LocalDate;
import java.util.Optional;

import com.example.jcurrencyapp.data.model.Currency;

public interface ProviderInterface {
	public Optional<Currency> getRate(String code, LocalDate date);
}
