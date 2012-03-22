package cl.buildersoft.framework.exception;

import cl.buildersoft.framework.util.Messages;


public class DataBaseException extends RuntimeException {
	private static final long serialVersionUID = -614107503606909020L;

	public DataBaseException(Throwable t) {
		super(Messages.getString("ErrorHandler.BaseDatosError"));
	}
}
