package main;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import data.model.Currency;
import data.model.CurrencyCodes;
import data.provider.ProviderFactory;
import data.provider.ProviderInterface;
import exceptions.WrongCurrencyCodeException;
import exceptions.WrongProtocolException;

public class AppController {
	
	AppConfig config;
	
	public AppController(AppConfig config) {
		this.config = config;
	}
	
	//Ask for today exchange rates by default
	public Optional<BigDecimal> calculate(String code, BigDecimal count) throws WrongCurrencyCodeException, 
		WrongProtocolException
	{
		return this.calculate(code, count, LocalDate.now());
	}
	
	public Optional<BigDecimal> calculate(String code, BigDecimal count, LocalDate date) throws WrongCurrencyCodeException, 
		WrongProtocolException 
	{
		Optional<Currency> data;
		
		// Check for null in input
		if (!inputsValid(code, count, date)) {
			return Optional.empty();
		}
		
		// Set date to today when ask for future
		if (date.isAfter(LocalDate.now())) {
			date = LocalDate.now();
		}
		
		// In this step we get currency model (rate, date, code) from provider.
		ProviderInterface<Currency> provider = ProviderFactory.getProvider(config.getProvider());
		if (provider != null) {
			// Try get previous day
			int retryCnt = 0;
			while ((data = provider.getRate(code, date)).isEmpty() && retryCnt < 100) {
				date = date.minusDays(1);
				retryCnt++;
			}
			
			if (data.isPresent()) {
				count = count.multiply(data.get().getRate());
				return Optional.of(count);
			}
		}
		
		return Optional.empty();
	}
	
	private boolean inputsValid(String code, BigDecimal count, LocalDate date) throws WrongCurrencyCodeException {
		return CurrencyCodes.exist(code) && count != null && date != null;
	}

	/*
	 * // BUY: PLN <= CURRENCY public Optional<BigDecimal> buyPln(String currency,
	 * BigDecimal count) { Optional<BigDecimal> value = null;
	 * 
	 * 
	 * return value; }
	 * 
	 * // SELL: PLN => CURRENCY public Optional<BigDecimal> sellPln(String currency,
	 * BigDecimal count) { Optional<BigDecimal> value = null;
	 * 
	 * return value; }
	 * 
	 * // MIDDLE: PLN ~ CURRENCY public Optional<BigDecimal> midPln(String currency,
	 * BigDecimal count) { Optional<BigDecimal> value = null;
	 * 
	 * return value; }
	 * 
	 * public Optional<BigDecimal> calc(String currency, BigDecimal count, String )
	 * { Optional<BigDecimal> value = null;
	 * 
	 * return value; }
	 */

	/*public double calculatePln(double count, String currency, LocalDate date) {
		if(count <= 0 || currency == null || date == null) {
			return 0;
		}
		
		ApiRequest apiRequest = new ApiRequest();
		apiRequest.Initialize("a", currency, date);
		ApiResponse response = getWebPageBodyAsString(apiRequest.getSimpleQuery());
		
		if(response.getCode() != 200) {
		  System.out.println(apiRequest.getSimpleQuery() + " => " + response.code);
		  return 0;
		}
		
		System.out.println(apiRequest.getSimpleQuery() + " => " + response.text);
		JsonParser parser = new JsonParser();
		
		
		return count * parser.getSimpleRate(response.text, "mid");
	}
	
	public double calculatePln(double count, String currency, LocalDate date, boolean forceXml) {
		if(count <= 0 || currency == null || date == null) {
			return 0;
		}
		
		ApiRequest apiRequest = new ApiRequest();
		apiRequest.Initialize("a", currency, date);
		apiRequest.forceXml(true);
		ApiResponse response = getWebPageBodyAsString(apiRequest.getSimpleQuery());
		
		if(response.getCode() != 200) {
			System.out.println(apiRequest.getSimpleQuery() + " => " + response.code);
			return 0;
		}
		
		System.out.println(apiRequest.getSimpleQuery() + " => " + response.text);

		return XmlParser.getSimpleRate(XmlParser.loadXmlFromString(response.text), "Mid") * count;
	}*/
	


	
	
}
