package cl.buildersoft.business.workflow.service;

import java.util.List;

import cl.buildersoft.business.workflow.beans.Flow;
import cl.buildersoft.business.workflow.beans.Instance;
import cl.buildersoft.business.workflow.beans.Step;
import cl.buildersoft.framework.beans.User;

public interface Workflow {
	public List<Flow> getFlows();

	public List<Flow> getFlows(User user);

	public Instance start(Long flow, User user);

	public List<Instance> listInstances(User user);

	public Instance getInstance(Long id);

	public void update(Instance instance, User user);

	public List<Step> getNextStep(Instance instance);

	public void jumpStep(Instance instance, Step step, User user);

	public void loadFieldsFlow(Flow flow);

	public void loadFieldsInstance(Instance instance);

	public void updateFieldsInstance(Instance instance, User user);

	public void loadFlowFromXPDL(String xml);

}
