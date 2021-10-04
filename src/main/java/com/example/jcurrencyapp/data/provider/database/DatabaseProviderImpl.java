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
import com.example.jcurrencyapp.data.provider.database.model.Currency;
import com.example.jcurrencyapp.data.provider.database.model.Quotation;
import com.example.jcurrencyapp.exceptions.DatabaseProviderException;
import com.example.jcurrencyapp.model.CurrencyTypes;
import com.example.jcurrencyapp.model.Rate;

public class DatabaseProviderImpl implements Provider {

	private Currency readCurrency(CurrencyTypes code) {
		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
		// TODO: is this correct approach?
		if (!em.isJoinedToTransaction()) {
			em.joinTransaction();
		}

		Query query = em.createNamedQuery(Currency.FIND_BY_CODE);
		query.setParameter(Currency.PARAM_CURRENCY_CODE, code.toString());

		return (Currency) query.getSingleResult();
	}

	private Quotation readQuotation(Currency currency, LocalDate date) {
		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
		// TODO: is this correct approach?
		if (!em.isJoinedToTransaction()) {
			em.joinTransaction();
		}

		Query query = em.createNamedQuery(Quotation.FIND_BY_CODE_AND_DATE);
		query.setParameter(Quotation.PARAM_DATE, date);
		query.setParameter(Quotation.PARAM_CURRENCY, currency);

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

			// Synchronize persisted object, please check how it works
//			for (Quotation quotation : quotations) {
//				quotation.getCurrency().addQuotation(quotation);
//			}
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	private List<Quotation> readQuotations(Currency currency, LocalDate startDate, LocalDate endDate) {
		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		Query query = em.createNamedQuery(Quotation.FIND_BY_CODE_AND_DATE_RANGE);
		query.setParameter(Quotation.PARAM_START_DATE, startDate);
		query.setParameter(Quotation.PARAM_END_DATE, endDate);
		query.setParameter(Quotation.PARAM_CURRENCY, currency);

		return (List<Quotation>) query.getResultList();
	}

	private Quotation toQuotation(Rate rate) {
		Currency currency;

		try {
			currency = this.readCurrency(rate.getCode());
			return new Quotation(currency, rate.getDate(), rate.getRate());
		} catch (NoResultException e) {
			throw new DatabaseProviderException("Issue in toQuotation", new Throwable());
		}
	}

	/* PROVIDER SECTION */

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
		saveQuotations(Arrays.asList(toQuotation(rate)));
	}

	@Override
	public List<Rate> getRates(CurrencyTypes code, LocalDate startDate, LocalDate endDate) {
		List<Quotation> quotations;
		List<Rate> rates = new ArrayList<Rate>();

		try {
			quotations = this.readQuotations(this.readCurrency(code), startDate, endDate);
			for (Quotation quotation : quotations) {
				rates.add(
						new Rate(quotation.getCurrency().getCurrencyCode(), quotation.getDate(), quotation.getRate()));
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
				quotations.add(toQuotation(rate));
			}
			saveQuotations(quotations);
		}
	}

}
