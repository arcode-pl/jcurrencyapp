package model;

public class CurrencyType {
	String table;
	String currency;
	String code;
	CurrencyRates rates;
	
	public String getTable() {
		return table;
	}
	
	public void setTable(String table) {
		this.table = table;
	}
	
	public String getCurrency() {
		return currency;
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public CurrencyRates getRates() {
		return rates;
	}
	
	public void setRates(CurrencyRates rates) {
		this.rates = rates;
	}
}
