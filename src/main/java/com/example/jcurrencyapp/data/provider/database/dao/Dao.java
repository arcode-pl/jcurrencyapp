package com.example.jcurrencyapp.data.provider.database.dao;

import java.time.LocalDate;
import java.util.List;

import com.example.jcurrencyapp.model.CountryTypes;
import com.example.jcurrencyapp.model.CurrencyTypes;

public interface Dao {

	Quotation getQuotation(CurrencyTypes currency, LocalDate date);
	
	List<Quotation> getQuotations(CurrencyTypes currency);
	
	List<Quotation> getQuotations(CurrencyTypes currency, LocalDate startDate, LocalDate endDate);

	List<Quotation> getQuotationsAsc(CurrencyTypes currency, int numberOfResult);
	
	List<Quotation> getQuotationsAsc(CurrencyTypes currency, LocalDate startDate, LocalDate endDate, int numberOfResult);
	
	List<Quotation> getQuotationsDesc(CurrencyTypes currency, int numberOfResult);
	
	List<Quotation> getQuotationsDesc(CurrencyTypes currency, LocalDate startDate, LocalDate endDate, int numberOfResult);
	
	void addQuotations(List<Quotation> quotations);
	
	Currency getCurrency(CurrencyTypes currency);
	
	Country getCountry(CountryTypes country);

	public Country updateCountry(Country country);
		
	public Currency updateCurrency(Currency country);
			
	public Quotation updateQuotation(Quotation country);

}