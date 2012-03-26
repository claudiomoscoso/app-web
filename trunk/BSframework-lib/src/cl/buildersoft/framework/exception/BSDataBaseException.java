package cl.buildersoft.framework.exception;

/**
 * @author Claudio
 */
public class BSDataBaseException extends BSException {
	private static final long serialVersionUID = -7631726640158900205L;

	public BSDataBaseException(String code) {
		super(code);
	}

	public BSDataBaseException(String code, String message) {
		super(code, message);
	}
}
