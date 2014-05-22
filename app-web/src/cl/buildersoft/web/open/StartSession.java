package cl.buildersoft.web.open;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.util.BSConfig;

@WebServlet("/open/StartSession")
public class StartSession extends HttpServlet {

	private static final long serialVersionUID = 2003517134295990179L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = null;
		BSConfig config = new BSConfig();
		BSmySQL mysql = new BSmySQL();
		// Connection conn = mysql.getConnection(request);

		// String url = "/jsp/login/login.jsp";
		// if (config.getBoolean(conn, "USE_BOOTSTRAP")) {
		url = "jsp/login/login2.jsp";
		// }
		response.sendRedirect(url);

	}

}
