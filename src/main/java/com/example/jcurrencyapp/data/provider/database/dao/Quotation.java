package com.example.jcurrencyapp.data.provider.database.dao;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.example.jcurrencyapp.model.CurrencyTypes;
import com.example.jcurrencyapp.model.Rate;

@NamedQueries({
		@NamedQuery(name = Quotation.FIND_BY_CODE, query = "SELECT u FROM Quotation u WHERE u.currencyCode = :"
				+ Quotation.PARAM_CURRENCY_CODE),

		@NamedQuery(name = Quotation.FIND_BY_CODE_AND_DATE, query = "SELECT u FROM Quotation u WHERE u.currencyCode = :"
				+ Quotation.PARAM_CURRENCY_CODE + " AND u.date = :" + Quotation.PARAM_DATE),
		
		@NamedQuery(name = Quotation.FIND_BY_CODE_AND_DATE_RANGE, query = "SELECT u FROM Quotation u WHERE u.currencyCode = :"
				+ Quotation.PARAM_CURRENCY_CODE + " AND u.date >= :" + Quotation.PARAM_START_DATE + " AND u.date <= :"
				+ Quotation.PARAM_END_DATE),
		
		@NamedQuery(name = Quotation.FIND_MAX_BY_CODE, query = "SELECT u FROM Quotation u WHERE u.currencyCode = :"
				+ Quotation.PARAM_CURRENCY_CODE + " ORDER BY u.price "),
		@NamedQuery(name = Quotation.FIND_MIN_BY_CODE, query = "SELECT u FROM Quotation u WHERE u.currencyCode = :"
				+ Quotation.PARAM_CURRENCY_CODE + " ORDER BY u.price DESC") })

@Entity
@Table(name = "quotation", indexes = @Index(name = "quotation_index", columnList = "currencyCode, date, price", unique = true))
public class Quotation {

	static final String FIND_BY_CODE = "Quotation.findByCode";
	static final String FIND_BY_CODE_AND_DATE = "Quotation.findByCodeAndDate";
	static final String FIND_BY_CODE_AND_DATE_RANGE = "Quotation.findByCodeAndDateRange";
	
	static final String FIND_MAX_BY_CODE = "Quotation.findMaxByCode";
	static final String FIND_MIN_BY_CODE = "Quotation.findMinByCode";

	static final String PARAM_DATE = "date";
	static final String PARAM_START_DATE = "startDate";
	static final String PARAM_END_DATE = "endDate";
	static final String PARAM_CURRENCY_CODE = "currencyCode";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long quotationId;
	private String currencyCode;
	LocalDate date;
	@Column(precision = 16, scale = 8)
	private BigDecimal price;

	public Quotation() {
		super();
	}

	public Quotation(Rate rate) {
		super();
		this.currencyCode = rate.getCurrency().getCode();
		this.date = rate.getDate();
		this.price = rate.getPrice();
	}

	public Long getQuotationId() {
		return quotationId;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public LocalDate getDate() {
		return date;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public Rate toRate() {
		return new Rate(CurrencyTypes.getByCode(currencyCode), date, price);
	}

	@Override
	public String toString() {
		return "Quotation [quotationId=" + quotationId + ", currencyCode=" + currencyCode + ", date=" + date
				+ ", price=" + price + "]";
	}
}
