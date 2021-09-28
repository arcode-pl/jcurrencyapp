package com.example.jcurrencyapp.model.db;

import com.example.jcurrencyapp.model.CurrencyTypes;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "currency")

@NamedQueries({
	@NamedQuery(name = "Currency.findAll", query = "SELECT u FROM Currency u ORDER BY u.currencyCode"),
	@NamedQuery(name = "Currency.findByCode", query = "SELECT u FROM Currency u WHERE u.currencyCode = :currencyCode")
})

public class Currency {

	private Long currencyId;

	private String currencyCode;
	private List<Country> supportedCountries = new ArrayList<Country>(0);
    private List<Quotation> quotations = new ArrayList<Quotation>();
	
	public Currency() {
		super();
	}
	
	public Currency(CurrencyTypes code) {
		this.setCurrencyCode(code.toString());
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "currency_id")
	public Long getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Long currencyId) {
		this.currencyId = currencyId;
	}

	@Column(name = "currency_code", unique = true, nullable = false)
	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "officialCurrencies")
	public List<Country> getCountries() {
		return supportedCountries;
	}
	
	public void setCountries(List<Country> supportedCountries) {
		this.supportedCountries = supportedCountries;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "currency")
	public List<Quotation> getQuotations() {
		return quotations;
	}

	public void setQuotations(List<Quotation> quotations) {
		this.quotations = quotations;
	}
	
	public void addQuotation(Quotation quotation) {
        this.quotations.add(quotation);
        quotation.setCurrency(this);
    }
	
	@Override
	public String toString() {
		return "Currency [currencyId=" + currencyId + ", currencyCode=" + currencyCode + "]";
	}
}
