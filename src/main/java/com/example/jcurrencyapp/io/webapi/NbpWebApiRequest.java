package com.example.jcurrencyapp.io.webapi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.example.jcurrencyapp.exceptions.WebApiException;
import com.example.jcurrencyapp.model.CurrencyTypes;

public class NbpWebApiRequest {
	private final static String host = "https://api.nbp.pl/api/exchangerates/rates/a/";

	public static String getJsonQuery(CurrencyTypes code, LocalDate date) {
		return getQuery(code, date) + "/?format=json";
	}
	
	public static String getJsonQuery(CurrencyTypes code, LocalDate startDate, LocalDate endDate) {
		return getQuery(code, startDate)
				+ "/" + endDate.format(DateTimeFormatter.ISO_LOCAL_DATE)
				+ "/?format=json";
	}
	
	public static String getXmlQuery(CurrencyTypes code, LocalDate date) {
		return getQuery(code, date) + "/?format=xml";
	}
	
	public static String getXmlQuery(CurrencyTypes code, LocalDate startDate, LocalDate endDate) {
		return getQuery(code, startDate)
				+ "/" + endDate.format(DateTimeFormatter.ISO_LOCAL_DATE)
				+ "/?format=xml";
	}
	
	private static String getQuery(CurrencyTypes code, LocalDate date) {
		try {
			return host + code.toString().toLowerCase() + "/" + date.format(DateTimeFormatter.ISO_LOCAL_DATE);
		} catch (Exception e) {
			throw new WebApiException("Can't get query: " + e.getMessage(), e.getCause());
		}
	}
}
