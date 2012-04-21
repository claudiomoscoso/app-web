package cl.buildersoft.framework.beans;

import java.io.Serializable;

public class Domain extends BSBean implements Serializable {
	private static final long serialVersionUID = 372116778968735407L;
	private String name = null;
	private String alias = null;
	private String TABLE = "tDomain";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

}
