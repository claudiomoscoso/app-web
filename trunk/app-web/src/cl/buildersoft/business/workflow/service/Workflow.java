package cl.buildersoft.business.workflow.service;

import java.util.List;

import cl.buildersoft.business.workflow.beans.Flow;
import cl.buildersoft.business.workflow.beans.Instance;
import cl.buildersoft.business.workflow.beans.StepFlow;
import cl.buildersoft.framework.beans.Rol;
import cl.buildersoft.framework.beans.User;

public interface Workflow {
	public List<Flow> getFlows();

	public Instance start(Long flow);

	public List<Instance> listInstances(User user);

	public List<Instance> listInstances(Rol user);

	public Instance getInstance(Long id);

	public void update(Instance instance);

	public List<StepFlow> getNextStep(Instance instance);

	public void jumpStep(Instance instance, StepFlow stepFlow);

	public void loadFieldsFlow(Flow flow);

	public void loadFieldsInstance(Instance instance);

	public void updateFieldsInstance(Instance instance);

	// public void loadFlowFromXML(String xml);

}
