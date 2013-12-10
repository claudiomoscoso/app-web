package cl.buildersoft.business.beans;

import cl.buildersoft.framework.beans.BSBean;

public class File extends BSBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8534647655605197606L;
	private String TABLE="tFile";
	private String employee = null;
	private String desc = null;
	private String filename = null;
	private String filerealname = null;
	private String size = null;
	private String filecategory = null;
	private String datetime = null;
	private String contenttype = null;
	
	public String getEmployee() {
		return employee;
	}
	public void setEmployee(String employee) {
		this.employee = employee;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilerealname() {
		return filerealname;
	}
	public void setFilerealname(String filerealname) {
		this.filerealname = filerealname;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getFilecategory() {
		return filecategory;
	}
	public void setFilecategory(String filecategory) {
		this.filecategory = filecategory;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getContenttype() {
		return contenttype;
	}
	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
	}	

}
