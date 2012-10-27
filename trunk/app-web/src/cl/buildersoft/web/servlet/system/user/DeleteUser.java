package cl.buildersoft.web.servlet.system.user;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.framework.database.BSmySQL;

@WebServlet("/servlet/system/user/DeleteUser")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteUser() {
		super();

	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] ids = request.getParameterValues("cId");

		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request.getServletContext(), "bsframework");

		for (String id : ids) {
			mysql.callSingleSP(conn, "pDelUser", id);
		}
		mysql.closeSQL();
		mysql.closeConnection(conn);
		request.getRequestDispatcher("/servlet/system/user/UserManager").forward(request, response);
	}

}
