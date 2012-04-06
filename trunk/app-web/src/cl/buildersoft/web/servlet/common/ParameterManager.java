package cl.buildersoft.web.servlet.common;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebServlet;

import cl.buildersoft.framework.beans.BSField;
import cl.buildersoft.framework.beans.BSHeadConfig;
import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.web.servlet.BSHttpServlet;

@WebServlet("/servlet/common/ParameterManager")
public class ParameterManager extends BSHttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	public ParameterManager() {
		super();
	}

	@Override
	protected BSTableConfig getBSTableConfig() {
		BSTableConfig table = new BSTableConfig("bsframework", "tParameter");
		BSField field;

		table.setTitle("Parámetros del sistema");
		
		field = new BSField("cId", "Id");
		table.addField(field);

		field = new BSField("cSystem", "Sistema");
		field.setFK("bscommon", "vSystem", "cName");
		table.addField(field);

		field = new BSField("cKey", "Llave");
		table.addField(field);

		field = new BSField("cLabel", "Nombre");
		table.addField(field);

		field = new BSField("cValue", "Valor");
		table.addField(field);

		field = new BSField("cType", "Tipo");
		field.setFK("bscommon", "vType", "cName");
		table.addField(field);

		// table.setSortField("cNombre");

		// table.setTitle("Parámetros del sistema");
		table.renameAction("INSERT", "ADD_PARAMS");
		table.renameAction("EDIT", "MOD_PARAMS");
		table.renameAction("DELETE", "DEL_PARAMS");

		return table;
	}

	@Override
	protected BSHeadConfig getBSHeadConfig() {
		return null;
	}

}
