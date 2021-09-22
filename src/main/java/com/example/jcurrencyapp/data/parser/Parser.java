package com.example.jcurrencyapp.data.parser;

import java.util.Optional;

import com.example.jcurrencyapp.model.FileTypes;

public interface Parser<T> {
	public Optional<String> serialize(T data);
	public Optional<T> deserialize(String data);
}
