package cl.buildersoft.web.open;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.util.BSConfig;
import cl.buildersoft.sso.filter.BSHttpServletSSO;

@WebServlet("/open/StartSession")
public class StartSession extends BSHttpServletSSO {

	private static final long serialVersionUID = 2003517134295990179L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = null;
		BSConfig config = new BSConfig();
		BSmySQL mysql = new BSmySQL();

		// BSDataUtils dau = new BSDataUtils();
		ServletContext ctx = request.getServletContext();
		Connection conn = mysql.getConnection2(ctx, (String) ctx.getAttribute("bsframework.datasource"));

		url = "/jsp/login/login.jsp";
		if (config.getBoolean(conn, "USE_BOOTSTRAP")) {
			url = "/jsp/login/login2.jsp";
		}
		super.fordward(request, response, url);
		// request.getRequestDispatcher(url).forward(request, response);
		// response.sendRedirect(url);

	}

}
