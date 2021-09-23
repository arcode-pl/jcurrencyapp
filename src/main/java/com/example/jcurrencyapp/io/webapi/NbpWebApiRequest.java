package com.example.jcurrencyapp.io.webapi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.example.jcurrencyapp.exceptions.WebApiException;
import com.example.jcurrencyapp.model.CurrencyTypes;

public class NbpWebApiRequest {
	private final static String host = "https://api.nbp.pl/api/exchangerates/rates/c/";

	public static String getJsonQuery(CurrencyTypes code, LocalDate date) {
		return getQuery(code, date) + "/?format=json";
	}
	
	public static String getXmlQuery(CurrencyTypes code, LocalDate date) {
		return getQuery(code, date) + "/?format=xml";
	}
	
	private static String getQuery(CurrencyTypes code, LocalDate date) {
		try {
			return host + code.toString().toLowerCase() + "/" + date.format(DateTimeFormatter.ISO_LOCAL_DATE);
		} catch (Exception e) {
			throw new WebApiException("Can't get query: " + e.getMessage(), e.getCause());
		}
	}
}
