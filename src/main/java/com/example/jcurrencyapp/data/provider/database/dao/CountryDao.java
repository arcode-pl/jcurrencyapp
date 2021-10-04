package com.example.jcurrencyapp.data.provider.database.dao;

import java.util.List;

import com.example.jcurrencyapp.data.provider.database.model.Country;

//@Repository for spring special
public interface CountryDao {

	Country findOne(long id);

	List<Country> findAll();

	void create(Country entity);

	Country update(Country entity);

	void delete(Country entity);

	void deleteById(long entityId);
	
}
