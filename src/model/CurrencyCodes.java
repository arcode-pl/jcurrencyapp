package model;

import java.util.Map;
import java.util.Optional;

import exceptions.WrongCurrencyCodeException;

public class CurrencyCodes {
	
	//TODO: Dla ka�dej waluty stworzyc model z tlumaczeniami lub resources?
	// na ten moment po polsku
	private static final Map<String, String> CODES = Map.ofEntries(
			Map.entry("THB", "bat (Tajlandia)"),
			Map.entry("USD", "dolar ameryka�ski"),
			Map.entry("AUD", "dolar australijski"),
			Map.entry("HKD", "dolar Hongkongu"),
			Map.entry("CAD", "dolar kanadyjski"),
			Map.entry("NZD", "dolar nowozelandzki"),
			Map.entry("SGD", "dolar singapurski"),
			Map.entry("EUR", "euro"),
			Map.entry("HUF", "forint (W�gry)"),
			Map.entry("CHF", "frank szwajcarski"),
			Map.entry("GBP", "funt szterling"),
			Map.entry("UAH", "hrywna (Ukraina)"),
			Map.entry("JPY", "jen (Japonia)"),
			Map.entry("CZK", "korona czeska"),
			Map.entry("DKK", "korona du�ska"),
			Map.entry("ISK", "korona islandzka"),
			Map.entry("NOK", "korona norweska"),
			Map.entry("SEK", "korona szwedzka"),
			Map.entry("HRK", "kuna (Chorwacja)"),
			Map.entry("RON", "lej rumu�ski"),
			Map.entry("BGN", "lew (Bu�garia)"),
			Map.entry("TRY", "lira turecka"),
			Map.entry("ILS", "nowy izraelski szekel"),
			Map.entry("CLP", "peso chilijskie"),
			Map.entry("PHP", "peso filipi�skie"),
			Map.entry("MXN", "peso meksyka�skie"),
			Map.entry("ZAR", "rand (Republika Po�udniowej Afryki)"),
			Map.entry("BRL", "real (Brazylia)"),
			Map.entry("MYR", "ringgit (Malezja)"),
			Map.entry("RUB", "rubel rosyjski"),
			Map.entry("IDR", "rupia indonezyjska"),
			Map.entry("INR", "rupia indyjska"),
			Map.entry("KRW", "won po�udniowokorea�ski"),
			Map.entry("CNY", "yuan renminbi (Chiny)"),
			Map.entry("XDR", "SDR (MFW)")
			);
	
	public static boolean exist(String code) throws WrongCurrencyCodeException {
		if (CODES.get(code) == null) {
			throw new WrongCurrencyCodeException();
		}
		
		return true;
	}
	
	public static String getDescription(String code) {
		String desc = CODES.get(code);
		
		if(desc == null) {
			desc = "not defined";
		}
		
		return desc;
	}
}
