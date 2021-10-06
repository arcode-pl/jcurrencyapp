package com.example.jcurrencyapp.data.provider.database.dao;

import java.util.HashSet;
import java.util.Set;

//import org.hibernate.annotations.Cache;
//import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@NamedQueries({ @NamedQuery(name = Country.FIND_ALL, query = "SELECT u FROM Country u ORDER BY u.countryName"),
		@NamedQuery(name = Country.FIND_BY_COUNTRY_NAME, query = "SELECT u FROM Country u WHERE u.countryName = :"
				+ Country.PARAM_COUNTRY_NAME) })

@NamedNativeQuery(name = Country.FIND_BY_COUNTRY_NAME_NATIVE, query = "SELECT * FROM country WHERE country_name = ?", resultClass = Country.class)

@Entity
@Table(name = "country", indexes = @Index(name = "country_index", columnList = "countryname"), uniqueConstraints = @UniqueConstraint(name = "country_unique", columnNames = {
		"countryname" }))
public class Country {

	public static final String FIND_ALL = "Country.findAll";
	public static final String FIND_BY_COUNTRY_NAME = "Country.findByCountryName";
	public static final String FIND_BY_COUNTRY_NAME_NATIVE = "Country.findByCountryNameNative";

	public static final String PARAM_COUNTRY_NAME = "countryName";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long countryId;

	@Column(nullable = false)
	private String countryName;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "country_currency", joinColumns = {
			@JoinColumn(name = "countryid", nullable = false, updatable = false, foreignKey = @ForeignKey(name = "country_currency_fk")) }, inverseJoinColumns = {
					@JoinColumn(name = "currencyid", nullable = false, updatable = false, foreignKey = @ForeignKey(name = "currency_country_fk")) })
	private Set<Currency> officialCurrencies = new HashSet<Currency>();

	public Country() {
		super();
	}

	public Country(String countryName) {
		this.countryName = countryName;
	}

	public Long getCountryId() {
		return countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public Set<Currency> getOfficialCurrencies() {
		return officialCurrencies;
	}

	public void setOfficialCurrencies(Set<Currency> officialCurrencies) {
		this.officialCurrencies = officialCurrencies;
	}

	@Override
	public String toString() {
		return "Country [countryId=" + countryId + ", countryName=" + countryName + "]";
	}

	public void addOfficialCurrency(Currency currency) {
		this.officialCurrencies.add(currency);
	}
}
