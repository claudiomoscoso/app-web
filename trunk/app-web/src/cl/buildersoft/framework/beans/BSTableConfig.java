package cl.buildersoft.framework.beans;

import cl.buildersoft.framework.type.BSActionType;

public class BSTableConfig {
	private String tableName = null;
	private BSField[] fields = null;
	private String title = null;
	private String uri = null;
	private BSAction[] actions = null;

	public BSTableConfig(String tableName) {
		this.fields = new BSField[0];
		this.actions = new BSAction[0];
		this.tableName = tableName;
		this.title = tableName;

		createInsert();
		createEdit();
		createDelete();

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
