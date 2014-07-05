package cl.buildersoft.web.servlet.config.systemparams;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.lib.util.crud.BSTableConfig;
import cl.buildersoft.web.servlet.common.BSHttpServlet;
import cl.buildersoft.web.servlet.common.crud.BSHttpServletCRUD;

@WebServlet("/servlet/config/systemparams/ParameterManager")
public class ParameterManager extends BSHttpServletCRUD implements Servlet {
	private static final long serialVersionUID = 5621476235297476355L;

	public ParameterManager() {
		super();
	}

	@Override
	protected BSTableConfig getBSTableConfig(HttpServletRequest request) {
		BSTableConfig table = initTable(request, "tParameter");

		table.setTitle("Parámetros del sistema");

		table.getField("cKey").setLabel("Llave");
		table.getField("cLabel").setLabel("Descripción");
		table.getField("cValue").setLabel("Valor");
		table.getField("cDataType").setLabel("Tipo de dato");

		table.renameAction("INSERT", "ADD_PARAMS");
		table.renameAction("EDIT", "MOD_PARAMS");
		table.renameAction("DELETE", "DEL_PARAMS");

		return table;
	}
}
