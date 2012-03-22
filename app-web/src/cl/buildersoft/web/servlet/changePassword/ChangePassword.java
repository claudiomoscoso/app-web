package cl.buildersoft.web.servlet.changePassword;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.framework.beans.User;
import cl.buildersoft.framework.database.BSBeanUtils;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.util.BSSecurity;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/servlet/changePassword/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChangePassword() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/common/no-access.jsp")
				.forward(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String newPassword = request.getParameter("NewPassword");
		String commitPassword = request.getParameter("CommitPassword");
		String oldPassword = null;
//		String currentPassword = null;

		if (!newPassword.equals(commitPassword)) {
			throw new RuntimeException("Las claves no coinciden");
		}

		Long id = Long.parseLong(request.getParameter("cId"));

		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(getServletContext(),
				"bsframework");

		BSBeanUtils bu = new BSBeanUtils();
		User user = new User();
		user.setId(id);
		bu.search(conn, user);
		String	currentPasswordMD5 = user.getPassword();

		if (currentPasswordMD5 != null) {
			oldPassword = request.getParameter("OldPassword");

//			String currentPasswordMD5 = md5(currentPassword);
			String oldPasswordMD5 = md5(oldPassword);

			if (!currentPasswordMD5.equals(oldPasswordMD5)) {
				throw new RuntimeException("La clave actual no conicide");
			}
		}

		newPassword = md5(newPassword);
		user.setPassword(newPassword);
		bu.update(conn, user);

		request.getRequestDispatcher("/servlet/table/LoadTable").forward(
				request, response);

	}

	private String md5(String newPassword) {
		BSSecurity security = new BSSecurity();
		return security.md5(newPassword);
	}
}
