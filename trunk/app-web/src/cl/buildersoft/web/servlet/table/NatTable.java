package cl.buildersoft.web.servlet.table;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
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
 * Servlet implementation class NatTable
 */
@WebServlet("/servlet/table/NatTable")
public class NatTable extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public NatTable() {
		super();

	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = null;
		synchronized (request) {
			session = request.getSession();
		}

		String code = request.getParameter("CodeAction");
		Long id = Long.parseLong(request.getParameter("cId"));

		BSTableConfig table = (BSTableConfig) session.getAttribute("BSTable");
		BSAction action = table.getAction(code);

		String sql = getSQL(action, table.getTableName());

		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request.getServletContext(),
				"bsframework");

		ResultSet relation = getRelation(id, sql, mysql, conn);
		request.setAttribute("Relation", relation);

		ResultSet list = getList(action, mysql, conn);

		request.setAttribute("List", list);

		request.getRequestDispatcher("/WEB-INF/jsp/table/relation-nat.jsp")
				.forward(request, response);
	}

	private ResultSet getList(BSAction action, BSmySQL mysql, Connection conn) {
		String sql;
		sql = "SELECT * FROM " + action.getNatTable()[1];
		ResultSet list = mysql.queryResultSet(conn, sql, null);
		return list;
	}

	private ResultSet getRelation(Long id, String sql, BSmySQL mysql,
			Connection conn) {
		List<Object> prms = new ArrayList<Object>();
		prms.add(id);
		ResultSet relation = mysql.queryResultSet(conn, sql, prms);
		return relation;
	}

	private String getSQL(BSAction action, String tableName) {
		/**
		 * <code>
SELECT * FROM tUser AS a
LEFT JOIN tR_UserRol AS b ON a.cId = b.cUser
LEFT JOIN tRol AS c ON b.cRol = c.cId; 
		  </code>
		 */
		String tables[] = action.getNatTable();
		String sql = "SELECT c.* FROM " + tableName + " AS a ";
		sql += "LEFT JOIN " + tables[0] + " AS b ON a.cId = b."
				+ table2Field(tableName) + " ";
		sql += "LEFT JOIN " + tables[1] + " AS c ON b."
				+ table2Field(tables[1]) + " = c.cId ";
		sql += "WHERE a.cId=? AND c.cId IS NOT NULL";
		// System.out.println(sql);
		return sql;
	}

	private String table2Field(String tableName) {
		return "c" + tableName.substring(1);
	}

}
