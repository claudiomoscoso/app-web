package cl.buildersoft.web.servlet.admin;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.framework.database.BSmySQL;

@WebServlet("/servlet/admin/SaveRoleDef")
public class SaveRoleDef extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SaveRoleDef() {
		super();

	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String[] options = request.getParameterValues("Option");
		Long rol = Long.parseLong(request.getParameter("Rol"));

		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request.getServletContext(),
				"bsframework");
		try {
			mysql.setAutoCommit(conn, false);

			deleteRolDef(conn, mysql, rol);
			saveNewDef(conn, mysql, options, rol);

			conn.commit();
		} catch (Exception e) {
			mysql.rollback(conn);
			throw new ServletException(e);
		} finally {
			mysql.closeSQL();
		}

		request.getRequestDispatcher("/servlet/admin/RoleDef").forward(request,
				response);

	}

	private void saveNewDef(Connection conn, BSmySQL mysql, String[] options,
			Long rol) {
		String sql = "INSERT INTO tR_RolOption(cRol, cOption) VALUES(?,?)";
		List<Object> prms = new ArrayList<Object>();
		prms.add(rol);

		for (String option : options) {
			prms.add(Long.parseLong(option));
			mysql.update(conn, sql, prms);
			prms.remove(1);
		}
	}

	private void deleteRolDef(Connection conn, BSmySQL mysql, Long rol) {
		String sql = "DELETE FROM tR_RolOption WHERE cRol=?";
		List<Object> prms = new ArrayList<Object>();
		prms.add(rol);
		mysql.update(conn, sql, prms);
	}
}
