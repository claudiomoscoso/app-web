package cl.buildersoft.framework.beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.exception.BSDataBaseException;
import cl.buildersoft.framework.exception.BSProgrammerException;
import cl.buildersoft.framework.type.BSActionType;
import cl.buildersoft.framework.type.BSFieldType;

public class BSTableConfig {
	private String database = null;
	private String tableName = null;
	private BSField[] fields = null;
	private String title = null;
	private String uri = null;
	private BSAction[] actions = null;
	private String sortField = null;

	public BSTableConfig(String database, String tableName) {
		this.database = database;
		this.fields = new BSField[0];
		this.actions = new BSAction[0];
		this.tableName = tableName;
		this.title = tableName;

		createInsert();
		createEdit();
		createDelete();

	}

	public String getDatabase() {
		return this.database;
	}

	public BSField[] getFKFields() {
		List<BSField> fkFields = new ArrayList<BSField>();
		for (BSField s : fields) {
			if (s.isFK()) {
				fkFields.add(s);
			}
		}
		BSField[] out = (BSField[]) fkFields.toArray();
		return out;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public void setSortField(BSField field) {
		setSortField(field.getName());
	}

	private void createInsert() {
		BSAction insert = new BSAction("INSERT", BSActionType.Table);
		insert.setLabel("Nuevo");
		insert.setUrl("/servlet/table/NewRecord");
		this.addAction(insert);
	}

	private void createEdit() {
		BSAction edit = new BSAction("EDIT", BSActionType.Record);
		edit.setLabel("Modificar");
		edit.setUrl("/servlet/table/SearchRecord");
		this.addAction(edit);
	}

	private void createDelete() {
		BSAction delete = new BSAction("DELETE", BSActionType.MultiRecord);
		delete.setLabel("Borrar");
		delete.setUrl("/servlet/table/DeleteRecords");
		this.addAction(delete);
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

	public void configFields(Connection conn, BSmySQL mysql) {
		try {
			configBasic(conn, mysql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		configFKFields(conn, mysql);
	}

	private void configBasic(Connection conn, BSmySQL mysql) throws SQLException {
		String sql = getSQL();
		ResultSet resultSet = mysql.queryResultSet(conn, sql, null);
		BSField[] fields = getFields();

		ResultSetMetaData metaData;
		try {
			metaData = resultSet.getMetaData();
		} catch (SQLException e) {
			throw new BSDataBaseException("300", e.getMessage());
		}

		String name = null;

		if (fields.length == 0) {
			Integer n;
			try {
				n = metaData.getColumnCount();
			} catch (SQLException e) {
				throw new BSDataBaseException("300", e.getMessage());
			}

			BSField field = null;
			Boolean hasPK = Boolean.FALSE;
			n++;
			for (Integer i = 1; i < n; i++) {
				try {
					name = metaData.getColumnName(i);
				} catch (SQLException e) {
					throw new BSDataBaseException("300", e.getMessage());
				}
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

	private String getSQL() {
		String out = "SELECT ";
		if (getFields().length == 0) {
			out += "*";
		} else {
			out += unSplitFieldNames(",");
		}

		out += " FROM " + getDatabase() + "." + getTableName();
		out += " LIMIT 0,1";
		return out;
	}

	public String unSplitFieldNames(String s) {
		return unSplitFieldNames(getFields(), s);
	}

	public String unSplitFieldNames(BSField[] fields, String s) {
		String out = "";
		for (BSField f : fields) {
			out += f.getName() + s;
		}
		out = out.substring(0, out.length() - 1);
		return out;
	}

	private String unSplitActionCodes(String s) {
		String out = "";
		for (BSAction f : getActions()) {
			out += f.getCode() + s;
		}
		out = out.substring(0, out.length() - 1);
		return out;
	}

	private void configFKFields(Connection conn, BSmySQL mySQL) {
		BSField[] fields = getFields();

		String databaseFK = null;
		String tableFK = null;
		String fieldFK = null;
	
		for (BSField field : fields) {			
			databaseFK = field.getFKDatabase();
			tableFK = field.getFKTable();
			fieldFK = field.getFKField();

			if (tableFK != null && fieldFK != null) {
				String sql = "SELECT cId," + fieldFK;
				sql += " FROM " + databaseFK + "." + tableFK + " ORDER BY "
						+ fieldFK;

				field.setFkData(mySQL.resultSet2Matrix(mySQL.queryResultSet(
						conn, sql, null)));
			}
		}
	}

	private void configField(ResultSetMetaData metaData, String name,
			Integer i, BSField field) {

		try {
			if (field.getType() == null) {
				setRealType(metaData, i, field);
			}
			if (field.isPk() == null) {
				field.setPk(metaData.isAutoIncrement(i));
			}
			if (field.getLength() == null) {
				field.setLength(metaData.getColumnDisplaySize(i));
			}
		} catch (SQLException e) {
			throw new BSDataBaseException("300", e.getMessage());
		}
	}

	private void setRealType(ResultSetMetaData metaData, Integer i,
			BSField field) {
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
		String typeName;
		try {
			typeName = metaData.getColumnTypeName(i);
		} catch (SQLException e) {
			throw new BSDataBaseException("300", e.getMessage());
		}

		if (typeName.equals("BIGINT")) {
			field.setType(BSFieldType.Long);
		} else if (typeName.equals("VARCHAR") || typeName.equals("CHAR")) {
			field.setType(BSFieldType.String);
		} else if (typeName.equals("DATE")) {
			field.setType(BSFieldType.Date);
		} else if (typeName.equals("TIMESTAMP")) {
			field.setType(BSFieldType.Timestamp);
		} else if (typeName.equals("DOUBLE")) {
			field.setType(BSFieldType.Double);
		} else if (typeName.equals("BIT")) {
			field.setType(BSFieldType.Boolean);
		} else if (typeName.equals("INT")) {
			field.setType(BSFieldType.Integer);
		} else {
			throw new BSProgrammerException("0110",
					"No está catalogado el tipo " + typeName
							+ ", verifique método BSTableConfig.setRealType()");
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

	/**
	 * public String [] getActionCodes() { BSAction [] actions = getActions();
	 * String [] names = new String [actions.length]; Integer i = 0;
	 * for(BSAction action: actions){ names[i++]=action.getCode(); }
	 * 
	 * return names; }
	 */
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

	public void renameAction(String source, String target) {
		BSAction action = getAction(source);
		if (action == null) {
			throw new BSProgrammerException("0105",
					"Accion no encontrada, posibles: ["
							+ unSplitActionCodes(",") + "]");
		}
		action.setCode(target);
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

	public BSField getIdField() {
		BSField[] fields = getFields();
		BSField out = null;

		if (fields.length == 0) {
			throw new BSProgrammerException("",
					"No se ha definido BSFields[] para la tabla "
							+ getTableName());
		} else {
			for (BSField s : fields) {
				if (s.isId()) {
					out = s;
					break;
				}
			}
		}
		return out;
	}

	public BSField[] deleteId() {
		BSField[] out = new BSField[this.fields.length - 1];
		int i = 0;
		for (BSField s : fields) {
			if (!s.isId()) {
				out[i++] = s;
			}
		}
		return out;
	}
}
