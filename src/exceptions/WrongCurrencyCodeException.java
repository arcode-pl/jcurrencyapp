package exceptions;

public class WrongCurrencyCodeException extends Exception {

	private static final long serialVersionUID = 1L;
	public WrongCurrencyCodeException(String currency) {
		super("Wrong Currency Code Exception: " + currency);
	}

}
