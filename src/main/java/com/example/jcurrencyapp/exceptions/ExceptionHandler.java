package com.example.jcurrencyapp.exceptions;

public class ExceptionHandler {
	
	public static void handleException(RuntimeException ex) {
		try {
			throw ex;
		} catch (ValidatorException e) {
			System.out.println(e.getMessage());
			throw new AppException(e.getMessage());
		}  catch (ProviderException e) {
			System.out.println(e.getMessage());
			throw new AppException(e.getMessage());
		} catch (ConverterException e) {
			System.out.println(e.getMessage());
			throw new AppException(e.getMessage());
		} catch (WebApiException e) {
			System.out.println(e.getMessage());
			throw new AppException(e.getMessage());
		} catch (RuntimeException e) {
			throw e;
		}
	}
}
