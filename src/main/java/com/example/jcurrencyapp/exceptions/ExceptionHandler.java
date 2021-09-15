package com.example.jcurrencyapp.exceptions;

public class ExceptionHandler {
	
	public static void handleException(Exception ex) {
		try {
			throw ex;
		} catch (ValidatorException e) {
			System.out.println(e.getMessage());
		}  catch (ProviderException e) {
			System.out.println(e.getMessage());
		} catch (ConverterException e) {
			System.out.println(e.getMessage());
		} catch (WebApiException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
