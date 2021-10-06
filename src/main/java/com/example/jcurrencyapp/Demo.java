package com.example.jcurrencyapp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.jcurrencyapp.data.provider.Provider;
import com.example.jcurrencyapp.data.provider.cache.CacheProviderImpl;
import com.example.jcurrencyapp.data.provider.database.DatabaseProviderImpl;
import com.example.jcurrencyapp.data.provider.database.dao.Quotation;
import com.example.jcurrencyapp.data.provider.database.dao.Country;
import com.example.jcurrencyapp.data.provider.database.dao.Currency;
import com.example.jcurrencyapp.data.provider.database.dao.DaoImpl;
import com.example.jcurrencyapp.data.provider.nbp.NbpJsonProviderImpl;
import com.example.jcurrencyapp.data.provider.nbp.NbpParams;
import com.example.jcurrencyapp.data.provider.nbp.NbpXmlProviderImpl;
import com.example.jcurrencyapp.model.CountryTypes;
import com.example.jcurrencyapp.model.CurrencyTypes;
import com.example.jcurrencyapp.model.Rate;

//• Znajdź kurs o największej różnicy kursy w okresie
//• Znajdź minimalną/maksymalną wartość w okresie
//• Znajdź pięć najlepszych kursów dla waluty na + i -
//• Znajdź kraj z co najmniej dwiema walutami.
//• Zaimportować kurs walut za ostatnie 1.000000 kursów

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
		DaoImpl dao = new DaoImpl();

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
		DaoImpl dao = new DaoImpl();
		for (CountryTypes tmpCountry : CountryTypes.values()) {
			Country country = dao.getCountry(tmpCountry);
			for (CurrencyTypes tmpCurrency : tmpCountry.getCurrencies()) {
				Currency currency = dao.getCurrency(tmpCurrency);
				currency.addSupportedCountries(country);
				country.addOfficialCurrency(currency);
			}
		}
	}

	public static void initQuotations() {
		for (CurrencyTypes val : CurrencyTypes.values()) {
			List<Rate> rates = new NbpJsonProviderImpl().getRates(val, LocalDate.of(2019, 1, 1), LocalDate.now());
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

		Optional<Rate> result;

		// Initialize application
		List<Provider> providers = Arrays.asList(new CacheProviderImpl(), new DatabaseProviderImpl(new DaoImpl()),
				new NbpJsonProviderImpl(), new NbpXmlProviderImpl());
		JCurrency jcurrency = new JCurrency(providers);

		result = jcurrency.tryExchange(CurrencyTypes.EUR, new BigDecimal("1.0"), LocalDate.now().minusDays(2));
		result.ifPresentOrElse(p -> System.out.println(p.toString()), () -> System.out.println("empty"));

		result = jcurrency.tryExchange(CurrencyTypes.USD, new BigDecimal("1.0"), LocalDate.now().minusDays(2));
		result.ifPresentOrElse(p -> System.out.println(p.toString()), () -> System.out.println("empty"));

		Optional<CurrencyTypes> currency = jcurrency.getMostUnstableCurrency(NbpParams.START_DATE, LocalDate.now());
		currency.ifPresentOrElse(p -> System.out.println(p.toString()), () -> System.out.println("empty"));

		currency = jcurrency.getMostUnstableCurrency(NbpParams.START_DATE, LocalDate.now());
		currency.ifPresentOrElse(p -> System.out.println(p.toString()), () -> System.out.println("empty"));

		HibernateUtil.shutdown();

		return;
	}
}
