package com.example.jcurrencyapp.io.webapi;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.example.jcurrencyapp.exceptions.ExceptionHandler;
import com.example.jcurrencyapp.exceptions.WebApiException;

public class WebApiController {
	
	// 1. Exceptions from client send method can't be avoided so this was checked exception. Really?
	// 2. Created class ApiResponse with code initialized with -1. When response not come, simple check this to avoid other exceptions upper.
	public static ApiResponse readApi(String url) {

		ApiResponse apiResponse = new ApiResponse();

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
			ExceptionHandler.handleException(new WebApiException("Can't read api: " + e.getMessage()));
		}

		return apiResponse;
	}
}
