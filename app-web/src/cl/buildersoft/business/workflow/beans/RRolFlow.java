package cl.buildersoft.business.workflow.beans;

import cl.buildersoft.framework.beans.BSBean;

public class RRolFlow extends BSBean {
	private static final long serialVersionUID = 277122279020349536L;
	private String TABLE = "tR_RolFlow";
	private Long rol = null;
	private Long flow = null;

	public Long getRol() {
		return rol;
	}

	public void setRol(Long rol) {
		this.rol = rol;
	}

	public Long getFlow() {
		return flow;
	}

	public void setFlow(Long flow) {
		this.flow = flow;
	}
}
