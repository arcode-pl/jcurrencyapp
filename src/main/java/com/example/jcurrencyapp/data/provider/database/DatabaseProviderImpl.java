package com.example.jcurrencyapp.data.provider.database;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.jcurrencyapp.Config;
import com.example.jcurrencyapp.HibernateUtil;
import com.example.jcurrencyapp.data.provider.Provider;
import com.example.jcurrencyapp.data.provider.database.dao.Quotation;
import com.example.jcurrencyapp.data.provider.database.dao.QuotationDaoImpl;
import com.example.jcurrencyapp.model.CurrencyTypes;
import com.example.jcurrencyapp.model.Rate;

public class DatabaseProviderImpl implements Provider {

	QuotationDaoImpl quotationDao;

	public DatabaseProviderImpl() {
		quotationDao = new QuotationDaoImpl();
	}

	@Override
	public BigDecimal getPrice(CurrencyTypes currency, LocalDate date) {
		try {
			return quotationDao.findForCurrencyAndData(currency, date).getPrice();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void saveRate(Rate rate) {
		quotationDao.insertIntoQuotations(Arrays.asList(new Quotation(rate)));
	}

	@Override
	public List<Rate> getRates(CurrencyTypes currency, LocalDate startDate, LocalDate endDate) {
		List<Quotation> quotations;
		List<Rate> rates = new ArrayList<Rate>();

		try {
			quotations = quotationDao.findAllForCurrencyWithDataRange(currency, startDate, endDate);
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
				quotations.add(new Quotation(rate));
			}
			quotationDao.insertIntoQuotations(quotations);
		}
	}

}
