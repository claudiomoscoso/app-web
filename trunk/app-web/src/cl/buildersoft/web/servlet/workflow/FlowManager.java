package cl.buildersoft.web.servlet.workflow;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.framework.util.crud.BSTableConfig;
import cl.buildersoft.web.servlet.common.BSHttpServlet;

@WebServlet("/servlet/workflow/FlowManager")
public class FlowManager extends BSHttpServlet {
	private static final long serialVersionUID = 5031905095957507242L;

	@Override
	protected BSTableConfig getBSTableConfig(HttpServletRequest request) {

		BSTableConfig table = initTable(request, "tFlow");
		table.setTitle("Definición de Flujos");

		// System.out.println("Flow Manager Executed");

		table.getField("cName").setLabel("Nombre");
		return table;
	}
}
