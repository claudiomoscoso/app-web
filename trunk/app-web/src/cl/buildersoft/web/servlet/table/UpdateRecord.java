package cl.buildersoft.web.servlet.table;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.buildersoft.framework.beans.BSField;
import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.util.BSWeb;

@WebServlet("/servlet/table/UpdateRecord")
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

		BSField[] fields = table.getFields();
		BSField idField = getIdField(fields);
		BSField[] fieldsWidthoutId = deleteId(fields);

		String sql = getSQL(table, fieldsWidthoutId, idField);

		List<Object> params;
		try {
			params = getParams(request, fieldsWidthoutId, idField);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		Connection conn = null;
		BSmySQL mySQL = new BSmySQL();
		try {
			conn = mySQL.getConnection(getServletContext(), "bsframework");
			mySQL.update(conn, sql, params);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		request.getRequestDispatcher("/servlet/table/LoadTable").forward(request,
				response);
	}

	private List<Object> getParams(HttpServletRequest request,
			BSField[] fieldsWidthoutId, BSField idField) throws Exception {
		List<Object> out = new ArrayList<Object>();

		for (BSField field : fieldsWidthoutId) {
			out.add(BSWeb.value2Object(request, field));
		}
		out.add(BSWeb.value2Object(request, idField));
		 
		return out;
	}

	private String getSQL(BSTableConfig table, BSField[] fieldsWidthoutId,
			BSField idField) {
		String sql = "UPDATE " + table.getTableName();
		sql += " SET " + unSplit(fieldsWidthoutId, "=?,");
		sql += " WHERE " + idField.getName() + "=?";

		return sql;
	}
}
