package cl.buildersoft.framework.beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.exception.BSProgrammerException;
import cl.buildersoft.framework.type.BSActionType;
import cl.buildersoft.framework.type.BSFieldType;

public class BSTableConfig {
	private String tableName = null;
	private BSField[] fields = null;
	private String title = null;
	private String uri = null;
	private BSAction[] actions = null;
	private String sortField = null;

	public BSTableConfig(String tableName) {
		this.fields = new BSField[0];
		this.actions = new BSAction[0];
		this.tableName = tableName;
		this.title = tableName;

		createInsert();
		createEdit();
		createDelete();

	}

	public String getSortField(String sortField) {
		return sortField;
	}

	public void sortField(String sortField) {
		this.sortField = sortField;
	}

	public void sortField(BSField field) {
		sortField(field.getName());
	}

	private void createInsert() {
		BSAction insert = new BSAction("INSERT", BSActionType.Table);
		insert.setLabel("Agregar");
		insert.setUrl("/servlet/table/NewRecord");
		this.addAction(insert);
	}

	private void createEdit() {
		BSAction edit = new BSAction("EDIT", BSActionType.Record);
		edit.setLabel("Modificar");

		edit.setUrl("/servlet/table/SearchRecord");
		// edit.setUrl("/servlet/ShowParameters");
		this.addAction(edit);
	}

	private void createDelete() {
		BSAction delete = new BSAction("DELETE", BSActionType.MultiRecord);
		delete.setLabel("Borrar");
		delete.setUrl("/servlet/table/DeleteRecords");
		this.addAction(delete);
	}

	public String getSQL4SelectAll() {
		BSField[] fields = getFields();
		String sql = "SELECT " + getFieldsNames(fields);
		sql += " FROM " + getTableName();
		sql += this.sortField != null ? " ORDER BY " + this.sortField : "";
		return sql;
	}

	public String getFieldsNames(BSField[] fields) {
		String out = "";
		if (fields.length == 0) {
			out = "*";
		} else {
			for (BSField field : fields) {
				out += field.getName() + ",";
			}
			out = out.substring(0, out.length() - 1);
		}
		return out;
	}

	public void configFields(ResultSet resultSet) throws SQLException {
		BSField[] fields = getFields();
		ResultSetMetaData metaData = resultSet.getMetaData();
		String name = null;

		if (fields.length == 0) {
			Integer n = metaData.getColumnCount();

			BSField field = null;
			Boolean hasPK = Boolean.FALSE;

			for (Integer i = 1; i < n; i++) {
				name = metaData.getColumnName(i);
				field = new BSField(name, name);
				configField(metaData, name, i, field);
				if (field.isPk() && !hasPK) {
					hasPK = Boolean.TRUE;
				}
				addField(field);
			}

			if (!hasPK) {
				throw new BSProgrammerException("0104");
			}
		} else {
			Integer i = 1;
			for (BSField field : fields) {
				name = field.getName();

				configField(metaData, name, i, field);

				i++;
			}

		}
	}

	public void configFKFields(Connection conn, BSmySQL mySQL) {
		BSField[] fields = getFields();

		String tableFK = null;
		String fieldFK = null;

		for (BSField field : fields) {
			tableFK = field.getFKTable();
			fieldFK = field.getFKField();
			if (tableFK != null && fieldFK != null) {
				String sql = "SELECT cId," + fieldFK;
				sql += " FROM " + tableFK + " ORDER BY " + fieldFK;

				field.setFkData(mySQL.resultSet2Matrix(mySQL.queryResultSet(
						conn, sql, null)));
			}
		}
	}

	private void configField(ResultSetMetaData metaData, String name,
			Integer i, BSField field) throws SQLException {

		if (field.getType() == null) {
			setRealType(metaData, i, field);
		}
		if (field.isPk() == null) {
			field.setPk(metaData.isAutoIncrement(i));
		}
		if (field.getLength() == null) {
			field.setLength(metaData.getColumnDisplaySize(i));
		}
	}

	private void setRealType(ResultSetMetaData metaData, Integer i,
			BSField field) throws SQLException {
		/**
		 * <code>
		System.out.println( name + " "+ metaData.getColumnTypeName(i));
		cId BIGINT
		cName VARCHAR
		cBorn DATE
		cLastLogin TIMESTAMP
		cSalary DOUBLE
		</code>
		 */
		String typeName = metaData.getColumnTypeName(i);

		if (typeName.equals("BIGINT")) {
			field.setType(BSFieldType.Long);
		} else if (typeName.equals("VARCHAR")) {
			field.setType(BSFieldType.String);
		} else if (typeName.equals("DATE")) {
			field.setType(BSFieldType.Date);
		} else if (typeName.equals("TIMESTAMP")) {
			field.setType(BSFieldType.Datetime);
		} else if (typeName.equals("DOUBLE")) {
			field.setType(BSFieldType.Double);
		} else if (typeName.equals("BIT")) {
			field.setType(BSFieldType.Boolean);
		} else if (typeName.equals("INT")) {
			field.setType(BSFieldType.Integer);
		} else {
			throw new BSProgrammerException("0110",
					"No está catalogado el tipo " + typeName);
		}
	}

	public String getTableName() {
		return this.tableName;
	}

	public BSField[] getFields() {
		return fields;
	}

	public BSAction[] getActions() {
		return this.actions;
	}

	public BSAction[] getActions(BSActionType actionType) {
		BSAction[] out = new BSAction[0];
		for (BSAction action : this.actions) {
			if (action.getActionType().equals(actionType)) {
				BSAction[] aux = new BSAction[out.length + 1];
				System.arraycopy(out, 0, aux, 0, out.length);
				aux[aux.length - 1] = action;
				out = aux;
			}
		}
		return out;
	}

	public BSAction getAction(String code) {
		BSAction out = null;
		for (BSAction action : this.actions) {
			if (action.getCode().equals(code)) {
				out = action;
				break;
			}
		}
		return out;
	}

	public void addField(BSField field) {
		BSField[] target = new BSField[this.fields.length + 1];
		System.arraycopy(this.fields, 0, target, 0, this.fields.length);
		target[target.length - 1] = field;
		this.fields = target;
	}

	public void addAction(BSAction action) {
		BSAction[] target = new BSAction[this.actions.length + 1];
		System.arraycopy(this.actions, 0, target, 0, this.actions.length);
		target[target.length - 1] = action;
		this.actions = target;
	}

	public void removeAction(String code) {
		BSAction[] target = new BSAction[this.actions.length - 1];
		Integer i = 0;
		for (BSAction action : this.actions) {
			if (!action.getCode().equals(code)) {
				target[i++] = action;
			}
		}
		this.actions = target;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

}
