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
import com.example.jcurrencyapp.data.provider.database.model.Quotation;
import com.example.jcurrencyapp.model.CurrencyTypes;
import com.example.jcurrencyapp.model.Rate;

public class DatabaseProviderImpl implements Provider {

	private Quotation readQuotation(CurrencyTypes currency, LocalDate date) {
		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		Query query = em.createNamedQuery(Quotation.FIND_BY_CODE_AND_DATE);
		query.setParameter(Quotation.PARAM_DATE, date);
		query.setParameter(Quotation.PARAM_CURRENCY_CODE, currency.getCode());

		return (Quotation) query.getSingleResult();
	}

	private void saveQuotations(List<Quotation> quotations) {
		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
		Session session = em.unwrap(Session.class);
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			int i = 0;
			for (Quotation quotation : quotations) {
				session.persist(quotation);
				if (++i % Config.BATCH_SIZE == 0) {
					session.flush();
					session.clear();
				}
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	private List<Quotation> readQuotations(CurrencyTypes currency, LocalDate startDate, LocalDate endDate) {
		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		Query query = em.createNamedQuery(Quotation.FIND_BY_CODE_AND_DATE_RANGE);
		query.setParameter(Quotation.PARAM_START_DATE, startDate);
		query.setParameter(Quotation.PARAM_END_DATE, endDate);
		query.setParameter(Quotation.PARAM_CURRENCY_CODE, currency.getCode());

		return (List<Quotation>) query.getResultList();
	}

	/* PROVIDER SECTION */

	@Override
	public BigDecimal getPrice(CurrencyTypes currency, LocalDate date) {
		try {
			return this.readQuotation(currency, date).getPrice();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void saveRate(Rate rate) {
		saveQuotations(Arrays.asList(new Quotation(rate)));
	}

	@Override
	public List<Rate> getRates(CurrencyTypes currency, LocalDate startDate, LocalDate endDate) {
		List<Quotation> quotations;
		List<Rate> rates = new ArrayList<Rate>();

		try {
			quotations = this.readQuotations(currency, startDate, endDate);
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
			saveQuotations(quotations);
		}
	}

}
