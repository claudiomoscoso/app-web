package cl.buildersoft.business.workflow.beans;

import cl.buildersoft.lib.beans.BSBean;

public class Instance extends BSBean {
	private static final long serialVersionUID = 5970049877943399494L;
	private String TABLE = "tInstance";
	private Long id = null;
	private Long flow = null;
	private Long creator = null;
	private Long assigned = null;
	private Long step = null;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getFlow() {
		return flow;
	}
	public void setFlow(Long flow) {
		this.flow = flow;
	}
	public Long getCreator() {
		return creator;
	}
	public void setCreator(Long creator) {
		this.creator = creator;
	}
	public Long getAssigned() {
		return assigned;
	}
	public void setAssigned(Long assigned) {
		this.assigned = assigned;
	}
	public Long getStep() {
		return step;
	}
	public void setStep(Long step) {
		this.step = step;
	}

}
