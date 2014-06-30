package cl.buildersoft.web.servlet.workflow;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.framework.util.crud.BSAction;
import cl.buildersoft.framework.util.crud.BSActionType;
import cl.buildersoft.framework.util.crud.BSTableConfig;
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

		table.setTitle("Flujos en ejecución");
		table.getField("cFlow").setLabel("Flujo");
		table.getField("cCreator").setLabel("Creador");
		table.getField("cAssigned").setLabel("Asignado");
		table.getField("cStep").setLabel("Etapa");
		
		
		BSAction action = new BSAction("START", BSActionType.Table);
		action.setLabel("Nuevo");
		action.setUrl("/servlet/workflow/Start");
		table.addAction(action);

		return table;
	}

}
