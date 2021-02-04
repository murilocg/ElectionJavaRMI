package client.exception;

public class InvalidActionException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String MSG = "There is no action available with name: ${actionName}";
	
	public InvalidActionException(String action) {
		super(MSG.replace("${actionName}", action));
	}

}
