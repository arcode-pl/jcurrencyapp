package com.example.jcurrencyapp.data.model.pojo.nbp;

import java.util.HashMap;
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
	"no",
	"effectiveDate",
	"bid",
	"ask"
})

@JacksonXmlRootElement(localName = "Rate")
@javax.annotation.processing.Generated("jsonschema2pojo")
public class NbpRate {

	@JsonProperty("no")
	@JacksonXmlProperty(localName = "No")
	private String no;
	@JsonProperty("effectiveDate")
	@JacksonXmlProperty(localName = "EffectiveDate")
	private String effectiveDate;
	@JsonProperty("bid")
	@JacksonXmlProperty(localName = "Bid")
	private Double bid;
	@JsonProperty("ask")
	@JacksonXmlProperty(localName = "Ask")
	private Double ask;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("no")
	@JacksonXmlProperty(localName = "No")
	public String getNo() {
		return no;
	}
	
	@JsonProperty("no")
	@JacksonXmlProperty(localName = "No")
	public void setNo(String no) {
		this.no = no;
	}

	@JsonProperty("effectiveDate")
	@JacksonXmlProperty(localName = "EffectiveDate")
	public String getEffectiveDate() {
		return effectiveDate;
	}
	
	@JsonProperty("effectiveDate")
	@JacksonXmlProperty(localName = "EffectiveDate")
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	@JsonProperty("bid")
	@JacksonXmlProperty(localName = "Bid")
	public Double getBid() {
		return bid;
	}
	
	@JsonProperty("bid")
	@JacksonXmlProperty(localName = "Bid")
	public void setBid(Double bid) {
		this.bid = bid;
	}

	@JsonProperty("ask")
	@JacksonXmlProperty(localName = "Ask")
	public Double getAsk() {
		return ask;
	}
	
	@JsonProperty("ask")
	@JacksonXmlProperty(localName = "Ask")
	public void setAsk(Double ask) {
		this.ask = ask;
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
		return "NbpRate [no=" + no + ", effectiveDate=" + effectiveDate + ", bid=" + bid + ", ask=" + ask
				+ ", additionalProperties=" + additionalProperties + "]";
	}
}