package cl.buildersoft.business.workflow.beans;

import java.util.List;

import cl.buildersoft.framework.beans.BSBean;
import cl.buildersoft.framework.dataType.BSDataType;

public class StepFlow extends BSBean {
	private static final long serialVersionUID = 6066212423899455423L;
	
	private String name=null;
	private BSDataType type =null;
	private Integer order=null;
	private List<Long> next=null;
}
