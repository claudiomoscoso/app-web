package cl.buildersoft.lib.services;

import java.sql.Connection;
import java.util.List;

import cl.buildersoft.lib.beans.Menu;
import cl.buildersoft.lib.beans.Option;
import cl.buildersoft.lib.beans.Rol;
import cl.buildersoft.lib.beans.Submenu;

public interface BSMenuService {
	public Menu getMenu(Connection conn, List<Rol> rols);
	public Menu getMenu(Connection conn, List<Rol> rols, Long type);
	
	public Boolean optionInMenu(Option opt, List<Submenu> main);
	
	public Option searchResourceByKey(Connection conn, String code);
}
