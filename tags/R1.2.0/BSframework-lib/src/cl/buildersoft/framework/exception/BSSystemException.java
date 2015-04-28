package cl.buildersoft.framework.exception;

/**
 * @author Claudio
 */
public class BSSystemException extends BSException {

	private static final long serialVersionUID = 2484840805705355963L;

	public BSSystemException(Exception e) {
		super(e);
	}

	public BSSystemException(String code) {
		super(code);
	}

	public BSSystemException(String code, String message) {
		super(code, message);
	}

}
