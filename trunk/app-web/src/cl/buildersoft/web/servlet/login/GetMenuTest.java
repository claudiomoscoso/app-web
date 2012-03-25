package cl.buildersoft.web.servlet.login;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;

import cl.buildersoft.framework.beans.Menu;
import cl.buildersoft.framework.beans.Rol;
import cl.buildersoft.framework.beans.User;
import cl.buildersoft.framework.services.BSMenuService;
import cl.buildersoft.framework.services.BSUserService;
import cl.buildersoft.framework.services.impl.BSMenuServiceImpl;
import cl.buildersoft.framework.services.impl.BSUserServiceImpl;
import cl.buildersoft.framework.util.BSDataUtils;

public class GetMenuTest {

	@Test
	public void testGetRol() {
		Menu menu = null;
		try {
			User user = new User();
			user.setId(1L);

			Connection conn = new BSDataUtils().getConnection(
					"org.gjt.mm.mysql.Driver", "localhost", "bsframework",
					"12870668", "root");

			BSMenuService menuService = new BSMenuServiceImpl();
			BSUserService userService = new BSUserServiceImpl();
			
			List<Rol> rols = userService.getRols(conn, user);
			menu = menuService.getMenu(conn, rols);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		assertTrue(menu != null && menu.list().size() > 0);
	}
}
