package cl.buildersoft.web.servlet.system.changepassword;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.lib.beans.User;
import cl.buildersoft.lib.database.BSBeanUtils;
import cl.buildersoft.lib.database.BSmySQL;
import cl.buildersoft.sso.filter.BSHttpServletSSO;
import cl.buildersoft.web.servlet.common.BSHttpServlet;

@WebServlet("/servlet/system/changepassword/SearchPassword")
public class SearchPassword extends BSHttpServlet {
	private static final long serialVersionUID = 7455312993130724891L;

	public SearchPassword() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id;

		String idString = request.getParameter("cId");
		if (idString == null) {
			User user = (User) request.getSession().getAttribute("User");
			id = user.getId();
			request.setAttribute("cId", id);
		} else {
			id = Long.parseLong(idString);
		}

		BSmySQL mysql = new BSmySQL();
		BSHttpServletSSO servletUtil = new BSHttpServletSSO();
		Connection conn = servletUtil.getConnection();
		// Connection conn =
		// servletUtil.getConnection(request.getServletContext(),
		// "bsframework");

		BSBeanUtils bu = new BSBeanUtils();
		User user = new User();
		user.setId(id);
		bu.search(conn, user);
		mysql.closeConnection(conn);

		request.setAttribute("PASS_IS_NULL", user.getPassword() == null);

		forward(request, response, "/WEB-INF/jsp/system/change-password/change-password.jsp");
		// request.getRequestDispatcher("/WEB-INF/jsp/system/change-password/change-password.jsp").forward(request,
		// response);
	}
}
