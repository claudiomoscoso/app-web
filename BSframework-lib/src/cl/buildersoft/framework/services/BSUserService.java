package cl.buildersoft.framework.services;

import java.sql.Connection;

import cl.buildersoft.framework.beans.Menu;
import cl.buildersoft.framework.beans.Rol;
import cl.buildersoft.framework.beans.User;

public interface BSUserService {
	public User login(Connection conn, String mail, String password);

	public Rol getRol(Connection conn, User user) throws Exception;

	public Menu getMenu(Connection conn, Rol rol) throws Exception;

}
