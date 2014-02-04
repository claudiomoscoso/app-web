package cl.buildersoft.business.workflow.service.impl;

import java.util.List;

import cl.buildersoft.business.workflow.beans.Flow;
import cl.buildersoft.business.workflow.beans.Instance;
import cl.buildersoft.business.workflow.beans.StepFlow;
import cl.buildersoft.business.workflow.service.Workflow;
import cl.buildersoft.framework.beans.Rol;
import cl.buildersoft.framework.beans.User;

public class WorkflowImpl implements Workflow {

	@Override
	public List<Flow> getFlows() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Instance start(Long flow) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Instance> listInstances(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Instance> listInstances(Rol user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Instance getInstance(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Instance instance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<StepFlow> getNextStep(Instance instance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void jumpStep(Instance instance, StepFlow stepFlow) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadFieldsFlow(Flow flow) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadFieldsInstance(Instance instance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateFieldsInstance(Instance instance) {
		// TODO Auto-generated method stub
		
	}

}
