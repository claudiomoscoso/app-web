package cl.buildersoft.web.servlet.workflow;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.web.servlet.common.BSHttpServlet;

@WebServlet("/servlet/workflow/InstancesManager")
public class InstancesManager extends BSHttpServlet {
	private static final long serialVersionUID = 583201704776707035L;

	@Override
	protected BSTableConfig getBSTableConfig(HttpServletRequest request) {
		BSTableConfig table = initTable(request, "tInstance");

		table.removeAction("DELETE");
		table.removeAction("INSERT");
		table.removeAction("EDIT");

		return table;
	}

}
