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

@WebServlet("/servlet/table/InsertRecord")
public class InsertRecord extends AbstractServletUtil {

	private static final long serialVersionUID = 947236230190327847L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/common/no-access.jsp")
				.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		BSTableConfig table = null;

		synchronized (session) {
			table = (BSTableConfig) session.getAttribute("BSTable");
		}

		String tableName = table.getTableName();
		BSField[] fields = deleteId(table.getFields());
		String sql = getSQL(tableName, fields, request);

		List<Object> params = getValues4Insert(request, fields);

		BSmySQL mySQL = new BSmySQL();
		Connection conn = null;
		try {
			conn = mySQL.getConnection(getServletContext(), "bsframework");
			mySQL.insert(conn, sql, params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		request.getRequestDispatcher("/servlet/table/LoadTable").forward(request,
				response);
	}

	private String getSQL(String tableName, BSField[] fields,
			HttpServletRequest request) {
		// fields = deleteId(fields);
		String sql = "INSERT INTO " + tableName;
		sql += "(" + unSplit(fields, ",") + ") ";
		sql += " VALUES (" + getCommas(fields) + ")";

		return sql;
	}

	private List<Object> getValues4Insert(HttpServletRequest request,
			BSField[] fields) {

		List<Object> out = new ArrayList<Object>();
		Object value = null;

		for (BSField field : fields) {
			try {
				value = BSWeb.value2Object(request, field);
				out.add(value);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}
		return out;
	}

}
