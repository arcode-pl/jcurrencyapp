package com.example.jcurrencyapp.data.provider.database.dao;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NaturalId;

import com.example.jcurrencyapp.model.CurrencyTypes;

@NamedQueries({ @NamedQuery(name = Currency.FIND_ALL, query = "SELECT u FROM Currency u ORDER BY u.currencyCode"),
		@NamedQuery(name = Currency.FIND_BY_CODE, query = "SELECT u FROM Currency u WHERE u.currencyCode = :"
				+ Currency.PARAM_CURRENCY_CODE) })

@Entity
@Table(name = "currency", indexes = @Index(name = "currency_index", columnList = "currencycode"), uniqueConstraints = @UniqueConstraint(name = "currency_unique", columnNames = {
		"currencycode" }))
public class Currency {

	static final String FIND_ALL = "Currency.findAll";
	static final String FIND_BY_CODE = "Currency.findByCode";

	static final String PARAM_CURRENCY_CODE = "currencyCode";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long currencyId;

	@NaturalId
	@Column(nullable = false)
	private String currencyCode;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "officialCurrencies")
	private Set<Country> supportedCountries = new HashSet<Country>();

	public Currency() {
		super();
	}

	public Currency(CurrencyTypes code) {
		this.currencyCode = code.toString();
	}

	public Long getCurrencyId() {
		return currencyId;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public Set<Country> getSupportedCountries() {
		return supportedCountries;
	}

	public void setSupportedCountries(Set<Country> supportedCountries) {
		this.supportedCountries = supportedCountries;
	}

	public void addSupportedCountries(Country country) {

		// prevent endless loop
		if (supportedCountries.contains(country)) {
			return;
		}
		
		// add new account
		supportedCountries.add(country);

		// add myself into the country
		country.addOfficialCurrency(this);
	}

	@Override
	public String toString() {
		return "Currency [currencyId=" + currencyId + ", currencyCode=" + currencyCode + "]";
	}
}
