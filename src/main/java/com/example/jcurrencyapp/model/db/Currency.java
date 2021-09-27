package com.example.jcurrencyapp.model.db;

import com.example.jcurrencyapp.model.CurrencyTypes;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "currency")
public class Currency {

	private Long currencyId;
	private String currencyCode;
//	private Set<Country> supportedCountries = new HashSet<Country>(0);
	
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

	@Override
	public String toString() {
		return "Currency [currencyId=" + currencyId + ", currencyCode=" + currencyCode + "]";
	}
	
//	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "officialCurrencies")
//	public Set<Country> getCountries() {
//		return supportedCountries;
//	}
//	
//	public void setCountries(Set<Country> supportedCountries) {
//		this.supportedCountries = supportedCountries;
//	}
}
