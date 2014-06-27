package cl.buildersoft.test.bean;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = -701406790473939613L;
	private String name = null;
	private Integer id = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
