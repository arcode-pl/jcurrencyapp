package com.example.jcurrencyapp;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class HibernateUtil {
	private static final EntityManagerFactory entityManagerFactory = buildEntityManagerFactory();
	
	private static EntityManagerFactory buildEntityManagerFactory() {
		try {
			// Create the SessionFactory from persistance.xml
			return Persistence.createEntityManagerFactory("jcurrency-persistance-unit");
			//Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public static void shutdown() {
		// Close caches and connection pools
		getEntityManagerFactory().close();
	}

	/*public static List<Currency> findAllCurrnciesWithCriteriaQuery() {
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
	}*/
}
