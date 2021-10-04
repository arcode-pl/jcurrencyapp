package com.example.jcurrencyapp.data.provider.database.model;

import java.util.HashSet;
import java.util.Set;

//import org.hibernate.annotations.Cache;
//import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@NamedQueries({
	@NamedQuery(name = Country.FIND_ALL, query = "SELECT u FROM Country u ORDER BY u.countryName"),
	@NamedQuery(name = Country.FIND_BY_COUNTRY_NAME, query = "SELECT u FROM Country u WHERE u.countryName = :" + Country.PARAM_COUNTRY_NAME)
})

@NamedNativeQuery(name = Country.FIND_BY_COUNTRY_NAME_NATIVE, query = "SELECT * FROM country WHERE country_name = ?", resultClass = Country.class)

@Entity
//@Cacheable
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "country", indexes = 
	@Index(name = "country_index", columnList = "country_name", unique = true) )
public class Country {

	public static final String FIND_ALL = "Country.findAll";
	public static final String FIND_BY_COUNTRY_NAME = "Country.findByCountryName";
	public static final String FIND_BY_COUNTRY_NAME_NATIVE = "Country.findByCountryNameNative";
	
	public static final String PARAM_COUNTRY_NAME = "countryName";
	
	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE)    
	@Column(name = "country_id")
	private Long countryId;
	
	@Column(name = "country_name", nullable = false)
	private String countryName;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "country_currency", 
		joinColumns = {@JoinColumn(name = "country_id", nullable = false, updatable = false, foreignKey = @ForeignKey(name = "country_currency_fk")) }, 
		inverseJoinColumns = {@JoinColumn(name = "currency_id", nullable = false, updatable = false, foreignKey = @ForeignKey(name = "currency_country_fk")) })
	@Fetch(FetchMode.JOIN)
	@BatchSize(size = 25)
	private Set<Currency> officialCurrencies = new HashSet<Currency>();

	public Country() {
		super();
	}
	
	public Country(String countryName) {
		this.setCountryName(countryName);
	}
	
	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
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
}
