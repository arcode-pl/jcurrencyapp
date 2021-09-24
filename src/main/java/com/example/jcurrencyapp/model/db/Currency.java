package com.example.jcurrencyapp.model.db;

import com.example.jcurrencyapp.model.CurrencyTypes;

import javax.persistence.*;

@Entity
@Table(name = "currency")
public class Currency {

	private int currencyId;
	private String currencyCode;

	public Currency(CurrencyTypes code) {
		this.currencyCode = code.toString();
	}
	
	@Id
	@Column(name = "currency_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

}
