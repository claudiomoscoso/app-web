package cl.buildersoft.web.servlet.table;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.buildersoft.framework.beans.BSField;
import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.type.BSFieldType;

/**
 * Servlet implementation class LoadTable
 */
@WebServlet("/servlet/table/LoadTable")
public class LoadTable extends AbstractServletUtil {
	private static final long serialVersionUID = 1L;

	public LoadTable() {
		super();
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		BSTableConfig table = null;
		synchronized (session) {
			table = (BSTableConfig) session.getAttribute("BSTable");
		}
		String sql = getSQL4SelectAll(table);

		Connection conn = null;
		BSmySQL mySQL = new BSmySQL();
		try {
			conn = mySQL.getConnection(getServletContext(), "bsframework");
			ResultSet rs = mySQL.queryResultSet(conn, sql, null);

			request.setAttribute("Data", rs);

			configFields(rs, table);

			synchronized (session) {
				session.setAttribute("BSTable", table);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		request.getRequestDispatcher("/WEB-INF/jsp/table/main.jsp").forward(
				request, response);
	}

	private void configFields(ResultSet rs, BSTableConfig table)
			throws Exception {
		BSField[] fields = table.getFields();
		ResultSetMetaData metaData = rs.getMetaData();
		String name = null;

		if (fields.length == 0) {
			Integer n = metaData.getColumnCount();

			BSField field = null;

			for (Integer i = 1; i < n; i++) {
				name = metaData.getColumnName(i);
				field = new BSField(name, name);
				configField(metaData, name, i, field);

				table.addField(field);
			}
		} else {
			Integer i = 1;
			for (BSField field : fields) {
				name = field.getName();

				configField(metaData, name, i, field);

				i++;
			}

		}
	}

	private void configField(ResultSetMetaData metaData, String name,
			Integer i, BSField field) throws Exception {

		if (field.getType() == null) {
			setRealType(metaData, i, field);
		}
		if (field.isPk() == null) {
			field.setPk(metaData.isAutoIncrement(i));
		}
		if (field.getLength() == null) {
			field.setLength(metaData.getColumnDisplaySize(i));
		}
	}

	private void setRealType(ResultSetMetaData metaData, Integer i,
			BSField field) throws Exception {
		/**
		 * <code>
		System.out.println( name + " "+ metaData.getColumnTypeName(i));
		cId BIGINT
		cName VARCHAR
		cBorn DATE
		cLastLogin TIMESTAMP
		cSalary DOUBLE
		</code>
		 */
		if (metaData.getColumnTypeName(i).equals("BIGINT")) {
			field.setType(BSFieldType.Long);
		} else if (metaData.getColumnTypeName(i).equals("VARCHAR")) {
			field.setType(BSFieldType.String);
		} else if (metaData.getColumnTypeName(i).equals("DATE")) {
			field.setType(BSFieldType.Date);
		} else if (metaData.getColumnTypeName(i).equals("TIMESTAMP")) {
			field.setType(BSFieldType.Datetime);
		} else if (metaData.getColumnTypeName(i).equals("DOUBLE")) {
			field.setType(BSFieldType.Double);
		} else if (metaData.getColumnTypeName(i).equals("BIT")) {
			field.setType(BSFieldType.Boolean);
		}
	}

	private String getSQL4SelectAll(BSTableConfig table) {
		BSField[] fields = table.getFields();
		String sql = "SELECT " + getFieldsNames(fields);
		sql += " FROM " + table.getTableName();
		return sql;
	}

	protected String getFieldsNames(BSField[] fields) {
		String out = "";
		if (fields.length == 0) {
			out = "*";
		} else {
			for (BSField field : fields) {
				out += field.getName() + ",";
			}
			out = out.substring(0, out.length() - 1);
		}
		return out;
	}

}
