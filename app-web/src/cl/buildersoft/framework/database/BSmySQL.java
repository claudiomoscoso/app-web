package cl.buildersoft.framework.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cl.buildersoft.framework.util.BSDataUtils;

public class BSmySQL extends BSDataUtils {
	public ResultSet queryResultSet(Connection conn, String sql,
			List<Object> parameters) throws SQLException {
		return super.queryResultSet(conn, sql, parameters);
	}

	public Integer update(Connection conn, String sql, List<Object> parameter)
			throws SQLException {
		return super.update(conn, sql, parameter);
	}

	public Long insert(Connection conn, String sql, List<Object> parameter)
			throws SQLException {
		return super.insert(conn, sql, parameter);
	}

}
