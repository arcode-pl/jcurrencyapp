package com.example.jcurrencyapp.exceptions;

public class ExceptionHandler {
	
	public static void handleException(RuntimeException ex) {
		try {
			throw ex;
		} catch (ValidatorException e) {
			System.out.println(e.getMessage());
			throw new AppException(e.getMessage(), e);
		}  catch (ProviderException e) {
			System.out.println(e.getMessage());
			throw new AppException(e.getMessage(), e);
		} catch (ConverterException e) {
			System.out.println(e.getMessage());
			throw new AppException(e.getMessage(), e);
		} catch (WebApiException e) {
			System.out.println(e.getMessage());
			throw new AppException(e.getMessage(), e);
		} catch (RuntimeException e) {
			throw e;
		}
	}
}
