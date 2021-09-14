package com.example.jcurrencyapp.data.parser;

import java.util.Optional;

public interface AppParser<T> {
	public Optional<String> serialize(T data);
	public Optional<T> deserialize(String data);
}
