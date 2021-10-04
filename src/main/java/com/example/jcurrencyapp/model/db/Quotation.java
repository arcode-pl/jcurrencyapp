package com.example.jcurrencyapp.model.db;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
		@NamedQuery(name = Quotation.FIND_BY_CODE_AND_DATE, query = "SELECT u FROM Quotation u WHERE u.currency = :"
				+ Quotation.PARAM_CURRENCY + " AND u.date = :" + Quotation.PARAM_DATE),
		@NamedQuery(name = Quotation.FIND_BY_CODE_AND_DATE_RANGE, query = "SELECT u FROM Quotation u WHERE u.currency = :"
				+ Quotation.PARAM_CURRENCY + " AND u.date >= :" + Quotation.PARAM_START_DATE + " AND u.date <= :"
				+ Quotation.PARAM_END_DATE),
		@NamedQuery(name = Quotation.FIND_MAX_BY_CODE, query = "SELECT u FROM Quotation u WHERE u.currency = :"
				+ Quotation.PARAM_CURRENCY + " ORDER BY u.rate"),
		@NamedQuery(name = Quotation.FIND_MIN_BY_CODE, query = "SELECT u FROM Quotation u WHERE u.currency = :"
				+ Quotation.PARAM_CURRENCY + " ORDER BY u.rate DESC") })

@Entity
@Table(name = "quotation", indexes = 
	@Index(name = "quotation_index", columnList = "date, rate", unique = true) )
public class Quotation {

	public static final String FIND_BY_CODE_AND_DATE = "Quotation.findByCodeAndDate";
	public static final String FIND_BY_CODE_AND_DATE_RANGE = "Quotation.findByCodeAndDateRange";
	public static final String FIND_MAX_BY_CODE = "Quotation.findMaxByCode";
	public static final String FIND_MIN_BY_CODE = "Quotation.findMinByCode";

	public static final String PARAM_DATE = "date";
	public static final String PARAM_START_DATE = "startDate";
	public static final String PARAM_END_DATE = "endDate";
	public static final String PARAM_CURRENCY = "currency";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "quotation_id")
	private Long quotationId;
	
	@ManyToOne
	@JoinColumn(name = "currency_id", foreignKey = @ForeignKey(name = "quotation_currency_fk"))
	private Currency currency;
	
	LocalDate date;
	
	@Column(precision = 16, scale = 8)
	private BigDecimal rate;

	public Quotation() {
		super();
	}

	public Quotation(Currency currency, LocalDate date, BigDecimal rate) {
		super();
		this.currency = currency;
		this.date = date;
		this.rate = rate;
	}

	public Long getQuotationId() {
		return quotationId;
	}

	public void setQuotationId(Long quotationId) {
		this.quotationId = quotationId;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal ask) {
		this.rate = ask;
	}

	@Override
	public String toString() {
		return "Quotation [quotationId=" + quotationId + ", currency=" + currency + ", date=" + date + ", rate=" + rate
				+ "]";
	}
}
