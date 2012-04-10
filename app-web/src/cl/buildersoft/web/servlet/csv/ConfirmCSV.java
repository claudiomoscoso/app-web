package cl.buildersoft.web.servlet.csv;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.buildersoft.framework.beans.BSField;
import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.type.BSData;
import cl.buildersoft.framework.type.BSFieldDataType;
import cl.buildersoft.framework.type.BSTypeFactory;
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
		List<Map<String, BSData>> allData = null;
		synchronized (session) {
			table = (BSTableConfig) session.getAttribute("BSTable");
			allData = (List<Map<String, BSData>>) session
					.getAttribute("respCSV");
		}

		String sqlInsert = getSQLInsert(table);
		String sqlUpdate = getSQLUpdate(table);

		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request.getServletContext(),
				table.getDatabase());

		List<Object> prms = null;
		String sql = null;
		Long id = null;
		mysql.setAutoCommit(conn, false);
		try {
			for (Map<String, BSData> row : allData) {
				id = exists(conn, mysql, row, table);
				if (id != null) {
					sql = sqlUpdate;
					prms = getValues4Update(conn, table, row);
					prms.add(id);
				} else {
					sql = sqlInsert;
					prms = getValues4Insert(conn, table, row);
				}
				mysql.update(conn, sql, prms);
			}
			mysql.commit(conn);
		} catch (Exception e) {
			mysql.rollback(conn);
		}
		mysql.setAutoCommit(conn, true);

		String page = "/servlet/Home";
		request.getRequestDispatcher(page).forward(request, response);

	}

	private Long exists(Connection conn, BSmySQL mysql,
			Map<String, BSData> row, BSTableConfig table) {
		BSField keyField = table.getKeyField(conn);
		BSData data = row.get(keyField.getName());

		String sql = "SELECT cId FROM " + table.getDatabase() + "."
				+ table.getTableName();
		sql += " WHERE " + keyField.getName() + "=?";

		String idString = mysql.queryField(conn, sql, data.getValue());
		Long out = null;
		if (idString != null) {
			out = Long.parseLong(idString);
		}
		return out;
	}

	// 17 + 13
	private String getSQLInsert(BSTableConfig table) {
		BSField[] fields = table.deleteId();
		String sql = "INSERT INTO " + table.getDatabase() + "."
				+ table.getTableName();
		sql += "(" + unSplit(fields, ",") + ") ";
		sql += " VALUES (" + getCommas(fields) + ")";
		return sql;
	}

	private String getSQLUpdate(BSTableConfig table) {
		String sql = "UPDATE " + table.getDatabase() + "."
				+ table.getTableName();
		sql += " SET " + unSplit(table.deleteId(), "=?,");
		sql += " WHERE " + table.getIdField().getName() + "=?";
		return sql;
	}

	/**
	 * <code>
	private void insert(Connection conn, BSmySQL mySQL, BSTableConfig table) {
		String sql = getSQLInsert(table);
		List<Object> params = null;
		params = getValues4Insert(table.getFields());
		mySQL.update(conn, sql, params);
	}
</code>
	 */
	/**
	 * <code>
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
	</code>
	 */

	private List<Object> getValues4Insert(Connection conn, BSTableConfig table,
			Map<String, BSData> row) {
		List<Object> out = new ArrayList<Object>();
		BSData data = null;
		for (BSField field : table.deleteId()) {
			data = row.get(field.getName());

			BSFieldDataType type = BSTypeFactory.create(field);
			Object value = type.parse(conn, data.getValue());

			out.add(value);
		}
		return out;
	}

	private List<Object> getValues4Update(Connection conn, BSTableConfig table,
			Map<String, BSData> row) {
		List<Object> out = new ArrayList<Object>();
		String valueString = null;
		Object value = null;
		for (BSField field : table.deleteId()) {
			// value = new String(field.getValue().toString());
			valueString = row.get(field.getName()).getValue();
			BSFieldDataType type = BSTypeFactory.create(field);

			value = type.parse(conn, valueString);

			out.add(value);
		}

		return out;
	}

}
