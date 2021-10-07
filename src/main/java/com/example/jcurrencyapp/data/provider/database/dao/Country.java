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

import org.hibernate.annotations.BatchSize;

@NamedQueries({ @NamedQuery(name = Country.FIND_ALL, query = "SELECT u FROM Country u ORDER BY u.countryName"),
		@NamedQuery(name = Country.FIND_BY_COUNTRY_NAME, query = "SELECT u FROM Country u WHERE u.countryName = :"
				+ Country.PARAM_COUNTRY_NAME),
		@NamedQuery(name = Country.FIND_COUNTRY_WITH_MULTIPLE_CURRENCY, query = "SELECT u, SIZE(u.officialCurrencies) FROM Country u WHERE SIZE(u.officialCurrencies) > 1") })

@NamedNativeQuery(name = Country.FIND_BY_COUNTRY_NAME_NATIVE, query = "SELECT * FROM country WHERE country_name = ?", resultClass = Country.class)

@Entity
@Table(name = "country", indexes = @Index(name = "country_index", columnList = "countryname"), uniqueConstraints = @UniqueConstraint(name = "country_unique", columnNames = {
		"countryname" }))
public class Country {

	static final String FIND_ALL = "Country.findAll";
	static final String FIND_BY_COUNTRY_NAME = "Country.findByCountryName";
	static final String FIND_BY_COUNTRY_NAME_NATIVE = "Country.findByCountryNameNative";
	static final String FIND_COUNTRY_WITH_MULTIPLE_CURRENCY = "Country.findCountryWithMultipleCyrrency";

	static final String PARAM_COUNTRY_NAME = "countryName";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long countryId;

	@Column(nullable = false)
	private String countryName;

//	Select = from Quotation, from Currency ... wszystkozewszystkim
//	SubSelect = on collection only (zagniezdzone selecty)
//	Join = outer join
//	
//	FetchType: LAZY - when access / EAGER - on entity load for all above
//	
//	BatchSize for all above
			
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@Fetch(FetchMode.SELECT) FetchMode.SUBSELECT, FetchMode.JOIN (outerJoin (left/right/full OneToMany,ManyToOne,ManyToMany)
	@BatchSize(size = 10)
	@JoinTable(name = "country_currency", joinColumns = {
			@JoinColumn(name = "countryid", nullable = false, updatable = true, foreignKey = @ForeignKey(name = "country_currency_fk")) }, inverseJoinColumns = {
					@JoinColumn(name = "currencyid", nullable = false, updatable = true, foreignKey = @ForeignKey(name = "currency_country_fk")) })
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
		// prevent endless loop
		if (officialCurrencies.contains(currency)) {
			return;
		}

		// add new account
		officialCurrencies.add(currency);

		// add myself into the country
		currency.addSupportedCountries(this);
	}
}
