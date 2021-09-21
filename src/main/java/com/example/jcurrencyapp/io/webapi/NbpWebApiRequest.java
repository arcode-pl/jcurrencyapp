package com.example.jcurrencyapp.io.webapi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.example.jcurrencyapp.exceptions.ExceptionHandler;
import com.example.jcurrencyapp.exceptions.WebApiException;
import com.example.jcurrencyapp.model.CurrencyTypes;

public class NbpWebApiRequest {
	private final String host = "https://api.nbp.pl/api/exchangerates/rates/c/";

	public String getSimpleQuery(CurrencyTypes code, LocalDate date, boolean forceXml) {
		String result = "";

		try {
			return host + code.toString().toLowerCase() + "/" + date.format(DateTimeFormatter.ISO_LOCAL_DATE)
					+ "/?format=" + (forceXml ? "xml" : "json");
		} catch (Exception e) {
			ExceptionHandler
					.handleException(new WebApiException("Can't getSimpleQuery: " + e.getMessage(), e.getCause()));
		}

		return result;
	}
}
