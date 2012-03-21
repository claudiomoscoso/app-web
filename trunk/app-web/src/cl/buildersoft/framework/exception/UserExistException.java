package cl.buildersoft.framework.exception;

import cl.buildersoft.framework.util.Messages;


public class UserExistException extends RuntimeException {

	public UserExistException(Throwable t) {
		super(Messages.getString("ErrorHandler.UsuarioExiste"));
	}
}
