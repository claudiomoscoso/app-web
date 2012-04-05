package cl.buildersoft.framework.beans;

import cl.buildersoft.framework.type.BSActionType;

public class BSAction {
	private String code = null;
	private String defaultCode = null;
	private String label = null;
	private BSActionType actionType = null;
	private String url = null;
	private String[] natTable = null;

	public BSAction(String code, BSActionType actionType) {
		super();
		this.code = code;
		this.defaultCode = code;
		this.label = code;
		this.actionType = actionType;
	}

	public String getCode() {
		return code;
	}

	public String getDefaultCode() {
		return defaultCode;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public BSActionType getActionType() {
		return actionType;
	}

	public void setActionType(BSActionType actionType) {
		this.actionType = actionType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "BSAction [code=" + code + ", label=" + label + ", actionType="
				+ actionType + ", url=" + url + "]";
	}

	public String[] getNatTable() {
		return natTable;
	}

	public void setNatTable(String directTable, String otherTable) {
		this.natTable = new String[2];
		this.natTable[0] = directTable;
		this.natTable[1] = otherTable;
		this.setActionType(BSActionType.Record);
		this.setUrl("/servlet/table/NatTable");
	}

}
