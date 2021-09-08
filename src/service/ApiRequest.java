package service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ApiRequest {
	private final String host = "http://api.nbp.pl/api/exchangerates/";
	String tableType;
	String currency;
	LocalDate dateFrom;
	LocalDate dateTo;
	int lastN;
	boolean forceXml;
	boolean multiple;
	
	public void Initialize(String table, String currency, LocalDate date) {
		this.tableType = table;
		this.currency = currency;
		this.dateFrom = date;
	}
	
	public String getSimpleQuery() {
		if (this.tableType == null || this.currency == null || this.dateFrom == null) {
			return "";
		}
		
		return host + 
			"rates/" +
			this.tableType + "/" +
			this.currency.toLowerCase() + "/"  + 
			this.dateFrom.format(DateTimeFormatter.ISO_LOCAL_DATE) + 
			"/?format=" + (this.forceXml ? "xml" : "json");
	}
	
	public void forceXml(boolean forceXml){
		this.forceXml = forceXml;
	}
}
