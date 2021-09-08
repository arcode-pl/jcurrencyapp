package main;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

import exceptions.WrongCurrencyCodeException;
import exceptions.WrongProtocolException;
import model.CurrencyCodes;
import model.CurrencyType;
import service.ApiResponse;
import strategy.parser.ParserFactory;
import strategy.parser.ParserStrategy;

public class AppController {
	
	AppConfig config;
	
	public AppController(AppConfig config) {
		this.config = config;
	}
	
	//Ask for today exchange rates by default
	public BigDecimal calculate(String code, BigDecimal count) throws WrongCurrencyCodeException, 
		WrongProtocolException
	{
		return this.calculate(code, count, LocalDate.now());
	}
	
	public BigDecimal calculate(String code, BigDecimal count, LocalDate date) throws WrongCurrencyCodeException, 
		WrongProtocolException 
	{
		CurrencyCodes.exist(code);
		
		//Set date to today when ask for future
		if (date.isAfter(LocalDate.now())) {
			date = LocalDate.now();
		}
		
		ParserStrategy<CurrencyType> strategy = ParserFactory.getStrategy(this.config.getParser());
		strategy.parse(code);
		
		return count;
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
	
	private String readFile(String filePath) throws IOException {
		return Files.readString(Path.of(filePath));
	}
	
	public static ApiResponse readApi(String url) {
		ApiResponse apiResponse = new ApiResponse();
		
		try {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET() // GET is default
                .build();

			HttpResponse <String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
			
			apiResponse.setCode(response.statusCode());
			apiResponse.setText(response.body());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return apiResponse;
	}

	
	
}
