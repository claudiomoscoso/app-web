package cl.buildersoft.web.servlet.system.user;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.lib.beans.User;
import cl.buildersoft.lib.database.BSBeanUtils;
import cl.buildersoft.lib.database.BSmySQL;

/**
 * Servlet implementation class UserEdit
 */

@WebServlet("/servlet/system/user/UserEdit_")
public class UserEdit_ extends HttpServlet {

	private static final long serialVersionUID = -6306199091852899234L;

	public UserEdit_() {
		super();

	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("cId"));

		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request.getServletContext(), "bsframework");

		BSBeanUtils bu = new BSBeanUtils();
		User user = new User();
		user.setId(id);
		bu.search(conn, user);
		mysql.closeConnection(conn);

		request.setAttribute("User", user);

		request.getRequestDispatcher("/WEB-INF/jsp/system/user/user-form.jsp").forward(request, response);

	}

}
