package cl.buildersoft.web.servlet.admin;

import javax.servlet.annotation.WebServlet;

import cl.buildersoft.framework.beans.BSField;
import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.web.servlet.BSHttpServlet;

/**
 * Servlet implementation class UserManager
 */
@WebServlet("/servlet/admin/UserManager")
public class UserManager extends BSHttpServlet {
	private static final long serialVersionUID = 1L;

	public UserManager() {
		super();

	}

	@Override
	protected BSTableConfig getBSTableConfig() {
		BSTableConfig table = new BSTableConfig("tUser");
		table.setTitle("Mantenimeito de usuarios");

		BSField field = null;
		field = new BSField("cId", "ID");
		table.addField(field);

		field = new BSField("cMail", "Correo electrónico/usuario");
		table.addField(field);

		field = new BSField("cName", "Nombre");
		table.addField(field);
		
//		table.removeAction("DELETE");
		/**		table.removeAction("EDIT");
		*/
		return table;
	}
}
