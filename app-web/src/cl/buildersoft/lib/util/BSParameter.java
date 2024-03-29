package cl.buildersoft.lib.util;

import java.sql.Connection;

public class BSParameter extends BSDataUtils {
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

	public char getCSVSeparator(Connection conn) {
		String seperator = getString(conn, "CSV_SEPARATOR");
		return seperator.toCharArray()[0];
	}

	public Integer getRecordsPerPage(Connection conn) {
		return getInteger(conn, "RECORDS_PER_PAGE");
	}

	@Deprecated
	public static String getFileSeparator() {
		return System.getProperty("file.separator");
	}

	public String getFilePath(Connection conn) {
		return fixPath(getString(conn, "EMPLOYEE_FILES"));
	}

	public String fixPath(String path) {
		String fileSeparator = BSParameter.getFileSeparator();
		if (path.lastIndexOf(fileSeparator) < path.length()) {
			path += fileSeparator;
		}
		return path;
	}
}
