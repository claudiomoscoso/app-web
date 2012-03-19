package cl.buildersoft.framework.beans;

import cl.buildersoft.framework.type.BSActionType;

public class BSAction {
	private String code = null;
	private String label = null;
	private BSActionType actionType = null;
	private String url = null;
	public BSAction(String code, BSActionType actionType) {
		super();
		this.code = code;
		this.label = code;
		this.actionType = actionType;
	}
	
	public String getCode() {
		return code;
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

}
