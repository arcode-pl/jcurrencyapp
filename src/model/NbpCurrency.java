package model;

public class NbpCurrency {
	String table;
	String currency;
	String code;
	NbpRates rates;
	
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
	
	public NbpRates getRates() {
		return rates;
	}
	
	public void setRates(NbpRates rates) {
		this.rates = rates;
	}
}
