package cl.buildersoft.web.servlet.admin;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.lib.util.crud.BSField;
import cl.buildersoft.lib.util.crud.BSTableConfig;
import cl.buildersoft.web.servlet.common.crud.BSHttpServletCRUD;

/**
 * Servlet implementation class RolManager
 */
@WebServlet("/servlet/admin/RolManager")
public class RolManager extends BSHttpServletCRUD {
	private static final long serialVersionUID = 4259494830171384785L;

	public RolManager() {
		super();

	}

	@Override
	protected BSTableConfig getBSTableConfig(HttpServletRequest request) {
		BSTableConfig table = new BSTableConfig("bsframework", "tRol");
		table.setTitle("Mantenimeito de Roles");

		BSField field = new BSField("cId", "Id");
		table.addField(field);

		field = new BSField("cName", "Nombre");
		table.addField(field);

		return table;
	}

}
