package com.example.jcurrencyapp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.jcurrencyapp.data.provider.Provider;
import com.example.jcurrencyapp.data.provider.cache.CacheProviderImpl;
import com.example.jcurrencyapp.data.provider.database.DatabaseProviderImpl;
import com.example.jcurrencyapp.data.provider.database.dao.Country;
import com.example.jcurrencyapp.data.provider.database.dao.Currency;
import com.example.jcurrencyapp.data.provider.database.dao.DaoImpl;
import com.example.jcurrencyapp.data.provider.database.dao.Divergance;
import com.example.jcurrencyapp.data.provider.database.dao.Quotation;
import com.example.jcurrencyapp.data.provider.nbp.NbpJsonProviderImpl;
import com.example.jcurrencyapp.data.provider.nbp.NbpXmlProviderImpl;
import com.example.jcurrencyapp.model.CountryTypes;
import com.example.jcurrencyapp.model.CurrencyTypes;
import com.example.jcurrencyapp.model.Rate;

public class Demo {

	public static void initCurrencies() {
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
			for (CountryTypes tmpCountry : CountryTypes.values()) {
				Country country = new Country(tmpCountry.getCode());

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
	}

	public static void linkCountryCurrencies() {
		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
		Session session = em.unwrap(Session.class);
		Transaction tx = null;
		DaoImpl dao = new DaoImpl();
		
		try {
			tx = session.beginTransaction();
			
			for (CountryTypes tmpCountry : CountryTypes.values()) {
				Country country = dao.getCountry(tmpCountry);	//persisted
				for (CurrencyTypes tmpCurrency : tmpCountry.getCurrencies()) {
					Currency currency = dao.getCurrency(tmpCurrency);	//persisted
					country.addOfficialCurrency(currency);  //country seems as detached ? what about currencies?
				}
				session.merge(country);	//persisted
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

	public static void initQuotations() {
		for (CurrencyTypes val : CurrencyTypes.values()) {
			List<Rate> rates = new NbpJsonProviderImpl().getRates(val, LocalDate.of(2004, 1, 1), LocalDate.of(2005, 1, 1));
			new DatabaseProviderImpl(new DaoImpl()).saveRates(rates);
		}
	}

	public static void initTables() {
		initCurrencies();
		initCountries();
		linkCountryCurrencies();
		initQuotations();
	}

	// Example usage of API
	public static void main(String[] args) {

		// Initialize currencies, countries and quotations table in database for demo
		// usage only.
		initTables();

		DaoImpl dao = new DaoImpl();

		Optional<Rate> result;

		// Initialize application
		List<Provider> providers = Arrays.asList(new CacheProviderImpl(), new DatabaseProviderImpl(new DaoImpl()),
				new NbpJsonProviderImpl(), new NbpXmlProviderImpl());
		JCurrency jcurrency = new JCurrency(providers);

		result = jcurrency.tryExchange(CurrencyTypes.EUR, new BigDecimal("1.0"), LocalDate.now().minusDays(2));
		result.ifPresentOrElse(p -> System.out.println(p.toString()), () -> System.out.println("empty"));

		result = jcurrency.tryExchange(CurrencyTypes.USD, new BigDecimal("1.0"), LocalDate.now().minusDays(2));
		result.ifPresentOrElse(p -> System.out.println(p.toString()), () -> System.out.println("empty"));

		// Optional<CurrencyTypes> currency =
		// jcurrency.getMostUnstableCurrency(NbpParams.START_DATE, LocalDate.now());
		// currency.ifPresentOrElse(p -> System.out.println(p.toString()), () ->
		// System.out.println("empty"));

		// currency = jcurrency.getMostUnstableCurrency(NbpParams.START_DATE,
		// LocalDate.now());
		// currency.ifPresentOrElse(p -> System.out.println(p.toString()), () ->
		// System.out.println("empty"));

		List<Quotation> quotations;

		// Znajdź pięć najlepszych kursów dla waluty na kupno
		quotations = dao.getQuotationsAsc(CurrencyTypes.USD, 5);
		for (Quotation var : quotations) {
			System.out.println("BOTTOM 5: " + var);
		}

		// Znajdź pięć najlepszych kursów dla waluty na sprzedaż
		quotations = dao.getQuotationsDesc(CurrencyTypes.USD, 5);
		for (Quotation var : quotations) {
			System.out.println("TOP 5: " + var);
		}

		// Znajdź minimalną wartość w okresie
		quotations = dao.getQuotationsAsc(CurrencyTypes.USD, LocalDate.of(2004, 1, 1), LocalDate.of(2005, 1, 1), 1);
		for (Quotation var : quotations) {
			System.out.println("MIN: " + var);
		}

		// Znajdź maksymalną wartość w okresie
		quotations = dao.getQuotationsDesc(CurrencyTypes.USD, LocalDate.of(2004, 1, 1), LocalDate.of(2005, 1, 1), 1);
		for (Quotation var : quotations) {
			System.out.println("MAX: " + var);
		}
		
		//Znajdź kurs o największej różnicy kursy w okresie
		List<Divergance> divergances = dao.getMostUnstable(LocalDate.of(2004, 1, 1), LocalDate.of(2005, 1, 1));
		for (Divergance var : divergances) {
			System.out.println("MOST UNSTABLE: " + var);
		}
		
		//Znajdz kraj z conajmniej dwoma walutami
		List<Country> countries = dao.getCuntryWithMultipleCurrency();
		for (Country var : countries) {
			System.out.println("COUNTRIES : " + var);
		}
		
		HibernateUtil.shutdown();

		return;
	}}
