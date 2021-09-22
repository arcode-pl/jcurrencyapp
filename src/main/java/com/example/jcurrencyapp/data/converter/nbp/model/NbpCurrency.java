package com.example.jcurrencyapp.data.converter.nbp.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"table",
	"currency",
	"code",
	"rates"
})

@JacksonXmlRootElement(localName = "ExchangeRatesSeries")
@javax.annotation.processing.Generated("jsonschema2pojo")
public class NbpCurrency {

	@JsonProperty("table")
	@JacksonXmlProperty(localName = "Table")
	private String table;
	
	@JsonProperty("currency")
	@JacksonXmlProperty(localName = "Currency")
	private String currency;
	
	@JsonProperty("code")
	@JacksonXmlProperty(localName = "Code")
	private String code;
	
	@JsonProperty("rates")
	@JacksonXmlProperty(localName = "Rates")
	private List<NbpRate> rates = null;

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	@JsonProperty("table")
	@JacksonXmlProperty(localName = "Table")
	public String getTable() {
		return table;
	}

	@JsonProperty("table")
	@JacksonXmlProperty(localName = "Table")
	public void setTable(String table) {
		this.table = table;
	}

	@JsonProperty("currency")
	@JacksonXmlProperty(localName = "Currency")
	public String getCurrency() {
		return currency;
	}

	@JsonProperty("currency")
	@JacksonXmlProperty(localName = "Currency")
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@JsonProperty("code")
	@JacksonXmlProperty(localName = "Code")
	public String getCode() {
		return code;
	}

	@JsonProperty("code")
	@JacksonXmlProperty(localName = "Code")
	public void setCode(String code) {
		this.code = code;
	}
	
	@JsonProperty("rates")
	@JacksonXmlProperty(localName = "Rates")
	public List<NbpRate> getRates() {
		return rates;
	}

	@JsonProperty("rates")
	@JacksonXmlProperty(localName = "Rates")
	public void setRates(List<NbpRate> rates) {
		this.rates = rates;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}
	
	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		return "NbpCurrency [table=" + table + ", currency=" + currency + ", code=" + code + ", rates=" + rates
				+ ", additionalProperties=" + additionalProperties + "]";
	}
	
	public double getSimpleAskRate() {
		return this.getRates().get(0).getAsk();
	}

}
