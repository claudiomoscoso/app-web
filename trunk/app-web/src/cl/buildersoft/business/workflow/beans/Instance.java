package cl.buildersoft.business.workflow.beans;

import java.util.List;

import cl.buildersoft.framework.beans.BSBean;

public class Instance extends BSBean {
	private static final long serialVersionUID = 5970049877943399494L;
	private Long idFlow = null;
	private Long assigned = null;
	private List<FieldFlow> fields = null;
	public Long getIdFlow() {
		return idFlow;
	}
	public void setIdFlow(Long idFlow) {
		this.idFlow = idFlow;
	}
	public Long getAssigned() {
		return assigned;
	}
	public void setAssigned(Long assigned) {
		this.assigned = assigned;
	}
	public List<FieldFlow> getFields() {
		return fields;
	}
	public void setFields(List<FieldFlow> fields) {
		this.fields = fields;
	}
}
