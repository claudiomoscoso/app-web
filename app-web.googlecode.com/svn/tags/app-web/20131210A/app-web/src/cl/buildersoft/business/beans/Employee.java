package cl.buildersoft.business.beans;

import java.util.Date;

import cl.buildersoft.framework.beans.BSBean;

public class Employee extends BSBean {
	private static final long serialVersionUID = 5015716040873333572L;
	private String TABLE = "tEmployee";
	private String rut = null;
	private String name = null;
	private String lastName1 = null;
	private String lastName2 = null;
	private Date birthDate = null;
	private String address = null;
	private Long genere = null;
	private Long comuna = null;
	private Long country = null;
	private String phone = null;
	private Long maritalStatus = null;
	private String movil = null;
	private String email = null;
	
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName1() {
		return lastName1;
	}
	public void setLastName1(String lastName1) {
		this.lastName1 = lastName1;
	}
	public String getLastName2() {
		return lastName2;
	}
	public void setLastName2(String lastName2) {
		this.lastName2 = lastName2;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getGenere() {
		return genere;
	}
	public void setGenere(Long genere) {
		this.genere = genere;
	}
	public Long getComuna() {
		return comuna;
	}
	public void setComuna(Long comuna) {
		this.comuna = comuna;
	}
	public Long getCountry() {
		return country;
	}
	public void setCountry(Long country) {
		this.country = country;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Long getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(Long maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getMovil() {
		return movil;
	}
	public void setMovil(String movil) {
		this.movil = movil;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Employee [rut=" + rut + ", name=" + name + ", lastName1="
				+ lastName1 + ", lastName2=" + lastName2 + ", birthDate="
				+ birthDate + ", address=" + address + ", genere=" + genere
				+ ", comuna=" + comuna + ", country=" + country + ", phone="
				+ phone + ", maritalStatus=" + maritalStatus + ", movil="
				+ movil + ", email=" + email + "]";
	}
}
