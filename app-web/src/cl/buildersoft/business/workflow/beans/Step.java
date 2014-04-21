package cl.buildersoft.business.workflow.beans;

import java.util.List;

import cl.buildersoft.framework.beans.BSBean;
import cl.buildersoft.framework.dataType.BSDataType;

public class Step extends BSBean {
	private static final long serialVersionUID = 6066212423899455423L;
	private String name = null;
	private BSDataType type = null;
	private Integer order = null;
	private List<Long> nextStepFlow = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<Long> getNext() {
		return nextStepFlow;
	}

	public void setNext(List<Long> next) {
		this.nextStepFlow = next;
	}
}
