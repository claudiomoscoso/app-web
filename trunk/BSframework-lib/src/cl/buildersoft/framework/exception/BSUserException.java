package cl.buildersoft.framework.exception;

/**
 * @author Claudio
 */
public class BSUserException extends BSException {
	private static final long serialVersionUID = -1241607908522051849L;

	public BSUserException(Exception e) {
		super(e);
	}

	public BSUserException(String code) {
		super(code);
	}

	public BSUserException(String code, String message) {
		super(code, message);
	}
}
