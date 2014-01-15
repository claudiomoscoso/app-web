package cl.buildersoft.business.workflow.beans;

import java.util.List;

import cl.buildersoft.framework.beans.BSBean;

public class Flow extends BSBean {
	private static final long serialVersionUID = -2550466801996973243L;

	private String key = null;
	private String name = null;
	private List<StepFlow> steps = null;
	private List<FieldFlow> fields = null;

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

}
