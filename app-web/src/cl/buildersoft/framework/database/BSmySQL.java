package cl.buildersoft.framework.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import cl.buildersoft.framework.util.BSDataUtils;

public class BSmySQL extends BSDataUtils {
	public ResultSet queryResultSet(Connection conn, String sql,
			List<Object> parameters) {
		return super.queryResultSet(conn, sql, parameters);
	}

	public Integer update(Connection conn, String sql, List<Object> parameter) {
		return super.update(conn, sql, parameter);
	}

	public Long insert(Connection conn, String sql, List<Object> parameter) {
		return super.insert(conn, sql, parameter);
	}

}
