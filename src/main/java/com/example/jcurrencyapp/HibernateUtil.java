package com.example.jcurrencyapp;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class HibernateUtil {
	private static final EntityManagerFactory entityManagerFactory = buildEntityManagerFactory();
	
	private static EntityManagerFactory buildEntityManagerFactory() {
		try {
			return Persistence.createEntityManagerFactory("jcurrency-persistance-unit");
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("EntityManagerFactory creation failed." + ex);
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
}
