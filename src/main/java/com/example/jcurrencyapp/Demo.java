package com.example.jcurrencyapp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.jcurrencyapp.data.provider.CacheProviderImpl;
import com.example.jcurrencyapp.data.provider.DatabaseProviderImpl;
import com.example.jcurrencyapp.data.provider.NbpJsonProviderImpl;
import com.example.jcurrencyapp.data.provider.NbpXmlProviderImpl;
import com.example.jcurrencyapp.data.provider.Provider;
import com.example.jcurrencyapp.model.CurrencyTypes;
import com.example.jcurrencyapp.model.Rate;
import com.example.jcurrencyapp.model.db.Country;
import com.example.jcurrencyapp.model.db.Currency;

public class Demo {

	public static void init_tables() {
		// This is test code - to remove
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;

		List<Currency> currencies = new ArrayList<Currency>();
		Set<Currency> officialCurrencies = null;

		try {
			tx = session.beginTransaction();
			for (CurrencyTypes val : CurrencyTypes.values()) {
				Currency currency = new Currency(val);
				currencies.add(currency);
				session.save(currency);
			}

			String[] countryCodes = Locale.getISOCountries();
			for (String code : countryCodes) {
				officialCurrencies = new HashSet<Currency>(0);
				officialCurrencies.add(currencies.get(0));

				Country country = new Country(code);
				// country.setOfficialCurrencies(officialCurrencies);
				session.save(country);
			}

			tx.commit();

		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		}
	}

	// Example usage of API
	public static void main(String[] args) {

		init_tables();

		Optional<Rate> result;

		// JSON
//		JCurrency jcurrency = new JCurrency();
//		result = jcurrency.tryExchange(CurrencyTypes.EUR, new BigDecimal("1.0"), LocalDate.now().minusDays(2));
//		result.ifPresentOrElse(p -> System.out.println(p.toString()), () -> System.out.println("empty"));

		List<Provider> providers = Arrays.asList(new DatabaseProviderImpl(), new CacheProviderImpl(),
				new NbpJsonProviderImpl(), new NbpXmlProviderImpl());
		JCurrency jcurrency = new JCurrency(providers);
		
		jcurrency.updateRatesFromProvider(new NbpJsonProviderImpl(), CurrencyTypes.EUR, LocalDate.of(2002, 1, 1), LocalDate.now());
		
		/*LocalDate date = LocalDate.now();
		for (int i = 0; i < 50; i++) {
			result = jcurrency.tryExchange(CurrencyTypes.USD, new BigDecimal("1.0"), date);
			date = date.minusDays(1);
		}
		
		date = LocalDate.now();
		for (int i = 0; i < 50; i++) {
			result = jcurrency.tryExchange(CurrencyTypes.USD, new BigDecimal("1.0"), date);
			date = date.minusDays(1);
			System.out.println(result);
		}*/
		
		result = jcurrency.tryExchange(CurrencyTypes.EUR, new BigDecimal("1.0"), LocalDate.now().minusDays(2));
		result.ifPresentOrElse(p -> System.out.println(p.toString()), () -> System.out.println("empty"));

		HibernateUtil.shutdown();

		return;
	}
}
