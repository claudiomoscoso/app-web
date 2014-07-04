package cl.buildersoft.business.workflow.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import cl.buildersoft.business.workflow.beans.Flow;
import cl.buildersoft.business.workflow.beans.Instance;
import cl.buildersoft.business.workflow.beans.Step;
import cl.buildersoft.business.workflow.service.Workflow;
import cl.buildersoft.lib.beans.User;
import cl.buildersoft.lib.database.BSBeanUtils;
import cl.buildersoft.lib.exception.BSConfigurationException;

public class WorkflowImpl implements Workflow {

	private static final String DATASOURCE_NAME = "jdbc/bsframework";

	public Connection getConnection(String dataSourceName) {
		Connection out = null;

		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup(dataSourceName);
			out = ds.getConnection();

		} catch (NamingException e) {
			e.printStackTrace();
			throw new BSConfigurationException(e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BSConfigurationException(e);
		}

		return out;
	}

	@Override
	public List<Flow> getFlows() {
		Connection conn = getConnection(DATASOURCE_NAME);

		BSBeanUtils bu = new BSBeanUtils();
		Flow flow = new Flow();

		List<Flow> out = (List<Flow>) bu.listAll(conn, flow);

		return out;
	}

	@Override
	public List<Flow> getFlows(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Instance start(Long flow, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Instance> listInstances(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Instance getInstance(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Instance instance, User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Step> getNextStep(Instance instance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void jumpStep(Instance instance, Step step, User user) {
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
	public void updateFieldsInstance(Instance instance, User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadFlowFromXPDL(String xml) {
		// TODO Auto-generated method stub
		
	}

	 

}
