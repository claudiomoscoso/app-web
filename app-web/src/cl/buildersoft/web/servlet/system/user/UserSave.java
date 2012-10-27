package cl.buildersoft.web.servlet.system.user;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.framework.beans.BSBean;
import cl.buildersoft.framework.beans.User;
import cl.buildersoft.framework.database.BSBeanUtils;
import cl.buildersoft.framework.database.BSmySQL;

/**
 * Servlet implementation class UserSave
 */
@WebServlet("/servlet/system/user/UserSave")
public class UserSave extends HttpServlet {
	private static final long serialVersionUID = 2626535852650186256L;

	public UserSave() {
		super();
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String idString = request.getParameter("cId");
		Long id = null;
		if (idString.trim().length() > 0) {
			id = Long.parseLong(idString);
		}
		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request.getServletContext(),
				"bsframework");

		BSBeanUtils bu = new BSBeanUtils();
		User user = new User();
		user.setId(id);

		bu.search(conn, user);
		user.setAdmin(request.getParameter("cAdmin") != null);
		user.setMail(request.getParameter("cMail"));
		user.setName(request.getParameter("cName"));
		bu.save(conn, user);
		mysql.closeConnection(conn);
		/**
		 * <code>	
		List<Object> prms = new ArrayList<Object>();

		String mail = request.getParameter("cMail");
		String name = request.getParameter("cName");
		Boolean admin = request.getParameter("cAdmin") != null;

		prms.add(id);
		prms.add(mail);
		prms.add(name);
		prms.add(null);
		prms.add(admin);

		mysql.callSingleSP(conn, "pSetUserSave", prms);
</code>
		 */
		request.getRequestDispatcher("/servlet/system/user/UserManager")
				.forward(request, response);

	}

}
