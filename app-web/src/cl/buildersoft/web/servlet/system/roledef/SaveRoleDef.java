package cl.buildersoft.web.servlet.system.roledef;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.buildersoft.lib.beans.Rol;
import cl.buildersoft.lib.database.BSmySQL;
import cl.buildersoft.sso.filter.BSHttpServletSSO;
import cl.buildersoft.web.servlet.common.BSHttpServlet;

@WebServlet("/servlet/system/roledef/SaveRoleDef")
public class SaveRoleDef extends BSHttpServlet {
	private static final long serialVersionUID = -2938011475821935220L;

	public SaveRoleDef() {
		super();

	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] options = request.getParameterValues("Option");
		Long rol = Long.parseLong(request.getParameter("Rol"));

		BSmySQL mysql = new BSmySQL();
		BSHttpServletSSO servletUtil = new BSHttpServletSSO();
		Connection conn = servletUtil.getConnection(request);

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
			mysql.closeConnection(conn);
		}

		String nextServlet = "/servlet/system/roleDef/RoleDef";

		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<Rol> rols = (List<Rol>) session.getAttribute("Rol");
		for (Rol rolUser : rols) {
			if (rolUser.getId().equals(rol)) {
				nextServlet = "/servlet/login/GetMenuServlet";
				break;
			}
		}

		forward(request, response, nextServlet);
		// request.getRequestDispatcher(nextServlet).forward(request, response);

	}

	private void saveNewDef(Connection conn, BSmySQL mysql, String[] options, Long rol) {
		String sql = "INSERT INTO tR_RolOption(cRol, cOption) VALUES(?,?);";
		List<Object> prms = new ArrayList<Object>();
		prms.add(rol);

		for (String option : options) {
			prms.add(Long.parseLong(option));
			mysql.update(conn, sql, prms);
			// printSQL(sql, prms);
			prms.remove(1);
		}
	}

	/**
	 * <code>
	private void printSQL(String sql, List<Object> prms) {
		String s = sql;
		s = s.replaceFirst("[?]", "@rolId");
		s = s.replaceFirst("[?]", prms.get(1).toString());
		System.out.println(s);
	}
</code>
	 */
	private void deleteRolDef(Connection conn, BSmySQL mysql, Long rol) {
		String sql = "DELETE FROM tR_RolOption WHERE cRol=?";
		List<Object> prms = new ArrayList<Object>();
		prms.add(rol);
		mysql.update(conn, sql, prms);
	}
}
