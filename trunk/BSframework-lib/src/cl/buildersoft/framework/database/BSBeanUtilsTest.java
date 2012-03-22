package cl.buildersoft.framework.database;

import static org.junit.Assert.fail;

import java.sql.Connection;

import org.junit.Test;

import cl.buildersoft.framework.beans.User;
import cl.buildersoft.framework.util.BSDataUtils;

public class BSBeanUtilsTest {

	@Test
	public void testSearch() throws Exception {
		BSDataUtils du = new BSDataUtils();

		Connection conn = du.getConnection("org.gjt.mm.mysql.Driver",
				"localhost", "bsframework", "12870668", "root");

		User user = new User();
		user.setId(1L);

		fail("Not yet implemented");
	}

}
