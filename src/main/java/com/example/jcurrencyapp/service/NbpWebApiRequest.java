package com.example.jcurrencyapp.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NbpWebApiRequest {
	private final String host = "http://api.nbp.pl/api/exchangerates/rates/c/";
	
	public String getSimpleQuery(String code, LocalDate date, boolean forceXml) {
		
		if (code == null || date == null) {
			return "";
		}
		
		return host +
			code.toLowerCase() + "/"  + 
			date.format(DateTimeFormatter.ISO_LOCAL_DATE) + 
			"/?format=" + (forceXml ? "xml" : "json");
	}
}
