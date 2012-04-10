package cl.buildersoft.framework.beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.exception.BSDataBaseException;
import cl.buildersoft.framework.exception.BSProgrammerException;
import cl.buildersoft.framework.type.BSActionType;
import cl.buildersoft.framework.type.BSFieldType;

import com.mysql.jdbc.DatabaseMetaData;

public class BSTableConfig {
	private String database = null;
	private String tableName = null;
	private BSField[] fields = null;
	private Map<String, BSField> fieldsMap = null;
	private String title = null;
	private String uri = null;
	private BSAction[] actions = null;
	private String sortField = null;
	private Map<String, String[]> fkInfo = null;
	private Set<String> tablesCommon = null;
	private String pk = null;
	private String key = null;

	public BSTableConfig(String database, String tableName) {
		this.database = database;
		this.fields = new BSField[0];
		this.fieldsMap = new HashMap<String, BSField>();
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
		configBasic(conn, mysql);
		configFKFields(conn, mysql);
	}

	private void configBasic(Connection conn, BSmySQL mysql) {
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
				addField(field);
				configField(conn, metaData, name, i, field);
				if (field.isPK() && !hasPK) {
					hasPK = Boolean.TRUE;
				}

			}

			if (!hasPK) {
				throw new BSProgrammerException("0104");
			}
		} else {
			Integer i = 1;
			for (BSField field : fields) {
				name = field.getName();

				configField(conn, metaData, name, i, field);

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
		/**
		 * <code>
			IMPORTED KEYS:
			--------------------
			PKTABLE_CAT=bscommon
			PKTABLE_SCHEM=null
			PKTABLE_NAME=tboard
			PKCOLUMN_NAME=cId
			FKTABLE_CAT=remu
			FKTABLE_SCHEM=null
			FKTABLE_NAME=tPerson
			FKCOLUMN_NAME=cSexo
			KEY_SEQ=1
			UPDATE_RULE=3
			DELETE_RULE=3
			FK_NAME=withSexo
			PK_NAME=null
			--------------------
			PKTABLE_CAT=bscommon
			PKTABLE_SCHEM=null
			PKTABLE_NAME=tcomuna
			PKCOLUMN_NAME=cId
			FKTABLE_CAT=remu
			FKTABLE_SCHEM=null
			FKTABLE_NAME=tPerson
			FKCOLUMN_NAME=cComuna
			KEY_SEQ=1
			UPDATE_RULE=3
			DELETE_RULE=3
			FK_NAME=withComuna
			PK_NAME=null
		</code>
		 */

		BSField[] fields = getFields();

		String databaseFK = null;
		String tableFK = null;
		String fieldFK = null;

		for (BSField field : fields) {
			String[] pkTableInfo = getFKTableInfo(conn, field);

			if (pkTableInfo != null) {
				field.setFK(pkTableInfo[0], pkTableInfo[1], pkTableInfo[2]);

				databaseFK = field.getFKDatabase();
				tableFK = field.getFKTable();
				fieldFK = field.getFKField();

				if (tableFK != null && fieldFK != null) {
					String sql = "SELECT cId,cName ";
					sql += "FROM " + databaseFK + ".";
					sql += field2Table(conn, field.getName());
					sql += " ORDER BY cName";

					field.setFkData(mySQL.resultSet2Matrix(mySQL
							.queryResultSet(conn, sql, null)));
				}
			}
		}
	}

	private String field2Table(Connection conn, String fieldName) {
		String out = null;
		String table = "t" + fieldName.substring(1);
		String view = "";
		Boolean isExist = isExistInCommon(conn, table);
		if (!isExist) {
			view = "v" + fieldName.substring(1);
			isExist = isExistInCommon(conn, view);
			if (isExist) {
				out = view;
			}
		} else {
			out = table;
		}
		if (!isExist) {
			throw new BSProgrammerException("", "No existe la tabla '" + table
					+ "' o vista '" + view + "'");
		}

		return out;
	}

	private String[] getFKTableInfo(Connection conn, BSField field) {
		/**
		 * <code>
		 * PKTABLE_CAT=bscommon 
		 * PKTABLE_SCHEM=null 
		 * PKTABLE_NAME=tboard
		 * PKCOLUMN_NAME=cId
		 * </code>
		 */
		DatabaseMetaData dbmd;
		ResultSet rs;
		String[] out = null;

		if (this.fkInfo == null) {
			this.fkInfo = new HashMap<String, String[]>();
			try {
				dbmd = (DatabaseMetaData) conn.getMetaData();
				rs = dbmd.getImportedKeys(null, null, getTableName());

				while (rs.next()) {
					String code = rs.getString("FKCOLUMN_NAME");
					String database = rs.getString("PKTABLE_CAT");
					String table = rs.getString("PKTABLE_NAME");
					String column = rs.getString("PKCOLUMN_NAME");

					String[] info = new String[3];
					info[0] = database;
					info[1] = table;
					info[2] = column;

					this.fkInfo.put(code, info);
				}

			} catch (SQLException e) {
				throw new BSDataBaseException("", e.getMessage());
			}
		}

		out = this.fkInfo.get(field.getName());

		return out;
	}

	private Boolean isExistInCommon(Connection conn, String table) {
		// Boolean out = Boolean.FALSE;
		if (this.tablesCommon == null) {
			this.tablesCommon = new HashSet<String>();
			try {
				DatabaseMetaData dbmd = (DatabaseMetaData) conn.getMetaData();
				ResultSet tables = dbmd.getTables("bscommon", null, null, null);

				while (tables.next()) {
					// ResultSetMetaData md = tables.getMetaData();
					this.tablesCommon.add(tables.getString("TABLE_NAME")
							.toLowerCase());
				}
				tables.close();
			} catch (SQLException e) {
				throw new BSDataBaseException("", e.getMessage());
			}
		}

		return this.tablesCommon.contains(table.toLowerCase());
	}

	private void configField(Connection conn, ResultSetMetaData metaData,
			String name, Integer i, BSField field) {
		try {
			if (field.getType() == null) {
				setRealType(metaData, i, field);
			}
			if (field.isPK() == null) {
				BSField pk = getPKField(conn);
				field.setPK(pk.getName().equals(name));
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
		this.fieldsMap.put(field.getName(), field);
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

	public BSField getKeyField(Connection conn) {
		String pk = getPKField(conn).getName();

		String fieldName = null;
		if (this.key == null) {
			DatabaseMetaData dbmd;
			try {
				dbmd = (DatabaseMetaData) conn.getMetaData();

				ResultSet rs = dbmd.getIndexInfo(getDatabase(), null,
						getTableName(), true, false);
				while (rs.next()) {
					fieldName = rs.getString("COLUMN_NAME");
					if (!pk.equals(fieldName)) {
						break;
					}
				}
				rs.close();
			} catch (SQLException e) {
				throw new BSDataBaseException("", e.getMessage());
			}
			this.key = fieldName;
		}
		return getField(this.key);
	}

	public BSField getPKField(Connection conn) {
		String fieldName = null;
		if (this.pk == null) {
			DatabaseMetaData dbmd;
			try {
				dbmd = (DatabaseMetaData) conn.getMetaData();

				ResultSet rs = dbmd.getPrimaryKeys(getDatabase(), null,
						getTableName());
				while (rs.next()) {
					fieldName = rs.getString("COLUMN_NAME");
				}
				rs.close();
			} catch (SQLException e) {
				throw new BSDataBaseException("", e.getMessage());
			}
			this.pk = fieldName;
		}
		return getField(this.pk);
	}

	private BSField getField(String fieldName) {
		return this.fieldsMap.get(fieldName);
	}

	@Deprecated
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

	@Deprecated
	public BSField[] deleteId() {
		BSField[] out = new BSField[this.fields.length - 1];
		int i = 0;
		for (BSField f : fields) {
			if (!f.isId()) {
				out[i++] = f;
			}
		}
		return out;
	}

	public Map<String, BSField> deleteIdMap() {
		BSField[] out = this.deleteId();
		Map<String, BSField> mapField = new HashMap<String, BSField>();
		for (BSField s : out) {
			mapField.put(s.getName(), s);
		}
		return mapField;
	}
}
