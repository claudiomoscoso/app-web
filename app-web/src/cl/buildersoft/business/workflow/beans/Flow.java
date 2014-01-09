package cl.buildersoft.business.workflow.beans;

import java.util.List;

import cl.buildersoft.framework.beans.BSBean;

public class Flow extends BSBean {
	private static final long serialVersionUID = -2550466801996973243L;

	private String name = null;
	private List<StepFlow> steps = null;
	private List<FieldFlow> fields = null;

}
