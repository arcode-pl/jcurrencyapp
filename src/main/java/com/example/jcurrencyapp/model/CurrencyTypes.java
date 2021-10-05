package com.example.jcurrencyapp.model;

public enum CurrencyTypes {
	THB("bat (Tajlandia)"), 
	USD("dolar amerykański"), 
	AUD("dolar australijski"), 
	HKD("dolar Hongkongu"), 
	CAD("dolar kanadyjski"), 
	NZD("dolar nowozelandzki"), 
	SGD("dolar singapurski"), 
	EUR("euro"), 
	HUF("forint (Węgry)"),
	CHF("frank szwajcarski"), 
	GBP("funt szterling"), 
	UAH("hrywna (Ukraina)"), 
	JPY("jen (Japonia)"), 
	CZK("korona czeska"), 
	DKK("korona duńska"), 
	ISK("korona islandzka"), 
	NOK("korona norweska"), 
	SEK("korona szwedzka"),
	HRK("kuna (Chorwacja)"), 
	RON("lej rumuński"), 
	BGN("lew (Bułgaria)"), 
	TRY("lira turecka"), 
	ILS("nowy izraelski szekel"), 
	CLP("peso chilijskie"), 
	PHP("peso filipińskie"), 
	MXN("peso meksykańskie"), 
	ZAR("rand (Republika Południowej Afryki)"),
	BRL("real (Brazylia)"), 
	MYR("ringgit (Malezja)"), 
	RUB("rubel rosyjski"), 
	IDR("rupia indonezyjska"), 
	INR("rupia indyjska"), 
	KRW("won południowokoreański"), 
	CNY("yuan renminbi (Chiny)"), 
	XDR("SDR (MFW)"), 
	BTC("bitcoin");

	private String name;

	CurrencyTypes(String name) {
		this.name = name;
	}

	public static CurrencyTypes getByCode(String code) {
		for (CurrencyTypes var : values()) {
			if (code.equals(var.toString())) {
				return var;
			}
		}

		return null;
	}
	
	public String getCode() {
		return this.toString();
	}
	
	public String getName() {
		return name;
	}
}
