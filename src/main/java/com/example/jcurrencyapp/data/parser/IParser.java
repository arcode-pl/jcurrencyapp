package com.example.jcurrencyapp.data.parser;

import java.util.Optional;

public interface IParser<T> {
	public Optional<String> serialize(T data);
	public Optional<T> deserialize(String data);
}
