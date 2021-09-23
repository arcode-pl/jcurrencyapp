package com.example.jcurrencyapp.io.webapi;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.example.jcurrencyapp.exceptions.WebApiException;
import com.example.jcurrencyapp.io.webapi.model.WebApiResponse;

public class WebApiController {
	
	public static WebApiResponse tryReadApi(String url) {

		WebApiResponse apiResponse = new WebApiResponse();

		try {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(url))
					.GET()
					.build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			apiResponse.setCode(response.statusCode());
			apiResponse.setText(response.body());
		} catch (Exception e) {
			throw new WebApiException("Can't read api: " + e.getMessage(), e.getCause());
		}
		
		return apiResponse;
	}
}
