package cl.buildersoft.framework.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.framework.util.BSDataUtils;
import cl.buildersoft.framework.util.BSPaging;

public class BSmySQL extends BSDataUtils {

	public ResultSet queryResultSet(Connection conn, BSTableConfig table,
			BSPaging paging) {
		/**
		 * <code>, String sql,
			List<Object> parameters, Integer firstRecord, Integer lastRecord</code>
		 */

		String sql = paging.getSQL(table);
		List<Object> prms = paging.getParams();

		if (paging.getRequiresPaging()) {
			sql += " LIMIT " + paging.getFirstRecord() + ","
					+ paging.getRecordPerPage();
		}

		ResultSet rs = queryResultSet(conn, sql, prms);

		return rs;
	}
}
