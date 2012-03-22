package cl.buildersoft.framework.beans;


public class Rol extends BSBean {
	private String TABLE = "tRol";

	private String name = null;
	private Boolean deleted = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

}
