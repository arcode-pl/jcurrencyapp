package com.example.jcurrencyapp.model.db;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Table(name = "quotation")

@NamedQueries({
		// @NamedQuery(name = "Quotation.findAll", query = "SELECT u FROM Quotation
		// ORDER BY u.date"),
		// @NamedQuery(name = "Quotation.findAllForCurrency", query = "SELECT u FROM
		// Quotation WHERE u.currencyId = :currrencyId"),
		@NamedQuery(name = "Quotation.findByCodeAndDate", query = "SELECT u FROM Quotation u WHERE u.currency = :currency AND u.date = :date"),
		@NamedQuery(name = "Quotation.findByCodeAndRateBiggerThan", query = "SELECT u FROM Quotation u WHERE u.currency = :currency AND u.rate > :rate") })

public class Quotation {

	private Long quotationId;
	private Currency currency;
	LocalDate date;
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

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "quotation_id")
	public Long getQuotationId() {
		return quotationId;
	}

	public void setQuotationId(Long quotationId) {
		this.quotationId = quotationId;
	}

	@ManyToOne
	@JoinColumn(name = "currency_id")
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

	@Column(name = "rate", precision = 8, scale = 4)
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
