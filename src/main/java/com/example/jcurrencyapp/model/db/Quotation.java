package com.example.jcurrencyapp.model.db;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.jcurrencyapp.model.CurrencyTypes;

@Entity
@Table(name = "quotation")
public class Quotation {
	
	
	private Long quotationId;
	private Long currencyId;
	LocalDate date;
	private BigDecimal rate;
	
	public Quotation(CurrencyTypes code, LocalDate date) {
		
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

	public Long getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Long currencyId) {
		this.currencyId = currencyId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public BigDecimal getAsk() {
		return rate;
	}

	public void setAsk(BigDecimal ask) {
		this.rate = ask;
	}
}
