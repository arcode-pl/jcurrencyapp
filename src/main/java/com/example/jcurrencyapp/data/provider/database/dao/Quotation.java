package com.example.jcurrencyapp.data.provider.database.dao;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import javax.persistence.UniqueConstraint;

import com.example.jcurrencyapp.model.CurrencyTypes;
import com.example.jcurrencyapp.model.Rate;

@NamedQueries({
		@NamedQuery(name = Quotation.FIND_BY_CODE, query = "SELECT u FROM Quotation u WHERE u.currency = :"
				+ Quotation.PARAM_CURRENCY),

		@NamedQuery(name = Quotation.FIND_BY_CODE_AND_DATE, query = "SELECT u FROM Quotation u WHERE u.currency = :"
				+ Quotation.PARAM_CURRENCY + " AND u.date = :" + Quotation.PARAM_DATE),

		@NamedQuery(name = Quotation.FIND_BY_CODE_AND_DATE_RANGE, query = "SELECT u FROM Quotation u WHERE u.currency = :"
				+ Quotation.PARAM_CURRENCY + " AND u.date >= :" + Quotation.PARAM_START_DATE + " AND u.date <= :"
				+ Quotation.PARAM_END_DATE),

		@NamedQuery(name = Quotation.FIND_BY_CODE_ASC_ORDER_BY_PRICE, query = "SELECT u FROM Quotation u WHERE u.currency = :"
				+ Quotation.PARAM_CURRENCY + " ORDER BY u.price ASC"),
		@NamedQuery(name = Quotation.FIND_BY_CODE_AND_DATE_RANGE_ASC_ORDER_BY_PRICE, query = "SELECT u FROM Quotation u WHERE u.currency = :"
				+ Quotation.PARAM_CURRENCY + " AND u.date >= :" + Quotation.PARAM_START_DATE + " AND u.date <= :"
				+ Quotation.PARAM_END_DATE + " ORDER BY u.price ASC"),

		@NamedQuery(name = Quotation.FIND_BY_CODE_DESC_ORDER_BY_PRICE, query = "SELECT u FROM Quotation u WHERE u.currency = :"
				+ Quotation.PARAM_CURRENCY + " ORDER BY u.price DESC"),
		@NamedQuery(name = Quotation.FIND_BY_CODE_AND_DATE_RANGE_DESC_ORDER_BY_PRICE, query = "SELECT u FROM Quotation u WHERE u.currency = :"
				+ Quotation.PARAM_CURRENCY + " AND u.date >= :" + Quotation.PARAM_START_DATE + " AND u.date <= :"
				+ Quotation.PARAM_END_DATE + " ORDER BY u.price DESC"),

		@NamedQuery(name = Quotation.FIND_MOST_UNSTABLE_CURRENCY_BY_DATE_RANGE, query = "SELECT c, (MAX(q.price)-MIN(q.price)) AS d FROM Quotation q JOIN q.currency c WHERE q.date >= :"
				+ Quotation.PARAM_START_DATE + " AND q.date <= :" + Quotation.PARAM_END_DATE
				+ " GROUP BY c ORDER BY d DESC") })

@Entity
@Table(name = "quotation", indexes = @Index(name = "quotation_index", columnList = "date, price"), uniqueConstraints = @UniqueConstraint(name = "quotation_unique", columnNames = {
		"currencyid", "date" }))
public class Quotation {

	static final String FIND_BY_CODE = "Quotation.findByCode";
	static final String FIND_BY_CODE_AND_DATE = "Quotation.findByCodeAndDate";
	static final String FIND_BY_CODE_AND_DATE_RANGE = "Quotation.findByCodeAndDateRange";

	static final String FIND_BY_CODE_ASC_ORDER_BY_PRICE = "Quotation.findByCodeAscOrderByPrice";
	static final String FIND_BY_CODE_AND_DATE_RANGE_ASC_ORDER_BY_PRICE = "Quotation.findByCodeAndDateRangeAscOrderByPrice";
	static final String FIND_BY_CODE_DESC_ORDER_BY_PRICE = "Quotation.findByCodeDescOrderByPrice";
	static final String FIND_BY_CODE_AND_DATE_RANGE_DESC_ORDER_BY_PRICE = "Quotation.findByCodeAndDateRangeDescOrderByPrice";
	static final String FIND_MOST_UNSTABLE_CURRENCY_BY_DATE_RANGE = "Quotation.findMostUnstableCurrencyByDateRange";

	static final String PARAM_DATE = "date";
	static final String PARAM_START_DATE = "startDate";
	static final String PARAM_END_DATE = "endDate";
	static final String PARAM_CURRENCY = "currency";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long quotationId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "currencyid", nullable = false, foreignKey = @ForeignKey(name = "currency_fk"))
	private Currency currency;
	LocalDate date;
	@Column(precision = 16, scale = 8)
	private BigDecimal price;

	public Quotation() {
		super();
	}

	public Quotation(Currency currency, LocalDate date, BigDecimal price) {
		super();
		this.currency = currency;
		this.date = date;
		this.price = price;
	}

	public Long getQuotationId() {
		return quotationId;
	}

	public Currency getCurrency() {
		return currency;
	}

	public LocalDate getDate() {
		return date;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public Rate toRate() {
		return new Rate(CurrencyTypes.getByCode(currency.getCurrencyCode()), date, price);
	}

	@Override
	public String toString() {
		return "Quotation [quotationId=" + quotationId + ", currencyCode=" + currency + ", date=" + date + ", price="
				+ price + "]";
	}
}
