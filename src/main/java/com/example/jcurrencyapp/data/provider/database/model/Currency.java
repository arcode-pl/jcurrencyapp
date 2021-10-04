package com.example.jcurrencyapp.data.provider.database.model;

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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.example.jcurrencyapp.model.CurrencyTypes;

@NamedQueries({
	@NamedQuery(name = Currency.FIND_ALL, query = "SELECT u FROM Currency u ORDER BY u.currencyCode"),
	@NamedQuery(name = Currency.FIND_BY_CODE, query = "SELECT u FROM Currency u WHERE u.currencyCode = :" + Currency.PARAM_CURRENCY_CODE)
})

@Entity
@Table(name = "currency", indexes = 
	@Index(name = "currency_index", columnList = "currency_code", unique = true) )
public class Currency {
	
	public static final String FIND_ALL = "Currency.findAll";
	public static final String FIND_BY_CODE = "Currency.findByCode";
	
	public static final String PARAM_CURRENCY_CODE = "currencyCode";
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "currency_id")
	private Long currencyId;
	
	@Column(name = "currency_code", nullable = false)
	private String currencyCode;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "officialCurrencies")
	@Fetch(FetchMode.JOIN)
	private Set<Country> supportedCountries = new HashSet<Country>();
	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "currency")
//    private List<Quotation> quotations = new ArrayList<Quotation>();
	
	public Currency() {
		super();
	}
	
	public Currency(CurrencyTypes code) {
		this.setCurrencyCode(code.toString());
	}
	
	public Long getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Long currencyId) {
		this.currencyId = currencyId;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
	public Set<Country> getSupportedCountries() {
		return supportedCountries;
	}
	
	public void setSupportedCountries(Set<Country> supportedCountries) {
		this.supportedCountries = supportedCountries;
	}
	
//	public List<Quotation> getQuotations() {
//		return quotations;
//	}
//
//	public void setQuotations(List<Quotation> quotations) {
//		this.quotations = quotations;
//	}
	
//	public void addQuotation(Quotation quotation) {
//        this.quotations.add(quotation);
//        quotation.setCurrency(this);
//    }
	
	@Override
	public String toString() {
		return "Currency [currencyId=" + currencyId + ", currencyCode=" + currencyCode + "]";
	}
}
