package service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

import javax.script.ScriptException;

import controller.JsonParser;
import controller.XmlParser;

public class NbpApiController {
	
	public double calculatePln(double count, String currency, LocalDate date) {
		if(count <= 0 || currency == null || date == null) {
			return 0;
		}
		
		NbpApiRequest apiRequest = new NbpApiRequest();
		apiRequest.Initialize("a", currency, date);
		NbpApiResponse response = getWebPageBodyAsString(apiRequest.getSimpleQuery());
		
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
		
		NbpApiRequest apiRequest = new NbpApiRequest();
		apiRequest.Initialize("a", currency, date);
		apiRequest.forceXml(true);
		NbpApiResponse response = getWebPageBodyAsString(apiRequest.getSimpleQuery());
		
		if(response.getCode() != 200) {
			System.out.println(apiRequest.getSimpleQuery() + " => " + response.code);
			return 0;
		}
		
		System.out.println(apiRequest.getSimpleQuery() + " => " + response.text);

		return XmlParser.getSimpleRate(XmlParser.loadXmlFromString(response.text), "Mid") * count;
	}
	
	public static NbpApiResponse getWebPageBodyAsString(String url) {
		NbpApiResponse apiResponse = new NbpApiResponse();
		
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
