package cl.buildersoft.business.workflow.service;

import java.util.List;

import cl.buildersoft.business.workflow.beans.Flow;
import cl.buildersoft.business.workflow.beans.Instance;
import cl.buildersoft.business.workflow.beans.StepFlow;

public interface Workflow {
	// public void doAction(Long id);
	public List<Flow> getFlows();

	public Instance start(Long flow);

	public Instance getInstance(Long id);

	public List<StepFlow> getNextStep(Instance instance);

	public void fillFieldsFlow(Flow flow);

	public void fillFieldsInstance(Instance instance);

}
