package com.example.jcurrencyapp.data.provider.database.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
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

	@Override
	public Quotation getQuotation(CurrencyTypes currency, LocalDate date) {
		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		Query query = em.createNamedQuery(Quotation.FIND_BY_CODE_AND_DATE);
		query.setParameter(Quotation.PARAM_CURRENCY, getCurrency(currency));
		query.setParameter(Quotation.PARAM_DATE, date);

		return (Quotation) query.getSingleResult();
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Quotation> getQuotations(CurrencyTypes currency) {
		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		Query query = em.createNamedQuery(Quotation.FIND_BY_CODE);
		query.setParameter(Quotation.PARAM_CURRENCY, getCurrency(currency));

		return (List<Quotation>) query.getResultList();
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Quotation> getQuotations(CurrencyTypes currency, LocalDate startDate, LocalDate endDate) {
		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		Query query = em.createNamedQuery(Quotation.FIND_BY_CODE_AND_DATE_RANGE);
		query.setParameter(Quotation.PARAM_CURRENCY, getCurrency(currency));
		query.setParameter(Quotation.PARAM_START_DATE, startDate);
		query.setParameter(Quotation.PARAM_END_DATE, endDate);

		return (List<Quotation>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Quotation> getQuotationsAsc(CurrencyTypes currency, int numberOfResult) {
		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		Query query = em.createNamedQuery(Quotation.FIND_BY_CODE_ASC_ORDER_BY_PRICE);
		query.setParameter(Quotation.PARAM_CURRENCY, getCurrency(currency));
		query.setMaxResults(numberOfResult);
		
		return (List<Quotation>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Quotation> getQuotationsAsc(CurrencyTypes currency, LocalDate startDate, LocalDate endDate, int numberOfResult) {
		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		Query query = em.createNamedQuery(Quotation.FIND_BY_CODE_AND_DATE_RANGE_ASC_ORDER_BY_PRICE);
		query.setParameter(Quotation.PARAM_CURRENCY, getCurrency(currency));
		query.setParameter(Quotation.PARAM_START_DATE, startDate);
		query.setParameter(Quotation.PARAM_END_DATE, endDate);
		query.setMaxResults(numberOfResult);
		
		return (List<Quotation>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Quotation> getQuotationsDesc(CurrencyTypes currency, int numberOfResult) {
		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		Query query = em.createNamedQuery(Quotation.FIND_BY_CODE_DESC_ORDER_BY_PRICE);
		query.setParameter(Quotation.PARAM_CURRENCY, getCurrency(currency));
		query.setMaxResults(numberOfResult);
		
		return (List<Quotation>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Quotation> getQuotationsDesc(CurrencyTypes currency, LocalDate startDate, LocalDate endDate, int numberOfResult) {
		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		Query query = em.createNamedQuery(Quotation.FIND_BY_CODE_AND_DATE_RANGE_DESC_ORDER_BY_PRICE);
		query.setParameter(Quotation.PARAM_CURRENCY, getCurrency(currency));
		query.setParameter(Quotation.PARAM_START_DATE, startDate);
		query.setParameter(Quotation.PARAM_END_DATE, endDate);
		query.setMaxResults(numberOfResult);
		
		return (List<Quotation>) query.getResultList();
	}

	@Override
	public void addQuotations(List<Quotation> quotations) {
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

	@Override
	public Currency getCurrency(CurrencyTypes currency) {
		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		Query query = em.createNamedQuery(Currency.FIND_BY_CODE);
		query.setParameter(Currency.PARAM_CURRENCY_CODE, currency.getCode());

		return (Currency) query.getSingleResult();
	}

	@Override
	public Country getCountry(CountryTypes country) {
		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		Query query = em.createNamedQuery(Country.FIND_BY_COUNTRY_NAME);
		query.setParameter(Country.PARAM_COUNTRY_NAME, country.getCode());

		return (Country) query.getSingleResult();
	}

	@Override
	public Country updateCountry(Country country) {
		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		return em.merge(country);
	}
	
	@Override
	public Currency updateCurrency(Currency currency) {
		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		return em.merge(currency);
	}
	
	@Override
	public Quotation updateQuotation(Quotation quotation) {
		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		return em.merge(quotation);
	}
	
	@SuppressWarnings("unchecked")
	public List<Divergance> getMostUnstable(LocalDate startDate, LocalDate endDate) {
		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		Query query = em.createNamedQuery(Quotation.FIND_MOST_UNSTABLE_CURRENCY_BY_DATE_RANGE);
		query.setParameter(Quotation.PARAM_START_DATE, startDate);
		query.setParameter(Quotation.PARAM_END_DATE, endDate);

		List<Object[]> resultList = (List<Object[]>) query.getResultList();
		List<Divergance> divergances = new ArrayList<Divergance>();
		for (Object[] var : resultList) {
			divergances.add(new Divergance((Currency) var[0], (BigDecimal) var[1]));
		}
		
		return divergances;
	}

	@SuppressWarnings("unchecked")
	public List<Country> getCuntryWithMultipleCurrency() {
		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		Query query = em.createNamedQuery(Country.FIND_COUNTRY_WITH_MULTIPLE_CURRENCY);
		
		List<Object[]> resultList = query.getResultList();
		List<Country> response = new ArrayList<Country>();
		for (Object[] var : resultList) {
			response.add((Country) var[0]);
		}
		
		return response;
	}
}
