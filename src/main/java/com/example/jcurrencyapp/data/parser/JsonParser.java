package com.example.jcurrencyapp.data.parser;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser<T> implements Parser<T> {

	private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
	private final Class<T> type;
	
	public JsonParser(Class<T> type){
	    this.type = type;
	}
	
	static { 
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY/MM/dd"); 
		JSON_MAPPER.setDateFormat(simpleDateFormat); 
	}

	@Override
	public Optional<String> serialize(T data) {
		try { 
			return Optional.of(JSON_MAPPER.writeValueAsString(data)); 
		} catch (IOException e) { 
			throw new UncheckedIOException("Oh no!", e); 
		}
	}

	@Override
	public Optional<T> deserialize(String data) {
		try { 
			return Optional.of(JSON_MAPPER.readValue(data, type)); 
		} catch (JsonProcessingException e) { 
			throw new UncheckedIOException("Bad!", e); 
		}
	}
}
