package com.example.jcurrencyapp;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.example.jcurrencyapp.model.db.*;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			return new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}

	public static List<Currency> findAllCurrnciesWithCriteriaQuery() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<Currency> currencies = null;

		try {
			tx = session.beginTransaction();

//			session.getNamedQuery(null)
	
			session.createNativeQuery(null);
			
			
			
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Currency> cq = cb.createQuery(Currency.class);
			Root<Currency> rootEntry = cq.from(Currency.class);
			CriteriaQuery<Currency> all = cq.select(rootEntry);

			TypedQuery<Currency> allQuery = session.createQuery(all);
			currencies = allQuery.getResultList();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		}
		
		return currencies;
	}
}
