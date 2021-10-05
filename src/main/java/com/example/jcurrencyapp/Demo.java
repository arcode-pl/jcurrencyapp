package com.example.jcurrencyapp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.example.jcurrencyapp.data.provider.Provider;
import com.example.jcurrencyapp.data.provider.cache.CacheProviderImpl;
import com.example.jcurrencyapp.data.provider.database.DatabaseProviderImpl;
import com.example.jcurrencyapp.data.provider.database.model.Quotation;
import com.example.jcurrencyapp.data.provider.nbp.NbpJsonProviderImpl;
import com.example.jcurrencyapp.data.provider.nbp.NbpXmlProviderImpl;
import com.example.jcurrencyapp.model.CurrencyTypes;
import com.example.jcurrencyapp.model.Rate;

//• Znajdź kurs o największej różnicy kursy w okresie
//• Znajdź minimalną/maksymalną wartość w okresie
//• Znajdź pięć najlepszych kursów dla waluty na + i -
//• Znajdź kraj z co najmniej dwiema walutami.
//• Zaimportować kurs walut za ostatnie 1.000000 kursów

public class Demo {
	
	/*public static void initCurrencies() {
		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
		Session session = em.unwrap(Session.class);
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			int i = 0;
			for (CurrencyTypes val : CurrencyTypes.values()) {
				Currency currency = new Currency(val);
				session.persist(currency);
				if (++i % Config.BATCH_SIZE == 0) {
					session.flush();
					session.clear();
				}
			}

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public static void initCountries() {
		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
		Session session = em.unwrap(Session.class);
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			int i = 0;
			for (String val : Locale.getISOCountries()) {
				Country country = new Country(val);
				session.persist(country);
				if (++i % Config.BATCH_SIZE == 0) {
					session.flush();
					session.clear();
				}
			}

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}*/

	public static void initQuotations() {
		for (CurrencyTypes val : CurrencyTypes.values()) {
			List<Rate> rates = new NbpJsonProviderImpl().getRates(val, LocalDate.of(2002, 1, 1), LocalDate.now());
			new DatabaseProviderImpl().saveRates(rates);
		}
	}
	
	public static void initTables() {
		//initCurrencies();
		//initCountries();
		initQuotations();
	}

	/*public static Currency readCurrency(CurrencyTypes code) {
		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
		
		Query query = em.createNamedQuery(Currency.FIND_BY_CODE);
		query.setParameter(Currency.PARAM_CURRENCY_CODE, code.toString());

		return (Currency) query.getSingleResult();
	}*/

	@SuppressWarnings("unchecked")
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
	}
	
	/*@SuppressWarnings("unchecked")
	public static List<Country> getCountryFromDatabase(String name) {
		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		Query q = em.createNamedQuery(Country.FIND_BY_COUNTRY_NAME_NATIVE);
		q.setParameter(1, name);

		return (List<Country>) q.getResultList();
	}*/

	// Example usage of API
	public static void main(String[] args) {

		// INIT CURRENCIES AND COUTRIES
		initTables();

		Optional<Rate> result;

		// INIT WITH PROVIDERS
		List<Provider> providers = Arrays.asList(new CacheProviderImpl(), new DatabaseProviderImpl(),
				new NbpJsonProviderImpl(), new NbpXmlProviderImpl());
		JCurrency jcurrency = new JCurrency(providers);
		
//		for (Quotation var : readMaxValues(readCurrency(CurrencyTypes.EUR), 5)) {
//			System.out.println(var);
//		}
//		
//		for (Quotation var : readMinValues(readCurrency(CurrencyTypes.EUR), 5)) {
//			System.out.println(var);
//		}
		
		result = jcurrency.tryExchange(CurrencyTypes.EUR, new BigDecimal("1.0"), LocalDate.now().minusDays(2));
		result.ifPresentOrElse(p -> System.out.println(p.toString()), () -> System.out.println("empty"));

		result = jcurrency.tryExchange(CurrencyTypes.USD, new BigDecimal("1.0"), LocalDate.now().minusDays(2));
		result.ifPresentOrElse(p -> System.out.println(p.toString()), () -> System.out.println("empty"));

		HibernateUtil.shutdown();

		return;
	}
}
