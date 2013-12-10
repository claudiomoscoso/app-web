package cl.buildersoft.web.servlet.config.systemparams;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.web.servlet.common.BSHttpServlet;

@WebServlet("/servlet/config/systemparams/ParameterManager")
public class ParameterManager extends BSHttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	public ParameterManager() {
		super();
	}

	@Override
	protected BSTableConfig getBSTableConfig(HttpServletRequest request) {
		BSTableConfig table =  initTable(request, "tParameter");

		table.setTitle("Parámetros del sistema");
		
		table.renameAction("INSERT", "ADD_PARAMS");
		table.renameAction("EDIT", "MOD_PARAMS");
		table.renameAction("DELETE", "DEL_PARAMS");

		return table;
	}

	 

}
