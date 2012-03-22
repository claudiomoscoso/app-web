package cl.buildersoft.framework.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

public class BSDataUtils extends BSUtils {
	PreparedStatement preparedStatement = null;

	protected void closeSQL(ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
		}
		closeSQL();
	}

	protected void closeSQL() throws SQLException {
		this.preparedStatement.close();
	}

	protected Integer update(Connection conn, String sql, Object parameter)
			throws SQLException {
		List<Object> prms = new ArrayList<Object>();
		prms.add(parameter);

		return update(conn, sql, prms);
	}

	protected Integer update(Connection conn, String sql, List<Object> parameter)
			throws SQLException {
		int rowsAffected = 0;

		preparedStatement = conn.prepareStatement(sql);
		parametersToStatement(parameter, preparedStatement);
		rowsAffected = preparedStatement.executeUpdate();

		return rowsAffected;
	}

	protected Long insert(Connection conn, String sql, List<Object> parameter)
			throws SQLException {
		Long newKey = 0L;

		preparedStatement = conn.prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS);
		parametersToStatement(parameter, preparedStatement);
		newKey = (long) preparedStatement.executeUpdate();

		ResultSet rs = preparedStatement.getGeneratedKeys();
		if (rs.next()) {
			newKey = rs.getLong(1);
		}
		rs.close();

		return newKey;
	}

	protected String queryField(Connection conn, String sql, Object parameter)
			throws SQLException {
		List<Object> prms = new ArrayList<Object>();
		prms.add(parameter);

		return queryField(conn, sql, prms);
	}

	protected String queryField(Connection conn, String sql,
			List<Object> parameter) throws SQLException {
		String out = null;
		ResultSet rs = queryResultSet(conn, sql, parameter);

		if (rs.next()) {
			out = rs.getString(1);
		}
		rs.close();

		return out;
	}

	protected ResultSet queryResultSet(Connection conn, String sql,
			Object parameter) throws SQLException {
		List<Object> prms = new ArrayList<Object>();
		prms.add(parameter);

		return queryResultSet(conn, sql, prms);
	}

	protected ResultSet queryResultSet(Connection conn, String sql,
			List<Object> parameters) throws SQLException {
		ResultSet out = null;

		preparedStatement = conn.prepareStatement(sql);
		parametersToStatement(parameters, preparedStatement);

		out = preparedStatement.executeQuery();

		return out;
	}

	private void parametersToStatement(List<Object> parameters,
			PreparedStatement preparedStatement) throws SQLException {
		// this.callableStatement = conn.prepareCall(sqlStatement);
		if (parameters != null) {
			// int len = parameters.size();
			int i = 0;
			// Object param = null;
			// for (int i = 0; i < len; i++) {
			// param = parameters[i];
			for (Object param : parameters) {

				if (param instanceof String) {
					preparedStatement.setString(i + 1, (String) param);
				} else if (param instanceof Integer) {
					preparedStatement.setInt(i + 1,
							((Integer) param).intValue());
				} else if (param instanceof Double) {
					preparedStatement.setDouble(i + 1,
							((Double) param).doubleValue());
				} else if (param instanceof Long) {
					preparedStatement
							.setLong(i + 1, ((Long) param).longValue());
				} else if (param instanceof Boolean) {
					preparedStatement.setBoolean(i + 1,
							((Boolean) param).booleanValue());
				} else if (param instanceof java.util.Calendar
						|| param instanceof java.util.GregorianCalendar) {
					java.sql.Timestamp time = new java.sql.Timestamp(
							((java.util.Calendar) param).getTimeInMillis());
					preparedStatement.setTimestamp(i + 1, time);
				} else if (param instanceof java.util.Date) {
					java.sql.Timestamp time = new java.sql.Timestamp(
							((java.util.Date) param).getTime());
					preparedStatement.setTimestamp(i + 1, time);
				} else if (param == null) {
					preparedStatement.setNull(i + 1, java.sql.Types.NULL);
				} else {
					String message = "Object type not cataloged, please insert code in \"AbstractProcess\" for class \""
							+ param.getClass().getName() + "\"";

					throw new RuntimeException(message);
				}

				i++;
			}
		}
	}

	public Connection getConnection(ServletContext context)
			throws ClassNotFoundException, SQLException {
		return getConnection(context, "cosoav");
	}

	public Connection getConnection(ServletContext context, String prefix)
			throws ClassNotFoundException, SQLException {

		String driverName = context.getInitParameter(prefix
				+ ".database.driver");
		String serverName = context.getInitParameter(prefix
				+ ".database.server");
		String database = context.getInitParameter(prefix
				+ ".database.database");
		String username = context.getInitParameter(prefix
				+ ".database.username");
		String password = context.getInitParameter(prefix
				+ ".database.password");

		return getConnection(driverName, serverName, database, password,
				username);
	}

	public Connection getConnection(String driverName, String serverName,
			String database, String password, String username)
			throws ClassNotFoundException, SQLException {
		Connection connection = null;
		Class.forName(driverName);

		String url = "jdbc:mysql://" + serverName + "/" + database;

		connection = DriverManager.getConnection(url, username, password);
		return connection;
	}
}
