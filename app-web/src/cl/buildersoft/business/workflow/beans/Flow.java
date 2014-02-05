package cl.buildersoft.business.workflow.beans;

import java.util.List;

import cl.buildersoft.framework.beans.BSBean;
import cl.buildersoft.framework.beans.Rol;

public class Flow extends BSBean {
	private static final long serialVersionUID = -2550466801996973243L;
	private String TABLE = "tFlow";
	private String key = null;
	private String name = null;
	private List<StepFlow> steps = null;
	private List<FieldFlow> fields = null;
	private List <Rol> allowed = null;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<StepFlow> getSteps() {
		return steps;
	}

	public void setSteps(List<StepFlow> steps) {
		this.steps = steps;
	}

	public List<FieldFlow> getFields() {
		return fields;
	}

	public void setFields(List<FieldFlow> fields) {
		this.fields = fields;
	}

	public List <Rol> getAllowed() {
		return allowed;
	}

	public void setAllowed(List <Rol> allowed) {
		this.allowed = allowed;
	}
}
