package cl.buildersoft.web.servlet.csv;

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
import cl.buildersoft.web.servlet.table.AbstractServletUtil;

@WebServlet("/servlet/csv/ConfirmCSV")
public class ConfirmCSV extends AbstractServletUtil {
	private static final long serialVersionUID = 1L;

	public ConfirmCSV() {
		super();
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		BSTableConfig table = null;
		synchronized (session) {
			table = (BSTableConfig) session.getAttribute("BSTable");

		}

		
		
		response.getWriter().print(";)");
	}

	private void insertData(BSmySQL mySQL, BSTableConfig table, BSField[] fields) {
		Connection conn;
		String sql = getSQL(table, fields);
		List<Object> params = getValues4Insert(fields);

		conn = mySQL.getConnection(getServletContext(), "bsframework");
		mySQL.insert(conn, sql, params);
	}

	private void copyTypeTableToField(BSField[] tableField, BSField[] fields) {
		Field: for (BSField field : fields) {

			for (BSField tblField : tableField) {
				if (tblField.getName().equals(field.getName())) {
					field.setType(tblField.getType());
					continue Field;
				}
			}
		}

	}

	private String getSQL(BSTableConfig table, BSField[] fields) {
		// fields = deleteId(fields);
		String sql = "INSERT INTO " + table.getDatabase() + "."
				+ table.getTableName();
		sql += "(" + unSplit(fields, ",") + ") ";
		sql += " VALUES (" + getCommas(fields) + ")";

		return sql;
	}

	private List<Object> getValues4Insert(BSField[] fields) {

		List<Object> out = new ArrayList<Object>();
		Object value = null;

		for (BSField field : fields) {
			value = new String(field.getValue().toString());
			out.add(value);

		}
		return out;
	}

}
