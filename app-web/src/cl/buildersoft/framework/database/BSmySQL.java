package cl.buildersoft.framework.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.framework.exception.BSDataBaseException;
import cl.buildersoft.framework.util.BSDataUtils;
import cl.buildersoft.framework.util.BSPaging;

public class BSmySQL extends BSDataUtils {

	public ResultSet queryResultSet(Connection conn, BSTableConfig table,
			BSPaging paging) {
		String sql = paging.getSQL(table);
		List<Object> prms = paging.getParams();

		if (paging.getRequiresPaging()) {
			sql += " LIMIT " + paging.getFirstRecord() + ","
					+ paging.getRecordPerPage();
		}

		ResultSet rs = queryResultSet(conn, sql, prms);

		return rs;
	}

	public String cloneTable(Connection conn, HttpServletRequest request,
			String table) {
		String out = request.getSession().getId() + "_" + table;
		String sql = "CREATE TABLE " + out + " LIKE " + table + ";";

		Statement statement;
		try {
			statement = conn.createStatement();
			statement.execute(sql);
			statement.close();
		} catch (SQLException e) {
			throw new BSDataBaseException("0300", e.getMessage());
		}

		return out;
	}
}
