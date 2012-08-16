package cl.buildersoft.framework.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cl.buildersoft.framework.beans.DomainAttribute;
import cl.buildersoft.framework.exception.BSConfigurationException;
import cl.buildersoft.framework.exception.BSDataBaseException;
import cl.buildersoft.framework.exception.BSProgrammerException;

public class BSDataUtils {
	PreparedStatement preparedStatement = null;

	public void closeSQL(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				throw new BSDataBaseException("0300", e.getMessage());
			}
		}
	}

	public void closeSQL() {
		if (this.preparedStatement != null) {
			try {
				this.preparedStatement.close();
			} catch (Exception e) {
				throw new BSDataBaseException("0300", e.getMessage());
			}
		}
	}

	public Integer update(Connection conn, String sql, Object parameter) {
		List<Object> prms = new ArrayList<Object>();
		prms.add(parameter);

		return update(conn, sql, prms);
	}

	public Integer update(Connection conn, String sql, List<Object> parameter) {
		int rowsAffected = 0;
		try {
			preparedStatement = conn.prepareStatement(sql);
			parametersToStatement(parameter, preparedStatement);
			rowsAffected = preparedStatement.executeUpdate();
		} catch (Exception e) {
			throw new BSDataBaseException("0300", e.getMessage());
		}

		return rowsAffected;
	}

	public Long insert(Connection conn, String sql, List<Object> parameter) {
		Long newKey = 0L;

		try {
			preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			parametersToStatement(parameter, preparedStatement);
			newKey = (long) preparedStatement.executeUpdate();

			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				newKey = rs.getLong(1);
			}

			closeSQL(rs);
			// rs.close();
		} catch (Exception e) {
			throw new BSDataBaseException("0300", e.getMessage());
		}

		return newKey;
	}

	public String queryField(Connection conn, String sql, Object oneParam) {
		List<Object> prms = new ArrayList<Object>();
		prms.add(oneParam);
		return queryField(conn, sql, prms);
	}

	public String queryField(Connection conn, String sql, List<Object> parameter) {
		String out = null;
		ResultSet rs = queryResultSet(conn, sql, parameter);
		try {
			if (rs.next()) {
				out = rs.getString(1);
			}
			rs.close();
		} catch (Exception e) {
			throw new BSDataBaseException("0300", e.getMessage());
		}
		return out;
	}

	public ResultSet queryResultSet(Connection conn, String sql, Object parameter) {
		List<Object> prms = new ArrayList<Object>();
		prms.add(parameter);

		return queryResultSet(conn, sql, prms);
	}

	public ResultSet queryResultSet(Connection conn, String sql, List<Object> parameters) {
		ResultSet out = null;

		try {
			preparedStatement = conn.prepareStatement(sql);
			parametersToStatement(parameters, preparedStatement);
			out = preparedStatement.executeQuery();
		} catch (Exception e) {
			throw new BSDataBaseException("0300", e.getMessage());
		}

		return out;
	}

	protected void parametersToStatement(List<Object> parameters, PreparedStatement preparedStatement) {
		parametersToStatement(parameters, preparedStatement, 0);

	}

	protected void parametersToStatement(List<Object> parameters, PreparedStatement preparedStatement, Integer initIndex) {
		// this.callableStatement = conn.prepareCall(sqlStatement);
		try {
			if (parameters != null) {
				// int len = parameters.size();
				// int initIndex = 0;
				// Object param = null;
				// for (int i = 0; i < len; i++) {
				// param = parameters[i];
				for (Object param : parameters) {
					if (param instanceof String) {
						preparedStatement.setString(initIndex + 1, (String) param);
					} else if (param instanceof Integer) {
						preparedStatement.setInt(initIndex + 1, ((Integer) param).intValue());
					} else if (param instanceof Double) {
						preparedStatement.setDouble(initIndex + 1, ((Double) param).doubleValue());
					} else if (param instanceof Long) {
						preparedStatement.setLong(initIndex + 1, ((Long) param).longValue());
					} else if (param instanceof Boolean) {
						preparedStatement.setBoolean(initIndex + 1, ((Boolean) param).booleanValue());
					} else if (param instanceof java.util.Calendar || param instanceof java.util.GregorianCalendar) {
						java.sql.Timestamp time = new java.sql.Timestamp(((java.util.Calendar) param).getTimeInMillis());
						preparedStatement.setTimestamp(initIndex + 1, time);
					} else if (param instanceof java.util.Date) {
						java.sql.Timestamp time = new java.sql.Timestamp(((java.util.Date) param).getTime());
						preparedStatement.setTimestamp(initIndex + 1, time);
					} else if (param == null) {
						preparedStatement.setNull(initIndex + 1, java.sql.Types.NULL);
					} else {
						String message = "Object type not cataloged, please insert code in \"AbstractProcess\" for class \""
								+ param.getClass().getName() + "\"";

						throw new BSProgrammerException("0103", message);
					}

					initIndex++;
				}
			}
		} catch (Exception e) {
			throw new BSDataBaseException("0300", e.getMessage());
		}
	}

	@Deprecated
	public Connection getConnection(ServletContext context) {
		return getConnection(context, "cosoav");
	}

	public Connection getConnection(Map<String, DomainAttribute> domainAttribute) {
		String driverName = domainAttribute.get("database.driver").getValue();
		String serverName = domainAttribute.get("database.server").getValue();
		String database = domainAttribute.get("database.database").getValue();
		String username = domainAttribute.get("database.username").getValue();
		String password = domainAttribute.get("database.password").getValue();

		return getConnection(driverName, serverName, database, password, username);
	}

	public Connection getConnection(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Map<String, DomainAttribute> domainAttribute = null;
		synchronized (session) {
			domainAttribute = (Map<String, DomainAttribute>) session.getAttribute("DomainAttribute");
		}

		return getConnection(domainAttribute);
	}

	public Connection getConnection(ServletContext context, String prefix) {
		String driverName = context.getInitParameter(prefix + ".database.driver");
		String serverName = context.getInitParameter(prefix + ".database.server");
		String database = context.getInitParameter(prefix + ".database.database");
		String username = context.getInitParameter(prefix + ".database.username");
		String password = context.getInitParameter(prefix + ".database.password");
		return getConnection(driverName, serverName, database, password, username);
	}

	public Connection getConnection(String driverName, String serverName, String database, String password, String username) {

		Connection connection = null;
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			throw new BSConfigurationException("0400", e.getMessage());
		}
		String url = "jdbc:mysql://" + serverName + "/" + database;

		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			throw new BSDataBaseException("0300", e.getMessage());
		}

		return connection;
	}

	public List<Object[]> resultSet2Matrix(ResultSet rs) {
		List<Object[]> out = new ArrayList<Object[]>();

		Integer i = 0;
		Integer colCount = 0;
		String[] colNames = null;
		try {
			ResultSetMetaData metaData = rs.getMetaData();
			colCount = metaData.getColumnCount();
			colNames = new String[colCount];
			for (i = 1; i <= colCount; i++) {
				colNames[i - 1] = metaData.getColumnName(i);
			}
		} catch (Exception e) {
			throw new BSDataBaseException("0300", "Error al trabajar con la definicion de una tabla " + e.getMessage());
		}

		Object[] innerArray = null;
		try {
			while (rs.next()) {
				i = 0;
				innerArray = new Object[colCount];
				for (String colName : colNames) {
					innerArray[i] = rs.getObject(colName);
					i++;
				}
				out.add(innerArray);
			}
		} catch (SQLException e) {
			throw new BSDataBaseException("0300", "Error al recorrer un ResultSet " + e.getMessage() + " "
					+ e.getLocalizedMessage());
		}
		this.closeSQL(rs);

		return out;
	}

	public void setAutoCommit(Connection conn, Boolean status) {
		try {
			conn.setAutoCommit(status);
		} catch (SQLException e) {
			throw new BSDataBaseException("0300", e.getMessage());
		}
	}

	public void commit(Connection conn) {
		try {
			conn.commit();
		} catch (SQLException e) {
			throw new BSDataBaseException("0300", e.getMessage());
		}
	}

	public void rollback(Connection conn) {
		try {
			conn.rollback();
		} catch (SQLException e) {
			throw new BSDataBaseException("0300", e.getMessage());
		}
	}
}
