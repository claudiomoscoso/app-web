package cl.buildersoft.web.servlet.table;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.buildersoft.framework.beans.BSField;
import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.exception.BSDataBaseException;
import cl.buildersoft.framework.exception.BSProgrammerException;
import cl.buildersoft.framework.type.BSFieldType;
import cl.buildersoft.framework.util.BSPaging;

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
		/**
		 * request.getRequestDispatcher("/WEB-INF/jsp/common/pagination.jsp")
		 * .include(request, response);
		 */

		String sql = getSQL4SelectAll(table);

		Connection conn = null;
		BSmySQL mySQL = new BSmySQL();
		try {
			conn = mySQL.getConnection(getServletContext(), "bsframework");
			BSPaging paging = new BSPaging(conn, mySQL, table, request);

			ResultSet rs;
			if (paging.getRequiresPaging()) {
				rs = mySQL.queryResultSet(conn, sql, null,
						paging.getFirstRecord(), paging.getRecordPerPage());
			} else {
				rs = mySQL.queryResultSet(conn, sql, null);
			}

			request.setAttribute("Data", rs);
			request.setAttribute("Paging", paging);

			configFields(rs, table);
			configFKFields(conn, mySQL, table);

			synchronized (session) {
				session.setAttribute("BSTable", table);
			}

		} catch (SQLException e) {
			throw new BSDataBaseException("0300", e.getMessage());
		}

		request.getRequestDispatcher("/WEB-INF/jsp/table/main.jsp").forward(
				request, response);
	}

	private void configFKFields(Connection conn, BSmySQL mySQL,
			BSTableConfig table) {
		BSField[] fields = table.getFields();

		String tableFK = null;
		String fieldFK = null;

		for (BSField field : fields) {
			tableFK = field.getFKTable();
			fieldFK = field.getFKField();
			if (tableFK != null && fieldFK != null) {
				String sql = "SELECT cId," + fieldFK;
				sql += " FROM " + tableFK + " ORDER BY " + fieldFK;

				field.setFkData(mySQL.resultSet2Matrix(mySQL.queryResultSet(
						conn, sql, null)));
			}
		}
	}

	private void configFields(ResultSet rs, BSTableConfig table)
			throws SQLException {
		BSField[] fields = table.getFields();
		ResultSetMetaData metaData = rs.getMetaData();
		String name = null;

		if (fields.length == 0) {
			Integer n = metaData.getColumnCount();

			BSField field = null;
			Boolean hasPK = Boolean.FALSE;

			for (Integer i = 1; i < n; i++) {
				name = metaData.getColumnName(i);
				field = new BSField(name, name);
				configField(metaData, name, i, field);
				if (field.isPk() && !hasPK) {
					hasPK = Boolean.TRUE;
				}
				table.addField(field);
			}
			
			if(!hasPK){
				throw new BSProgrammerException("0104");
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
			Integer i, BSField field) throws SQLException {

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
			BSField field) throws SQLException {
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
		String typeName = metaData.getColumnTypeName(i);

		if (typeName.equals("BIGINT")) {
			field.setType(BSFieldType.Long);
		} else if (typeName.equals("VARCHAR")) {
			field.setType(BSFieldType.String);
		} else if (typeName.equals("DATE")) {
			field.setType(BSFieldType.Date);
		} else if (typeName.equals("TIMESTAMP")) {
			field.setType(BSFieldType.Datetime);
		} else if (typeName.equals("DOUBLE")) {
			field.setType(BSFieldType.Double);
		} else if (typeName.equals("BIT")) {
			field.setType(BSFieldType.Boolean);
		} else if (typeName.equals("INT")) {
			field.setType(BSFieldType.Integer);
		} else {
			throw new BSProgrammerException("0110",
					"No está catalogado el tipo " + typeName);
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
