package cl.buildersoft.framework.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import cl.buildersoft.framework.util.BSDataUtils;

public class BSmySQL extends BSDataUtils {

	public ResultSet queryResultSet(Connection conn, String sql,
			List<Object> parameters, Integer firstRecord, Integer lastRecord) {
		if (firstRecord == null) {
			firstRecord = 0;
		}
		if (lastRecord == null) {
			lastRecord = 10;
		}

		sql += " LIMIT " + firstRecord + "," + lastRecord;

		ResultSet rs = super.queryResultSet(conn, sql, parameters);

		return rs;

	}

}
