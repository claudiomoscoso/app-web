package cl.buildersoft.web.servlet.workflow;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.lib.util.crud.BSTableConfig;
import cl.buildersoft.web.servlet.common.crud.BSHttpServletCRUD;

@WebServlet("/servlet/workflow/FlowElementManager")
public class FlowElementManager extends BSHttpServletCRUD implements Servlet {

	private static final long serialVersionUID = 4773950162044504458L;

	@Override
	protected BSTableConfig getBSTableConfig(HttpServletRequest request) {
		BSTableConfig table = super.initTable(request, "tFlowElement");

		/**
		BSField typeElement = table.getField("cType");
		typeElement.setFK(typeElement.getFKDatabase(), "tFlowElementType", typeElement.getFKField());
*/
		table.setTitle("Elementos de Flujo");
		return table;
	}

}
