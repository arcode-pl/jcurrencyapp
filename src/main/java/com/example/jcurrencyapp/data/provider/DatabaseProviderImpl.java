package com.example.jcurrencyapp.data.provider;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.jcurrencyapp.HibernateUtil;
import com.example.jcurrencyapp.exceptions.DatabaseProviderException;
import com.example.jcurrencyapp.model.CurrencyTypes;
import com.example.jcurrencyapp.model.Rate;
import com.example.jcurrencyapp.model.db.Currency;
import com.example.jcurrencyapp.model.db.Quotation;

public class DatabaseProviderImpl implements Provider {

	public Currency readCurrency(CurrencyTypes code) {
		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		Query query = em.createNamedQuery(Currency.FIND_BY_CODE);
		query.setParameter(Currency.PARAM_CURRENCY_CODE, code.toString());

		return (Currency) query.getSingleResult();
	}

	public Quotation readQuotation(Currency currency, LocalDate date) {
		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		Query query = em.createNamedQuery(Quotation.FIND_BY_CODE_AND_DATE);
		query.setParameter(Quotation.PARAM_DATE, date);
		query.setParameter(Quotation.PARAM_CURRENCY, currency);

		return (Quotation) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Quotation> readQuotation(Currency currency, LocalDate startDate, LocalDate endDate) {
		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		Query query = em.createNamedQuery(Quotation.FIND_BY_CODE_AND_DATE_RANGE);
		query.setParameter(Quotation.PARAM_START_DATE, startDate);
		query.setParameter(Quotation.PARAM_END_DATE, endDate);
		query.setParameter(Quotation.PARAM_CURRENCY, currency);

		return (List<Quotation>) query.getResultList();
	}

	@Override
	public BigDecimal getRate(CurrencyTypes code, LocalDate date) {
		try {
			return this.readQuotation(this.readCurrency(code), date).getRate();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void saveRate(Rate rate) {
		Currency currency;
		Quotation quotation;

		try {
			currency = this.readCurrency(rate.getCode());
		} catch (NoResultException e) {
			throw new DatabaseProviderException("Any currency in database for code = " + rate.getCode(), new Throwable());
		}

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
		Session session = em.unwrap(Session.class);
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			quotation = new Quotation(currency, rate.getDate(), rate.getRate());
			session.persist(quotation);
			tx.commit();
			currency.addQuotation(quotation);
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
	}

	@Override
	public List<Rate> getRates(CurrencyTypes code, LocalDate startDate, LocalDate endDate) {
		List<Quotation> quotations;
		List<Rate> rates = new ArrayList<Rate>();

		try {
			quotations = this.readQuotation(this.readCurrency(code), startDate, endDate);
			for (Quotation quotation : quotations) {
				rates.add(
						new Rate(quotation.getCurrency().getCurrencyCode(), 
								quotation.getDate(), 
								quotation.getRate()));
			}

		} catch (NoResultException e) {
			return null;
		}

		return rates;
	}

	@Override
	public void saveRates(List<Rate> rates) {
		if (rates != null) {
			for (Rate rate : rates) {
				this.saveRate(rate);
			}
		}
	}

}
