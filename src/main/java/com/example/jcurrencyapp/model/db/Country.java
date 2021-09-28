package com.example.jcurrencyapp.model.db;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
//import org.hibernate.annotations.Cache;
//import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;


@Entity
//@Cacheable
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "country")

@NamedQueries({
	//@NamedQuery(name = "Country.findAll", query = "SELECT u FROM Country ORDER BY u.countryName"),
	//@NamedQuery(name = "Country.findByCountryName", query = "SELECT u FROM Country WHERE u.countryName = :countryName")
})

public class Country {

	private Long countryId;
	private String countryName;
	private List<Currency> officialCurrencies = new ArrayList<Currency>(0);

	public Country() {
		super();
	}
	
	public Country(String countryName) {
		this.setCountryName(countryName);
	}
	
	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE)    
	@Column(name = "country_id")
	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long id) {
		this.countryId = id;
	}

	@Column(name = "country_name", unique = true, nullable = false)
	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String name) {
		this.countryName = name;
	}

	@Override
	public String toString() {
		return "Country [countryId=" + countryId + ", countryName=" + countryName + "]";
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "country_currency", 
		joinColumns = {@JoinColumn(name = "country_id", nullable = false, updatable = false) }, 
		inverseJoinColumns = {@JoinColumn(name = "currency_id", nullable = false, updatable = false) })
	public List<Currency> getOfficialCurrencies() {
		return officialCurrencies;
	}

	public void setOfficialCurrencies(List<Currency> officialCurrencies) {
		this.officialCurrencies = officialCurrencies;
	}
}
