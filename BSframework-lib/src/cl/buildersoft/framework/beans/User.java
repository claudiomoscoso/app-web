package cl.buildersoft.framework.beans;


public class User extends BSBean {
	private String mail = null;
	private String name = null;
	private String password = null;
	private String TABLE = "tUser";

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
