package client.exception;

public class MissingParamException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String MSG = "The parameter ${param} is missing";
	
	public MissingParamException(String paramName) {
		super(MSG.replace("${param}", paramName));
	}

}
