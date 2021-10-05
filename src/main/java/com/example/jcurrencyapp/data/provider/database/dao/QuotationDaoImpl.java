package com.example.jcurrencyapp.data.provider.database.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.jcurrencyapp.Config;
import com.example.jcurrencyapp.HibernateUtil;
import com.example.jcurrencyapp.model.CurrencyTypes;

//• Znajdź walutę o największej różnicy kursy w okresie
//• Znajdź minimalną/maksymalną wartość w okresie
//• Znajdź pięć najlepszych kursów dla waluty na + i -

public class QuotationDaoImpl { //Impl extends AbstractJpaDAO <Quotation> implements QuotationDao {

	/*public QuotationDaoImpl() {
		super();

		setClazz(Quotation.class);
	}*/
	
	@SuppressWarnings("unchecked")
	public List<Quotation> findAllForCurrency(CurrencyTypes currency) {
		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
		
		Query query = em.createNamedQuery(Quotation.FIND_BY_CODE);
		query.setParameter(Quotation.PARAM_CURRENCY_CODE, currency.getCode());

		return (List<Quotation>) query.getResultList();
	}
	
	public Quotation findForCurrencyAndData(CurrencyTypes currency, LocalDate date) {
		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
		
		Query query = em.createNamedQuery(Quotation.FIND_BY_CODE_AND_DATE);
		query.setParameter(Quotation.PARAM_CURRENCY_CODE, currency.getCode());
		query.setParameter(Quotation.PARAM_DATE, date);
		
		return (Quotation) query.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Quotation> findAllForCurrencyWithDataRange(CurrencyTypes currency, LocalDate startDate, LocalDate endDate) {
		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
		
		Query query = em.createNamedQuery(Quotation.FIND_BY_CODE_AND_DATE_RANGE);
		query.setParameter(Quotation.PARAM_CURRENCY_CODE, currency.getCode());
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
	
	/*@SuppressWarnings("unchecked")
	public static List<Quotation> readMaxValues(CurrencyTypes currency, int limit) {
		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		Query query = em.createNamedQuery(Quotation.FIND_MAX_BY_CODE);
		query.setMaxResults(limit);
		query.setParameter(Quotation.PARAM_CURRENCY_CODE, currency.getCode());

		return (List<Quotation>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public static List<Quotation> readMinValues(CurrencyTypes currency, int limit) {
		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		Query query = em.createNamedQuery(Quotation.FIND_MIN_BY_CODE);
		query.setMaxResults(limit);
		query.setParameter(Quotation.PARAM_CURRENCY_CODE, currency.getCode());

		return (List<Quotation>) query.getResultList();
	}*/
	
	
	/*public List<Quotation> findBestQuotationsForSellCurrency(Long currencyId, Long numbrOfQuoatations) {
		return null;
	}

	public List<Quotation> findBestQuotationsForBuyCurrency(Long currencyId, Long numbrOfQuoatations) {
		return null;
	}*/
	
}
