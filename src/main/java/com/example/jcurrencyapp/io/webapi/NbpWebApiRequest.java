package com.example.jcurrencyapp.io.webapi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.example.jcurrencyapp.exceptions.ExceptionHandler;
import com.example.jcurrencyapp.exceptions.WebApiException;
import com.example.jcurrencyapp.model.CurrencyTypes;

public class NbpWebApiRequest {
	private final String host = "https://api.nbp.pl/api/exchangerates/rates/c/";
	
	CurrencyTypes code;
	LocalDate date;
	
	public NbpWebApiRequest(CurrencyTypes code, LocalDate date) {
		this.code = code;
		this.date = date;
	}

	public String getJsonQuery() {
		return this.getQuery() + "json";
	}
	
	public String getXmlQuery() {
		return this.getQuery() + "xml";
	}
	
	private String getQuery() {
		String result = "";

		try {
			return host + code.toString().toLowerCase() + "/" + date.format(DateTimeFormatter.ISO_LOCAL_DATE)
					+ "/?format=";
		} catch (Exception e) {
			ExceptionHandler
					.handleException(new WebApiException("Can't getSimpleQuery: " + e.getMessage(), e.getCause()));
		}

		return result;
	}
}
