package cl.buildersoft.business.workflow.beans;

import cl.buildersoft.framework.beans.BSBean;

public class Flow extends BSBean {
	private static final long serialVersionUID = -2550466801996973243L;
	private String TABLE = "tFlow";
	private String key = null;
	private String name = null;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
