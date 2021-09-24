package com.example.jcurrencyapp;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.jcurrencyapp.model.CurrencyTypes;
import com.example.jcurrencyapp.model.db.Country;
import com.example.jcurrencyapp.model.db.Currency;

//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import com.example.jcurrencyapp.data.provider.CacheProviderImpl;
//import com.example.jcurrencyapp.data.provider.NbpJsonProviderImpl;
//import com.example.jcurrencyapp.data.provider.NbpXmlProviderImpl;
//import com.example.jcurrencyapp.data.provider.Provider;
//import com.example.jcurrencyapp.model.CurrencyTypes;
//import com.example.jcurrencyapp.model.Rate;

public class Demo {

	// Example usage of API
	public static void main(String[] args) {

		/*
		 * Optional<Rate> result;
		 * 
		 * // JSON JCurrency jcurrency = new JCurrency(); result =
		 * jcurrency.tryExchange(CurrencyTypes.EUR, new BigDecimal("1.0"),
		 * LocalDate.now().minusDays(2)); result.ifPresentOrElse(p ->
		 * System.out.println(p.toString()), () -> System.out.println("empty"));
		 * 
		 * List<Provider> providers = Arrays.asList( new CacheProviderImpl(), new
		 * NbpJsonProviderImpl(), new NbpXmlProviderImpl()); jcurrency = new
		 * JCurrency(providers); result = jcurrency.tryExchange(CurrencyTypes.USD, new
		 * BigDecimal("2.0"), LocalDate.now()); result.ifPresentOrElse(p ->
		 * System.out.println(p.toString()), () -> System.out.println("empty"));
		 */

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		for (int i = 0; i < 99; i++) {
			Country country = new Country("Polska");
			session.save(country);
			if (i % 20 == 0) { // 20, same as the JDBC batch size
				// flush a batch of inserts and release memory:
				session.flush();
				session.clear();
			}
		}

		for (CurrencyTypes val : CurrencyTypes.values()) {
			Currency currency = new Currency(val);
			session.save(currency);
		}

		tx.commit();
		HibernateUtil.shutdown();

		return;
	}
}
