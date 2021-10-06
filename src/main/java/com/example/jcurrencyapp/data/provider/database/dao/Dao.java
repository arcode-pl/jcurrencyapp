package com.example.jcurrencyapp.data.provider.database.dao;

import java.time.LocalDate;
import java.util.List;

import com.example.jcurrencyapp.model.CountryTypes;
import com.example.jcurrencyapp.model.CurrencyTypes;

public interface Dao {

	List<Quotation> findAllForCurrency(CurrencyTypes currency);
	
	List<Quotation> findAllForCurrencyWithDataRange(CurrencyTypes currency, LocalDate startDate, LocalDate endDate);

	Quotation findForCurrencyAndData(CurrencyTypes currency, LocalDate date);

	void insertIntoQuotations(List<Quotation> quotations);
	
	Currency getCurrency(CurrencyTypes currency);
	
	Country getCountry(CountryTypes country);
}