package cl.buildersoft.web.servlet.admin;

import javax.servlet.annotation.WebServlet;

import cl.buildersoft.framework.beans.BSField;
import cl.buildersoft.framework.beans.BSHeadConfig;
import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.web.servlet.BSHttpServlet;

/**
 * Servlet implementation class RolManager
 */
@WebServlet("/servlet/admin/RolManager")
public class RolManager extends BSHttpServlet {
	private static final long serialVersionUID = 1L;

	public RolManager() {
		super();

	}

	@Override
	protected BSTableConfig getBSTableConfig() {
		BSTableConfig table = new BSTableConfig("bsframework", "tRol");
		table.setTitle("Mantenimeito de Roles");

		BSField field = new BSField("cId", "Id");
		table.addField(field);

		field = new BSField("cName", "Nombre");
		table.addField(field);

		return table;
	}

	@Override
	protected BSHeadConfig getBSHeadConfig() {
		// TODO Auto-generated method stub
		return null;
	}
}
