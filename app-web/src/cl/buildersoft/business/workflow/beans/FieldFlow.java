package cl.buildersoft.business.workflow.beans;

import cl.buildersoft.framework.beans.BSBean;
import cl.buildersoft.framework.dataType.BSDataType;

public class FieldFlow extends BSBean {
	private static final long serialVersionUID = 5573164459367640372L;

	private String name = null;
	private String label = null;
	private BSDataType type = null;
	private Integer order = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public BSDataType getType() {
		return type;
	}

	public void setType(BSDataType type) {
		this.type = type;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

}
