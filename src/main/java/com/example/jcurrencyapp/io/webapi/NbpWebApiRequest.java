package com.example.jcurrencyapp.io.webapi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.example.jcurrencyapp.exceptions.ExceptionHandler;
import com.example.jcurrencyapp.exceptions.WebApiException;

public class NbpWebApiRequest {
	private final String host = "https://api.nbp.pl/api/exchangerates/rates/c/";
	
	public String getSimpleQuery(String code, LocalDate date, boolean forceXml) {
		String result = "";
		
		try {
			return host + 
				code.toLowerCase() + "/" + 
				date.format(DateTimeFormatter.ISO_LOCAL_DATE) + 
				"/?format=" + (forceXml ? "xml" : "json");
		} catch (Exception e) {
			ExceptionHandler.handleException(new WebApiException("Can't getSimpleQuery: " + e.getMessage()));
		}
	
		return result;
	}
}
