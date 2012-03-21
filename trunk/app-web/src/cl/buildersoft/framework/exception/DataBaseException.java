package cl.buildersoft.framework.exception;

import cl.buildersoft.framework.util.Messages;


public class DataBaseException extends RuntimeException {

	public DataBaseException(Throwable t) {
		super(Messages.getString("ErrorHandler.BaseDatosError"));
	}
}
