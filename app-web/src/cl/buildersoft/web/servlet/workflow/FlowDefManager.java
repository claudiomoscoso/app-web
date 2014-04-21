package cl.buildersoft.web.servlet.workflow;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.business.workflow.beans.Flow;
import cl.buildersoft.business.workflow.service.Workflow;
import cl.buildersoft.business.workflow.service.impl.WorkflowImpl;
import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.web.servlet.common.BSHttpServlet;

@WebServlet("/servlet/workflow/FlowDefManager")
public class FlowDefManager extends BSHttpServlet {
	private static final long serialVersionUID = 5031905095957507242L;

	@Override
	protected BSTableConfig getBSTableConfig(HttpServletRequest request) {

		Workflow wf = new WorkflowImpl();

		List<Flow> list = wf.getFlows();

		BSTableConfig table = initTable(request, "tFlow");
		table.setTitle("Definición de Flujos");

		table.getField("cName").setLabel("Nombre");
		return table;
	}
}
