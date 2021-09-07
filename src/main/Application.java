package main;

import java.time.LocalDate;

import service.NbpApiController;

public class Application {
	
	public static void main(String[] args) {
		double caluclated;
		
		NbpApiController api = new NbpApiController();
		
		caluclated = api.calculatePln(1, "EUR", LocalDate.of(2016, 4, 4), true);
		System.out.println("Calulated 1 EUR to PLN: " + caluclated);
		
		caluclated = api.calculatePln(2, "JPY", LocalDate.now());
        System.out.println("Calulated 2 JPY to PLN: " + caluclated);
		
        caluclated = api.calculatePln(2.6, "USD", LocalDate.now());
        System.out.println("Calulated 2.6 USD to PLN: " + caluclated);
        
        return;
	}
}
