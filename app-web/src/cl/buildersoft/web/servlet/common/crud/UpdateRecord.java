package cl.buildersoft.web.servlet.common.crud;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.util.BSWeb;
import cl.buildersoft.framework.util.crud.BSField;
import cl.buildersoft.framework.util.crud.BSTableConfig;
import cl.buildersoft.web.servlet.common.AbstractServletUtil;

@WebServlet("/servlet/common/crud/UpdateRecord")
public class UpdateRecord extends AbstractServletUtil {
	private static final long serialVersionUID = 1L;

	public UpdateRecord() {
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

		// BSField[] fields = table.getFields();
		BSField idField = table.getIdField();
		BSField[] fieldsWidthoutId = table.deleteId();

		String sql = getSQL(table, fieldsWidthoutId, idField);

		List<Object> params;

		Connection conn = null;
		BSmySQL mysql = new BSmySQL();

		conn = mysql.getConnection(request);
		params = getParams(conn, request, fieldsWidthoutId, idField);

		mysql.update(conn, sql, params);
		mysql.closeConnection(conn);
		
		request.getRequestDispatcher(LoadTable.URL).forward(
				request, response);
	}

	private List<Object> getParams(Connection conn, HttpServletRequest request,
			BSField[] fieldsWidthoutId, BSField idField) {
		List<Object> out = new ArrayList<Object>();

		for (BSField field : fieldsWidthoutId) {
			out.add(BSWeb.value2Object(conn, request, field,true));
		}
		out.add(BSWeb.value2Object(conn, request, idField, true));

		return out;
	}

	private String getSQL(BSTableConfig table, BSField[] fieldsWidthoutId,
			BSField idField) {
		String sql = "UPDATE " + table.getDatabase() + "."
				+ table.getTableName();
		sql += " SET " + unSplit(fieldsWidthoutId, "=?,");
		sql += " WHERE " + idField.getName() + "=?";

		return sql;
	}
}
