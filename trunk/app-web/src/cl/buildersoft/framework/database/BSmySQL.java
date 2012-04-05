package cl.buildersoft.framework.database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.framework.exception.BSDataBaseException;
import cl.buildersoft.framework.util.BSDataUtils;
import cl.buildersoft.framework.util.BSPaging;

public class BSmySQL extends BSDataUtils {
	CallableStatement callableStatement;

	@Override
	public void closeSQL() {
		if (this.callableStatement != null) {
			try {
				this.callableStatement.close();
			} catch (SQLException e) {
				throw new BSDataBaseException("0300", e.getMessage());
			}
		}
		super.closeSQL();
	}

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

	@Deprecated
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

	public List<Object> callComplexSP(Connection conn, String name,
			List<Object> parameter) {

		String sqlStatement = getSQL4SP(name, parameter);

		List<Object> out = new ArrayList<Object>();

		try {
			this.callableStatement = conn.prepareCall(sqlStatement);
			parametersToStatement(parameter, callableStatement);

			// this.callableStatement.execute();
			// this.callableStatement = conn.prepareCall(sqlStatement);

			Boolean moreResults = Boolean.TRUE;

			Boolean isResultSet = this.callableStatement.execute();

			ResultSet rs = this.callableStatement.getResultSet();
			out.add(rs);

			while (moreResults) {
				if (isResultSet) {
					rs = this.callableStatement.getResultSet();
					out.add(rs);
					out.add(resultSet2Map(rs));
					rs.close();
				} else {
					Integer rowsAffected = this.callableStatement
							.getUpdateCount();
					if (rowsAffected == -1) {
						moreResults = Boolean.FALSE;
					}
				}
				if (moreResults) {
					isResultSet = this.callableStatement.getMoreResults();
				}
			}
		} catch (SQLException e) {
			throw new BSDataBaseException("0300", e.getMessage());
		}
		return out;

	}

	public ResultSet callSingleSP(Connection conn, String name,
			List<Object> parameter) {
		String sqlStatement = getSQL4SP(name, parameter);

		ResultSet out = null;

		try {
			this.callableStatement = conn.prepareCall(sqlStatement);
			parametersToStatement(parameter, callableStatement);

			Boolean moreResults = Boolean.TRUE;
			Boolean isResultSet = this.callableStatement.execute();

			while (moreResults) {
				if (isResultSet) {
					out = this.callableStatement.getResultSet();
					moreResults = Boolean.FALSE;
				} else {
					Integer rowsAffected = this.callableStatement
							.getUpdateCount();
					if (rowsAffected == -1) {
						moreResults = Boolean.FALSE;
					}
				}
				if (moreResults) {
					isResultSet = this.callableStatement.getMoreResults();
				}
			}
		} catch (SQLException e) {
			throw new BSDataBaseException("0300", e.getMessage());
		}
		return out;
	}

	private String getSQL4SP(String name, List<Object> parameter) {
		String questionMarks = getQuestionMarks(parameter);
		String sqlStatement = null;
		if (questionMarks != null) {
			sqlStatement = "{call " + name + "(" + questionMarks + ")}";
		} else {
			sqlStatement = "{call " + name + "}";
		}
		return sqlStatement;
	}

	private String getQuestionMarks(List<Object> prms) {
		String out = null;

		if (prms != null && prms.size() > 0) {
			out = "";

			Iterator<Object> iter = prms.iterator();
			while (iter.hasNext()) {
				out += "?,";
				iter.next();
			}
			out = out.substring(0, out.length() - 1);
		}
		return out;
	}

	public Map<Long, Map<String, Object>> resultSet2Map(ResultSet rs) {
		Map<Long, Map<String, Object>> out = new HashMap<Long, Map<String, Object>>();

		Integer i = 0;
		Integer colCount = 0;
		String[] colNames = null;
		try {
			ResultSetMetaData metaData = rs.getMetaData();
			colCount = metaData.getColumnCount();
			colNames = new String[colCount - 1];
			String colName = null;

			for (i = 2; i <= colCount; i++) {
				colName = metaData.getColumnName(i);
				// if (colName.equalsIgnoreCase("cid")) {
				colNames[i - 2] = colName;
				// }
			}
		} catch (Exception e) {
			throw new BSDataBaseException("0300",
					"Error al trabajar con la definicion de una tabla "
							+ e.getMessage());
		}

		Map<String, Object> innerData = null;
		try {
			while (rs.next()) {
				Long key = rs.getLong(1);
				for (String colName : colNames) {
					innerData = new HashMap<String, Object>();
					innerData.put(colName, rs.getObject(colName));
					out.put(key, innerData);
				}
			}
		} catch (SQLException e) {
			throw new BSDataBaseException("0300",
					"Error al recorrer un ResultSet " + e.getMessage() + " "
							+ e.getLocalizedMessage());
		}
		this.closeSQL(rs);

		return out;
	}
}
