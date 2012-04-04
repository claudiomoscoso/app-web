package cl.buildersoft.web.servlet.login;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mysql.jdbc.DatabaseMetaData;

import cl.buildersoft.framework.beans.User;
import cl.buildersoft.framework.services.impl.BSUserServiceImpl;
import cl.buildersoft.framework.util.BSDataUtils;

public class DBTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testDB() {
		String mail = "admin";
		String password = "admin";

		BSUserServiceImpl userService = new BSUserServiceImpl();

		try {
			BSDataUtils dau = new BSDataUtils();
			Connection conn = null;
			conn = dau.getConnection("org.gjt.mm.mysql.Driver", "localhost",
					"remu", "admin", "root");
			DatabaseMetaData dbmd = (DatabaseMetaData) conn.getMetaData();
			ResultSet rs = dbmd.getImportedKeys(null,null,"tperson");
			
			while (rs.next()) {
				System.out.println("PKTABLE_NAME = "+ rs.getString("PKTABLE_NAME"));
				System.out.println("PKCOLUMN_NAME =" + rs.getString("PKCOLUMN_NAME"));
				System.out.println("FKTABLE_NAME = "+ rs.getString("FKTABLE_NAME"));
				System.out.println("FKCOLUMN_NAME =" + rs.getString("FKCOLUMN_NAME"));
			}
			rs.close();

			System.out.println("Exported keys:");
			rs = dbmd.getExportedKeys(null, null,"tcomuna");
			while (rs.next()) {
				System.out.println("PKTABLE_NAME = "+ rs.getString("PKTABLE_NAME"));
				System.out.println("PKCOLUMN_NAME =" + rs.getString("PKCOLUMN_NAME"));
				System.out.println("FKTABLE_NAME = "+ rs.getString("FKTABLE_NAME"));
				System.out.println("FKCOLUMN_NAME =" + rs.getString("FKCOLUMN_NAME"));
			}
			rs.close();
			conn.close();			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testLogin2() {
		String mail = "noUser";
		String password = "admin";

		BSUserServiceImpl userService = new BSUserServiceImpl();

		User user = null;
		BSDataUtils dau = new BSDataUtils();
		Connection conn = null;
		conn = dau.getConnection("org.gjt.mm.mysql.Driver", "localhost",
				"bsframework", "12870668", "root");

		user = userService.login(conn, mail, password);

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
		conn = dau.getConnection("org.gjt.mm.mysql.Driver", "localhost",
				"bsframework", "12870668", "root");

		user = userService.login(conn, mail, password);

		assertNull(user);

	}

}
