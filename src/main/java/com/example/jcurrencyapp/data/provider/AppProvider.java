package com.example.jcurrencyapp.data.provider;

import java.time.LocalDate;
import java.util.Optional;

import com.example.jcurrencyapp.exceptions.ReadApiException;

//Returns String from selected source
public interface AppProvider {
	public Optional<String> getData(String code, LocalDate date) throws ReadApiException;
}
