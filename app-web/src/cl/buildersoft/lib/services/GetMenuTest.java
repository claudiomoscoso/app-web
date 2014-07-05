package cl.buildersoft.lib.services;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;

import cl.buildersoft.lib.beans.Menu;
import cl.buildersoft.lib.beans.Rol;
import cl.buildersoft.lib.beans.User;
import cl.buildersoft.lib.services.impl.BSMenuServiceImpl;
import cl.buildersoft.lib.services.impl.BSUserServiceImpl;
import cl.buildersoft.lib.util.BSDataUtils;

public class GetMenuTest {

	@Test
	public void testGetRol1() {
		Menu menu = null;

		User user = new User();
		user.setId(1L);

		Connection conn = new BSDataUtils().getConnection(
				"org.gjt.mm.mysql.Driver", "localhost", "bsframework",
				"12870668", "root");

		BSMenuService menuService = new BSMenuServiceImpl();
		BSUserService userService = new BSUserServiceImpl();

		List<Rol> rols = userService.getRols(conn, user);
		menu = menuService.getMenu(conn, rols);

		assertTrue(menu != null && menu.list().size() > 0);
	}

	@Test
	public void testGetMenu1() {
		Menu menu = null;

		User user = new User();
		user.setId(1L);

		Connection conn = new BSDataUtils().getConnection(
				"org.gjt.mm.mysql.Driver", "localhost", "bsframework",
				"12870668", "root");

		BSUserService userService = new BSUserServiceImpl();
		List<Rol> rols = userService.getRols(conn, user);

		BSMenuService menuService = new BSMenuServiceImpl();
		menu = menuService.getMenu(conn, rols);

		assertTrue(menu != null && menu.list().size() > 0);
	}

}
