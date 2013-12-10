package cl.buildersoft.web.servlet.common;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.framework.database.BSmySQL;

@WebServlet("/servlet/common/DeleteRecords")
public class DeleteRecords extends AbstractServletUtil {

	private static final long serialVersionUID = -2340853411641380529L;

	public DeleteRecords() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/common/no-access.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		BSTableConfig table = null;
		synchronized (session) {
			table = (BSTableConfig) session.getAttribute("BSTable");
		}
		BSmySQL mysql = new BSmySQL();
		Connection  conn = mysql.getConnection(request);
		
		String idField = table.getIdField().getName();
		String[] values = request.getParameterValues(idField);
		
		
		if (table.getDeleteSP() != null) {
			for (String value : values) {
				Long id = Long.parseLong(value);
				mysql.callSingleSP(conn, table.getDeleteSP() , id);
			}

		} else {
			String sql = getSQL4Search(table, idField);

			for (String value : values) {
				Long id = Long.parseLong(value);
				mysql.update(conn, sql, array2List(id));

			}
		}
		mysql.closeConnection(conn);

		request.getRequestDispatcher("/servlet/common/LoadTable").forward(
				request, response);
	}

	private String getSQL4Search(BSTableConfig table, String idField) {
		// BSField[] fields = table.getFields();
		String sql = "DELETE FROM " + table.getDatabase() + ".";
		sql += table.getTableName();
		sql += " WHERE " + idField + "=?";
		return sql;
	}

}
