package cl.buildersoft.web.servlet.workflow;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.web.servlet.common.BSHttpServlet;

@WebServlet("/servlet/workflow/FlowDefManager")
public class FlowDefManager extends BSHttpServlet {
	private static final long serialVersionUID = 5031905095957507242L;

	@Override
	protected BSTableConfig getBSTableConfig(HttpServletRequest request) {
		BSTableConfig table =  initTable(request, "tFlowDef");
		table.setTitle("Definición de Flujos");
		
		table.getField("cName").setLabel("Nombre");
		return table;
	}
}
