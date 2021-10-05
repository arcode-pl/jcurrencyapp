package com.example.jcurrencyapp.data.provider.database.dao;

import java.util.List;

import com.example.jcurrencyapp.data.provider.database.model.Quotation;

//• Znajdź walutę o największej różnicy kursy w okresie
//• Znajdź minimalną/maksymalną wartość w okresie
//• Znajdź pięć najlepszych kursów dla waluty na + i -

public class QuotationDaoImpl extends AbstractJpaDAO <Quotation> implements QuotationDao {

	public QuotationDaoImpl() {
		super();

		setClazz(Quotation.class);
	}
	
	public List<Quotation> findBestQuotationsForSellCurrency(Long currencyId, Long numbrOfQuoatations) {
		return null;
	}

	public List<Quotation> findBestQuotationsForBuyCurrency(Long currencyId, Long numbrOfQuoatations) {
		return null;
	}
	
}
