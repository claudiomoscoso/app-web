package cl.buildersoft.lib.services;

import java.sql.Connection;
import java.util.List;

import cl.buildersoft.lib.beans.Rol;
import cl.buildersoft.lib.beans.User;

public interface BSUserService {
	public User login(Connection conn, String mail, String password);

	public List<Rol> getRols(Connection conn, User user);


}
