package exceptions;

public class NullException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public NullException(String strMessage) {
		super(strMessage);
	}
}
