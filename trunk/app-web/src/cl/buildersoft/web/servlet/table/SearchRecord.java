package cl.buildersoft.web.servlet.table;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.buildersoft.framework.beans.BSField;
import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.framework.database.BSmySQL;

/**
 * Servlet implementation class EditRecord
 */
@WebServlet("/servlet/table/SearchRecord")
public class SearchRecord extends AbstractServletUtil {

	private static final long serialVersionUID = -5785656616097922095L;

	public SearchRecord() {
		super();
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		BSTableConfig table = null;
		synchronized (session) {
			table = (BSTableConfig) session.getAttribute("BSTable");
		}

		String idField = getIdField(table.getFields()).getName();
		String sql = getSQL4Search(table, idField);
		Long id = Long.parseLong(request.getParameter(idField));

		Connection conn = null;
		BSmySQL mySQL = new BSmySQL();
		try {
			conn = mySQL.getConnection(getServletContext(), "bsframework");
			ResultSet rs = mySQL.queryResultSet(conn, sql, array2List(id));
			resultset2Table(rs, table);

			request.setAttribute("Data", rs);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		request.setAttribute("Action", "Update");
		request.getRequestDispatcher("/WEB-INF/jsp/table/data-form.jsp")
				.forward(request, response);
	}

	private void resultset2Table(ResultSet rs, BSTableConfig table)
			throws Exception {
		BSField[] fields = table.getFields();
		if (rs.next()) {
			for (BSField f : fields) {
				f.setValue(rs.getObject(f.getName()));
			}
		}
	}

	private String getSQL4Search(BSTableConfig table, String idField) {
		BSField[] fields = table.getFields();
		String sql = "SELECT " + getFieldsNames(fields);
		sql += " FROM " + table.getTableName();
		sql += " WHERE " + idField + "=?";
		return sql;
	}
}
