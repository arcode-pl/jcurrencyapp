package com.example.jcurrencyapp.data.provider.database;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.NoResultException;

import com.example.jcurrencyapp.data.provider.Provider;
import com.example.jcurrencyapp.data.provider.database.dao.Currency;
import com.example.jcurrencyapp.data.provider.database.dao.Dao;
import com.example.jcurrencyapp.data.provider.database.dao.Quotation;
import com.example.jcurrencyapp.model.CurrencyTypes;
import com.example.jcurrencyapp.model.Rate;

public class DatabaseProviderImpl implements Provider {

	Dao dao;
	
	public DatabaseProviderImpl(Dao dao) {
		this.dao = dao;
	}

	@Override
	public BigDecimal getPrice(CurrencyTypes currency, LocalDate date) {
		try {
			return dao.findForCurrencyAndData(currency, date).getPrice();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void saveRate(Rate rate) {
		Currency currency = dao.getCurrency(rate.getCurrency());
		dao.insertIntoQuotations(Arrays.asList(new Quotation(currency, rate.getDate(), rate.getPrice())));
	}

	@Override
	public List<Rate> getRates(CurrencyTypes currency, LocalDate startDate, LocalDate endDate) {
		List<Quotation> quotations;
		List<Rate> rates = new ArrayList<Rate>();

		try {
			quotations = dao.findAllForCurrencyWithDataRange(currency, startDate, endDate);
			for (Quotation quotation : quotations) {
				rates.add(new Rate(currency, quotation.getDate(), quotation.getPrice()));
			}

		} catch (NoResultException e) {
			return null;
		}

		return rates;
	}

	@Override
	public void saveRates(List<Rate> rates) {
		if (rates != null) {
			List<Quotation> quotations = new ArrayList<Quotation>();
			for (Rate rate : rates) {
				Currency currency = dao.getCurrency(rate.getCurrency());
				quotations.add(new Quotation(currency, rate.getDate(), rate.getPrice()));
			}
			dao.insertIntoQuotations(quotations);
		}
	}

}
