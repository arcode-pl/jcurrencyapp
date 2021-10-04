package com.example.jcurrencyapp.data.provider.database.dao;

import com.example.jcurrencyapp.data.provider.database.model.Quotation;

public class QuotationDaoImpl extends AbstractJpaDAO <Quotation> implements QuotationDao {

	public QuotationDaoImpl() {
		super();

		setClazz(Quotation.class);
	}

}
