package cl.buildersoft.web.servlet.system.role;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.lib.beans.Domain;
import cl.buildersoft.lib.util.crud.BSField;
import cl.buildersoft.lib.util.crud.BSTableConfig;
import cl.buildersoft.web.servlet.common.crud.BSHttpServletCRUD;

/**
 * Servlet implementation class RolManager
 */
@WebServlet("/servlet/system/role/RolManager")
public class RolManager extends BSHttpServletCRUD {
	private static final long serialVersionUID = 1L;

	public RolManager() {
		super();

	}

	@Override
	protected BSTableConfig getBSTableConfig(HttpServletRequest request) {
		Domain		domain = (Domain)request.getSession().getAttribute("Domain");
		
		BSTableConfig table = new BSTableConfig(domain.getAlias(), "tRol");
		table.setTitle("Mantenimeito de Roles");

		BSField field = new BSField("cId", "Id");
		table.addField(field);

		field = new BSField("cName", "Nombre");
		table.addField(field);

		return table;
	}

}
