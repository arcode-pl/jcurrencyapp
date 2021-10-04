package com.example.jcurrencyapp.data.provider.database.dao;

import java.util.List;

import com.example.jcurrencyapp.data.provider.database.model.Currency;

public interface CurrencyDao {

	Currency findOne(long id);

	List<Currency> findAll();

	void create(Currency entity);

	Currency update(Currency entity);

	void delete(Currency entity);

	void deleteById(long entityId);
	
}
