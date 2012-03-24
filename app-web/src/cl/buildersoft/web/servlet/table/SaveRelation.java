package cl.buildersoft.web.servlet.table;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.buildersoft.framework.beans.BSAction;
import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.framework.database.BSmySQL;

/**
 * Servlet implementation class SaveRelation
 */
@WebServlet("/servlet/table/SaveRelation")
public class SaveRelation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SaveRelation() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/common/no-access.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Long id = Long.parseLong(request.getParameter("cId"));
		String[] relations = request.getParameterValues("Relation");

		HttpSession session = request.getSession();
		BSTableConfig table = null;
		synchronized (session) {
			table = (BSTableConfig) session.getAttribute("BSTable");

		}

		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request.getServletContext(),
				"bsframework");

		BSAction action = table.getAction("ROL_RELATION");

		try {
			conn.setAutoCommit(false);
			removeRelation(conn, mysql, id, table, action);

			setRelation(conn, mysql, id, relations);

			conn.commit();
			conn.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e2) {
				throw new RuntimeException(e2);
			}
			throw new RuntimeException(e);
		} finally {
			try {
				conn.close();
			} catch (Exception e2) {
				throw new RuntimeException(e2);
			}
		}
		String uri = table.getUri();
//		System.out.println("->" + uri);
		//"/servlet/admin/UserManager"
		request.getRequestDispatcher(uri).forward(request, response);

	}

	private void setRelation(Connection conn, BSmySQL mysql, Long id,
			String[] relations) {
		List<Object> prms = new ArrayList<Object>();
		String sql = "INSERT INTO tR_UserRol VALUES(?,?)";

		prms.add(id);

		for (String relation : relations) {
			prms.add(Long.parseLong(relation));

			mysql.update(conn, sql, prms);
			prms.remove(1);
		}

	}

	private void removeRelation(Connection conn, BSmySQL mysql, Long id,
			BSTableConfig table, BSAction action) {
		String tableName = action.getNatTable()[0];

		List<Object> prms = new ArrayList<Object>();
		String sql = "DELETE FROM " + tableName + " WHERE "
				+ table2Field(table.getTableName()) + "=?";

		prms.add(id);

		mysql.update(conn, sql, prms);
	}

	private String table2Field(String tableName) {
		return "c" + tableName.substring(1);
	}
}
