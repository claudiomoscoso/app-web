package cl.buildersoft.framework.database;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cl.buildersoft.framework.beans.BSBean;
import cl.buildersoft.framework.util.BSDataUtils;

public class BSBeanUtils extends BSDataUtils {

	public Long insert(Connection conn, BSBean bean) {
		Class<? extends BSBean> theClass = bean.getClass();

		String[] tableFields = getTableFields(theClass);
		String[] objectFields = getObjectFields(theClass);

		tableFields = deleteId(tableFields);
		objectFields = deleteId(objectFields);

		String sql = buildInsertSQLString(theClass, tableFields, bean);
		Object[] params = getValues4Insert(theClass, objectFields, bean);

		Long newId;
		newId = insert(conn, sql, array2List(params));

		String idField = getIdField(getObjectFields(theClass));
		fillField(theClass, idField, newId, bean);

		return newId;
	}

	public Integer update(Connection conn, BSBean bean) {
		Class<? extends BSBean> theClass = bean.getClass();
		String[] tableFields = getTableFields(theClass);
		String[] objectFields = getObjectFields(theClass);

		String sql = buildUpdateSQLString(theClass, tableFields, bean);

		Object[] params = getValues4Update(theClass, objectFields, bean);

		Integer out;
		out = update(conn, sql, array2List(params));

		return out;

	}

	public void save(Connection conn, BSBean bean) {
		try {
			if (update(conn, bean) == 0) {
				insert(conn, bean);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void delete(Connection conn, BSBean bean) {
		Class<? extends BSBean> c = bean.getClass();

		String[] objectFields = getObjectFields(c);
		Integer idValue = (Integer) getMethodValue(c, "get"
				+ getIdField(objectFields), bean);

		String[] tableFields = getTableFields(c);
		String sql = buildDeleteSQLString(c, tableFields, bean);

		update(conn, sql, array2List(idValue));

		Object[] prms = new Object[objectFields.length];

		fillObject(c, objectFields, prms, bean);
	}

	public Boolean search(Connection conn, BSBean bean) {
		Class<? extends BSBean> theClass = bean.getClass();
		Boolean out = Boolean.FALSE;

		String[] tableFields = getTableFields(theClass);
		String[] objectFields = getObjectFields(theClass);
		String[] tableFieldsWithOutId = deleteId(tableFields);
		String tableName = getTableName(theClass, bean);

		String sql = buildSelectSQLString(tableName, tableFields,
				tableFieldsWithOutId);

		try {
			Long idValue = getIdValue(theClass, "get"
					+ getIdField(objectFields), bean);
			ResultSet rs;
			rs = queryResultSet(conn, sql, idValue);
			Object value = null;
			if (rs.next()) {

				for (String f : tableFieldsWithOutId) {
					value = rs.getObject(f);
					fillField(theClass, f.substring(1, f.length()), value, bean);
				}
				out = Boolean.TRUE;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return out;
	}

	private void fillObject(Class<? extends BSBean> c, String[] objectFields,
			Object[] params, BSBean bean) {

		Class objectClass = null;
		String fieldName = null;
		Object value = null;
		for (int i = 0; i < objectFields.length; i++) {
			objectClass = params[i] == null ? null : params[i].getClass();
			fieldName = objectFields[i];
			value = params[i];

			fillField(c, fieldName, value, bean);
		}
	}

	private void fillField(Class<? extends BSBean> c, String fieldName,
			Object value, BSBean bean) {
		/**
		 * http://www.roseindia.net/jdbc/jdbc-mysql/mapping-mysql-data-types-in-java.shtml
		 * */
		Class<?> type = getTypeMethod(c, fieldName);
		Class[] paramTypes = new Class[] { type };
		Method method;
		try {
			method = c.getMethod("set" + fieldName, paramTypes);
			method.invoke(bean, value);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	private Class<?> getTypeMethod(Class<? extends BSBean> c, String methodName) {
		Method m;
		try {
			m = c.getMethod("get" + methodName, null);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
		Type type = m.getGenericReturnType();

		Class<?> out = null;

		if (type.toString().indexOf("String") > -1) {
			out = "".getClass();
		} else if (type.toString().indexOf("Integer") > -1) {
			out = Integer.class;
		} else if (type.toString().indexOf("Double") > -1) {
			out = Double.class;
		} else if (type.toString().indexOf("Long") > -1) {
			out = Long.class;
		} else if (type.toString().indexOf("Boolean") > -1) {
			out = Boolean.class;
		} else if (type.toString().indexOf("Calendar") > -1) {
			out = Calendar.class;
		} else if (type.toString().equals("java.util.Date")) {
			out = Date.class;
		} else {
			throw new RuntimeException("Type mismatch");
		}

		return out;
	}

	private String buildInsertSQLString(Class<? extends BSBean> c,
			String[] tableFields, BSBean bean) {
		String sql = "INSERT INTO " + getTableName(c, bean) + "(";

		sql += unSplit(tableFields, ",") + ") VALUES(";
		sql += getCommas(tableFields) + ");";
		return sql;
	}

	private String buildUpdateSQLString(Class<? extends BSBean> c,
			String[] tableFields, BSBean bean) {

		String id = getIdField(tableFields);
		tableFields = deleteId(tableFields);

		String sql = "UPDATE " + getTableName(c, bean) + " SET ";
		sql += unSplit(tableFields, "=?,");
		sql += " WHERE " + id + "=?";
		return sql;
	}

	private String buildDeleteSQLString(Class<? extends BSBean> c,
			String[] tableFields, BSBean bean) {

		String sql = "DELETE FROM " + getTableName(c, bean) + " WHERE "
				+ getIdField(tableFields) + "=?";

		return sql;
	}

	private String buildSelectSQLString(String tableName, String[] tableFields,
			String[] tableFieldsWithOutId) {

		String sql = "SELECT " + unSplit(tableFieldsWithOutId, ",") + " FROM "
				+ tableName + " WHERE " + getIdField(tableFields) + "=?";

		return sql;
	}

	private String getIdField(String[] tableFields) {
		String out = "";
		for (String s : tableFields) {
			if (isId(s)) {
				out = s;
				break;
			}
		}
		return out;
	}

	private Long getIdValue(Class<? extends BSBean> c, String idFieldName,
			BSBean bean) {
		return (Long) getMethodValue(c, idFieldName, bean);

	}

	private Object[] getValues4Insert(Class<? extends BSBean> c,
			String[] tableFields, BSBean bean) {

		Object[] out = new Object[tableFields.length];
		Method method = null;
		int i = 0;
		for (String name : tableFields) {
			name = "get" + name;
			try {
				method = c.getMethod(name, null);
				out[i++] = method.invoke(bean, null);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}
		return out;
	}

	private Object[] getValues4Update(Class<? extends BSBean> c,
			String[] objectFields, BSBean bean) {

		Object[] out = new Object[objectFields.length];
		Object aux = null;
		Object value = null;
		String methodName = "";
		// Method method = null;
		int i = 0;
		for (String name : objectFields) {
			methodName = "get" + name;

			value = getMethodValue(c, methodName, bean);
			if (isId(name)) {
				aux = value;
			} else {
				out[i++] = value;
			}
		}
		out[i] = aux;

		return out;
	}

	private Object getMethodValue(Class<? extends BSBean> c, String methodName,
			BSBean bean) {
		Object value;
		Method method;
		try {
			method = c.getMethod(methodName, null);
			value = method.invoke(bean, null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return value;
	}

	private String getCommas(String[] tableFields) {
		String out = "";
		for (int i = 0; i < tableFields.length; i++) {
			out += "?,";
		}
		out = out.substring(0, out.length() - 1);
		return out;
	}

	private String[] deleteId(String[] tableFields) {
		String[] out = new String[tableFields.length - 1];
		int i = 0;
		for (String s : tableFields) {
			if (!isId(s)) {
				out[i++] = s;
			}
		}
		return out;
	}

	private boolean isId(String s) {
		return "id".equalsIgnoreCase(s) || "cid".equalsIgnoreCase(s);
	}

	private String unSplit(String[] tableFields, String s) {
		String out = "";
		for (String f : tableFields) {
			out += f + s;
		}
		out = out.substring(0, out.length() - 1);
		return out;
	}

	private String[] getTableFields(Class<? extends BSBean> theClass) {
		return getFields(theClass, true);
	}

	private String[] getObjectFields(Class<? extends BSBean> c) {
		return getFields(c, false);
	}

	private String[] getFields(Class<? extends BSBean> c, boolean asFields) {
		String[] out = null;
		List<String> list = new ArrayList<String>();

		String pre = asFields ? "c" : "";

		String name = null;
		Method[] methods = getMethods(c);
		for (Method method : methods) {

			name = method.getName();
			if (name.startsWith("get")) {
				name = name.substring(3);
				list.add(pre + name);
			}

		}

		out = new String[list.size()];
		int i = 0;
		for (String o : list) {
			out[i++] = o;

		}

		return out;
	}

	private Method[] getMethods(Class<?> c) {
		List<Method> out = new ArrayList<Method>();

		out = array2List(c.getDeclaredMethods());

		if (!c.equals(Object.class)) {

			List<Method> parentMethods = array2List(c.getSuperclass()
					.getDeclaredMethods());

			for (Method o : parentMethods) {
				out.add(o);
			}

		}

		return list2Array(out);
	}

	protected List<Method> array2List(Method... prms) {
		List<Method> out = new ArrayList<Method>();

		for (Method o : prms) {
			out.add(o);
		}

		return out;
	}

	protected Method[] list2Array(List<Method> prms) {
		Method[] out = new Method[prms.size()];

		int i = 0;
		for (Method o : prms) {
			out[i++] = o;
		}

		return out;
	}

	public Method getMethod(Class<?> clazz, String name) {
		Method m;
		try {
			m = clazz.getDeclaredMethod(name);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		if (m == null) {
			if (clazz.equals(Object.class)) {
				throw new RuntimeException(
						"Method not found in any super class.");
			}
			return getMethod(clazz.getSuperclass(), name);
		}
		return m;
	}

	private String getTableName(Class<? extends BSBean> c, BSBean instance) {
		String out = null;
		Field privateStringField = null;
		try {
			privateStringField = c.getDeclaredField("TABLE");
			privateStringField.setAccessible(true);
			out = (String) privateStringField.get(instance);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return out;
	}

}
