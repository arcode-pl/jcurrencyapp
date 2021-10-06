package com.example.jcurrencyapp.data.provider.database.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.jcurrencyapp.Config;
import com.example.jcurrencyapp.HibernateUtil;
import com.example.jcurrencyapp.model.CountryTypes;
import com.example.jcurrencyapp.model.CurrencyTypes;

public class DaoImpl implements Dao {

	public Quotation findForCurrencyAndData(CurrencyTypes currency, LocalDate date) {
		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		Query query = em.createNamedQuery(Quotation.FIND_BY_CODE_AND_DATE);
		query.setParameter(Quotation.PARAM_CURRENCY, getCurrency(currency));
		query.setParameter(Quotation.PARAM_DATE, date);

		return (Quotation) query.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Quotation> findAllForCurrency(CurrencyTypes currency) {
		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		Query query = em.createNamedQuery(Quotation.FIND_BY_CODE);
		query.setParameter(Quotation.PARAM_CURRENCY, getCurrency(currency));

		return (List<Quotation>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Quotation> findAllForCurrencyWithDataRange(CurrencyTypes currency, LocalDate startDate,
			LocalDate endDate) {
		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		Query query = em.createNamedQuery(Quotation.FIND_BY_CODE_AND_DATE_RANGE);
		query.setParameter(Quotation.PARAM_CURRENCY, getCurrency(currency));
		query.setParameter(Quotation.PARAM_START_DATE, startDate);
		query.setParameter(Quotation.PARAM_END_DATE, endDate);

		return (List<Quotation>) query.getResultList();
	}

	public void insertIntoQuotations(List<Quotation> quotations) {
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

	public Currency getCurrency(CurrencyTypes currency) {
		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		Query query = em.createNamedQuery(Currency.FIND_BY_CODE);
		query.setParameter(Currency.PARAM_CURRENCY_CODE, currency.getCode());

		return (Currency) query.getSingleResult();
	}

	public Country getCountry(CountryTypes country) {
		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		Query query = em.createNamedQuery(Country.FIND_BY_COUNTRY_NAME);
		query.setParameter(Country.PARAM_COUNTRY_NAME, country.getCode());

		return (Country) query.getSingleResult();
	}

	/*
	 * @SuppressWarnings("unchecked") public static List<Quotation>
	 * readMaxValues(CurrencyTypes currency, int limit) { EntityManager em =
	 * HibernateUtil.getEntityManagerFactory().createEntityManager();
	 * 
	 * Query query = em.createNamedQuery(Quotation.FIND_MAX_BY_CODE);
	 * query.setMaxResults(limit); query.setParameter(Quotation.PARAM_CURRENCY_CODE,
	 * currency.getCode());
	 * 
	 * return (List<Quotation>) query.getResultList(); }
	 * 
	 * @SuppressWarnings("unchecked") public static List<Quotation>
	 * readMinValues(CurrencyTypes currency, int limit) { EntityManager em =
	 * HibernateUtil.getEntityManagerFactory().createEntityManager();
	 * 
	 * Query query = em.createNamedQuery(Quotation.FIND_MIN_BY_CODE);
	 * query.setMaxResults(limit); query.setParameter(Quotation.PARAM_CURRENCY_CODE,
	 * currency.getCode());
	 * 
	 * return (List<Quotation>) query.getResultList(); }
	 */

	/*
	 * public List<Quotation> findBestQuotationsForSellCurrency(Long currencyId,
	 * Long numbrOfQuoatations) { return null; }
	 * 
	 * public List<Quotation> findBestQuotationsForBuyCurrency(Long currencyId, Long
	 * numbrOfQuoatations) { return null; }
	 */

}
