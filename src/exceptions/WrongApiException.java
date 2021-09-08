package exceptions;

public class WrongApiException extends Exception {
	
	private static final long serialVersionUID = 1L;
	public WrongApiException() {
	   super("Wrong Api Exception");
	}
}
