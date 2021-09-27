package com.example.jcurrencyapp.data.provider;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.example.jcurrencyapp.HibernateUtil;
import com.example.jcurrencyapp.model.CurrencyTypes;
import com.example.jcurrencyapp.model.Rate;
import com.example.jcurrencyapp.model.db.Country;

public class DatabaseProviderImpl implements Provider {

	@Override
	public BigDecimal getRate(CurrencyTypes code, LocalDate date) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String jpql = "SELECT u from Country u";
		 
		Query query = session.createQuery(jpql);
		 
		List<Country> result = query.getResultList();
		 
		for (Country country : result) {
		    System.out.println(country);
		}
		
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		
//		session.getMetamodel().managedType(Country.class);
//		
//		Query query = session.createNamedQuery("Country.findAll");
//		List<Country> results = query.getResultList();
//		
//		
//		
//		//List<Currency> list = HibernateUtil.findAllCurrnciesWithCriteriaQuery();
//		
//		for (Country var : results) {
//			System.out.println(var);
//		}
		
		// search for quotation by provided code and date
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveRate(Rate rate) {
		
		// search for currency via provided rate.code, if not exist create new one
		
		// search for id of quotation via provided rate.date and currency id, if not exist create new one
		
		// create/update quotation with rate.rate
		
		// TODO Auto-generated method stub
	}

}
