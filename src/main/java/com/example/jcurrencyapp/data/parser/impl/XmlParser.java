package com.example.jcurrencyapp.data.parser.impl;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import com.example.jcurrencyapp.data.parser.IParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XmlParser<T> implements IParser<T> {

	private static final ObjectMapper XML_MAPPER = new XmlMapper();
	private final Class<T> type;
	
	public XmlParser(Class<T> type){
	    this.type = type;
	}
	
	static { 
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY/MM/dd"); 
		XML_MAPPER.setDateFormat(simpleDateFormat); 
	}

	@Override
	public Optional<String> serialize(T data) {
		try { 
			return Optional.of(XML_MAPPER.writeValueAsString(data)); 
		} catch (IOException e) { 
			throw new UncheckedIOException("Oh no!", e); 
		}
	}

	@Override
	public Optional<T> deserialize(String data) {
		try { 
			return Optional.of(XML_MAPPER.readValue(data, type)); 
		} catch (JsonProcessingException e) { 
			throw new UncheckedIOException("Bad!", e); 
		}
	}
}
