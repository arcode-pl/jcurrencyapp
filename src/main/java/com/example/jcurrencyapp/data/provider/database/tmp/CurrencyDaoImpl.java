package com.example.jcurrencyapp.data.provider.database.tmp;
/*package com.example.jcurrencyapp.data.provider.database.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.example.jcurrencyapp.HibernateUtil;
import com.example.jcurrencyapp.data.provider.database.model.Country;
import com.example.jcurrencyapp.data.provider.database.model.Currency;
import com.example.jcurrencyapp.model.CurrencyTypes;

public class CurrencyDaoImpl extends AbstractJpaDAO <Currency> implements CurrencyDao {
	
	public CurrencyDaoImpl() {
		super();

		setClazz(Currency.class);
	}
	
	public Currency readCurrency(CurrencyTypes code) {
		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
		// TODO: is this correct approach?
		if (!em.isJoinedToTransaction()) {
			em.joinTransaction();
		}

		Query query = em.createNamedQuery(Currency.FIND_BY_CODE);
		query.setParameter(Currency.PARAM_CURRENCY_CODE, code.toString());

		return (Currency) query.getSingleResult();
	}
}*/
