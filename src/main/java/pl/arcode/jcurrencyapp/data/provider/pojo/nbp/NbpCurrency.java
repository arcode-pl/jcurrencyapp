package pl.arcode.jcurrencyapp.data.provider.pojo.nbp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"table",
	"currency",
	"code",
	"rates"
})
	
@JacksonXmlRootElement(localName = "NbpCurrency")
@javax.annotation.processing.Generated("jsonschema2pojo")
public class NbpCurrency {

	@JsonProperty("table")
	private String table;
	
	@JsonProperty("currency")
	private String currency;
	
	@JsonProperty("code")
	private String code;
	
	@JsonProperty("rates")
	private List<NbpRate> rates = null;
	
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	@JsonProperty("table")
	public String getTable() {
		return table;
	}

	@JsonProperty("table")
	public void setTable(String table) {
		this.table = table;
	}

	@JsonProperty("currency")
	public String getCurrency() {
		return currency;
	}

	@JsonProperty("currency")
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@JsonProperty("code")
	public String getCode() {
		return code;
	}

	@JsonProperty("code")
	public void setCode(String code) {
		this.code = code;
	}
	
	@JsonProperty("rates")
	public List<NbpRate> getRates() {
		return rates;
	}

	@JsonProperty("rates")
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

}
