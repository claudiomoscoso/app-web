package cl.buildersoft.web.servlet.common.crud;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.buildersoft.lib.database.BSmySQL;
import cl.buildersoft.lib.exception.BSDataBaseException;
import cl.buildersoft.lib.util.crud.BSField;
import cl.buildersoft.lib.util.crud.BSTableConfig;
import cl.buildersoft.web.servlet.common.BSHttpServlet;

import static cl.buildersoft.web.servlet.common.AbstractServletUtil.*;

@WebServlet("/servlet/common/crud/SearchRecord")
public class SearchRecord extends BSHttpServlet {

	private static final long serialVersionUID = -5785656616097922095L;

	public SearchRecord() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		BSTableConfig table = null;
		synchronized (session) {
			table = (BSTableConfig) session.getAttribute("BSTable");
		}

		String idField = table.getIdField().getName();
		String sql = getSQL4Search(table, idField);
		Long id = Long.parseLong(request.getParameter(idField));

		Connection conn = null;
		BSmySQL mysql = new BSmySQL();

		conn = getConnection(request);
		ResultSet rs = mysql.queryResultSet(conn, sql, array2List(id));
		resultset2Table(rs, table);

		mysql.closeConnection(conn);

		// request.setAttribute("Data", rs);
		// request.setAttribute("Conn", conn);

		request.setAttribute("Action", "Update");
		request.getRequestDispatcher("/WEB-INF/jsp/table/data-form.jsp").forward(request, response);
	}

	private void resultset2Table(ResultSet rs, BSTableConfig table) {
		BSField[] fields = table.getFields();
		try {
			if (rs.next()) {
				for (BSField f : fields) {
					f.setValue(rs.getObject(f.getName()));
				}
			}
		} catch (SQLException e) {
			throw new BSDataBaseException(e);
		}
	}

	private String getSQL4Search(BSTableConfig table, String idField) {
		BSField[] fields = table.getFields();
		String sql = "SELECT " + getFieldsNamesWithCommas(fields);
		sql += " FROM " + table.getDatabase() + "." + table.getTableOrViewName();
		sql += " WHERE " + idField + "=?";
		return sql;
	}
}
