package cl.buildersoft.framework.util;

import java.sql.Connection;

public class BSConfig extends BSDataUtils {
	public String getString(Connection conn, String key) {
		return getValue(conn, key);
	}

	public Integer getInteger(Connection conn, String key) {
		return Integer.parseInt(getValue(conn, key));
	}

	public Double getDouble(Connection conn, String key) {
		return Double.parseDouble(getValue(conn, key));
	}

	public Boolean getBoolean(Connection conn, String key) {
		return Boolean.parseBoolean(getValue(conn, key));
	}

	private String getValue(Connection conn, String key) {
		String sql = "SELECT cValue FROM tConfig WHERE cKey=?";
		return super.queryField(conn, sql, key);
	}
}
