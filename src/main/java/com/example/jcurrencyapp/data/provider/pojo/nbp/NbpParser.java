package com.example.jcurrencyapp.data.provider.pojo.nbp;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class NbpParser {
	
	private static final ObjectMapper XML_MAPPER = new XmlMapper();
	private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
	
	static {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY/MM/dd");
		XML_MAPPER.setDateFormat(simpleDateFormat);
	    JSON_MAPPER.setDateFormat(simpleDateFormat);
	}
	  
	public static String serialize(NbpCurrency currency, boolean useXml) {
		    return useXml ? serialize(currency, XML_MAPPER) : serialize(currency, JSON_MAPPER);
	}

	private static String serialize(NbpCurrency currency, ObjectMapper objectMapper) {
		try {
		      return objectMapper.writeValueAsString(currency);
		    } catch (IOException e) {
		      throw new UncheckedIOException("Oh no!", e);
		    }
	}
	
	public static NbpCurrency deserialize(String NbpCurrency, boolean useXml) {
	    return useXml ? deserialize(NbpCurrency, XML_MAPPER) : deserialize(NbpCurrency, JSON_MAPPER);
	  }

	  private static NbpCurrency deserialize(String NbpCurrency, ObjectMapper objectMapper) {
	    try {
	      return objectMapper.readValue(NbpCurrency, NbpCurrency.class);
	    } catch (JsonProcessingException e) {
	      throw new UncheckedIOException("Bad!", e);
	    }
	  }
}
