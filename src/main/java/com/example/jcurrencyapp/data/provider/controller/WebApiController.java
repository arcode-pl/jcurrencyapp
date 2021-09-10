package com.example.jcurrencyapp.data.provider.controller;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.example.jcurrencyapp.service.ApiResponse;

public class WebApiController {

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
