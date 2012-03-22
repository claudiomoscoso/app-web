package cl.buildersoft.framework.beans;

public class RUserRol extends BSBean{
	private String TABLE = "tR_UserRol";
	private Long user = null;
	private Long rol = null;

	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}

	public Long getRol() {
		return rol;
	}

	public void setRol(Long rol) {
		this.rol = rol;
	}
}
