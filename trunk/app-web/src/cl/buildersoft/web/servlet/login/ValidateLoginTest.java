package cl.buildersoft.web.servlet.login;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import cl.buildersoft.framework.beans.User;
import cl.buildersoft.framework.services.impl.BSUserServiceImpl;
import cl.buildersoft.framework.util.BSDataUtils;

public class ValidateLoginTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testLogin1() {
		String mail = "admin";
		String password = "admin";

		BSUserServiceImpl userService = new BSUserServiceImpl();

		User user = null;
		BSDataUtils dau = new BSDataUtils();
		Connection conn = null;
		try {
			conn = dau.getConnection("org.gjt.mm.mysql.Driver", "localhost",
					"bsframework", "12870668", "root");

			user = userService.login(conn, mail, password);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		assertNotNull(user);

	}

	@Test
	public void testLogin2() {
		String mail = "noUser";
		String password = "admin";

		BSUserServiceImpl userService = new BSUserServiceImpl();

		User user = null;
		BSDataUtils dau = new BSDataUtils();
		Connection conn = null;
		try {
			conn = dau.getConnection("org.gjt.mm.mysql.Driver", "localhost",
					"bsframework", "12870668", "root");

			user = userService.login(conn, mail, password);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		assertNull(user);

	}

	@Test
	public void testLogin3() {
		String mail = "demo";
		String password = "demo";

		BSUserServiceImpl userService = new BSUserServiceImpl();

		User user = null;
		BSDataUtils dau = new BSDataUtils();
		Connection conn = null;
		try {
			conn = dau.getConnection("org.gjt.mm.mysql.Driver", "localhost",
					"bsframework", "12870668", "root");

			user = userService.login(conn, mail, password);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		assertNull(user);

	}

}
