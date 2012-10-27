package cl.buildersoft.web.servlet.system.user;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cl.buildersoft.framework.beans.BSAction;
import cl.buildersoft.framework.beans.BSField;
import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.framework.beans.Domain;
import cl.buildersoft.framework.beans.User;
import cl.buildersoft.framework.type.BSActionType;
import cl.buildersoft.web.servlet.common.BSHttpServlet;

/**
 * Servlet implementation class UserManager
 */
@WebServlet("/servlet/system/user/UserManager")
public class UserManager extends BSHttpServlet {
	private static final long serialVersionUID = -3497399350893131897L;

	@Override
	protected BSTableConfig getBSTableConfig(HttpServletRequest request) {
		HttpSession session = request.getSession();

		Boolean isAdmin = null;
		User user = null;
		BSTableConfig table = null;
		synchronized (session) {
			user = (User) session.getAttribute("User");
			isAdmin = user.getAdmin();
		}

		BSField field = null;
		if (isAdmin) {
			table = new BSTableConfig("bsframework", "tUser", "vUserAdmin");
			table.setSaveSP("bsframework.pSaveUserAdmin");
		} else {
			table = new BSTableConfig("bsframework", "tUser", "vUser");
		}
		table.setTitle("Usuarios del sistema");
		table.setDeleteSP("bsframework.pDelUser");

		field = new BSField("cId", "ID");
		field.setPK(true);
		table.addField(field);

		field = new BSField("cMail", "Mail");
		table.addField(field);

		field = new BSField("cName", "Nombre");
		table.addField(field);

		if (isAdmin) {
			field = new BSField("cAdmin", "Administrador");
			table.addField(field);

			BSAction domainRelation = new BSAction("ROL_DOMAIN", null);
			domainRelation.setNatTable("bsframework", "tR_UserDomain", "bsframework", "tDomain");
			domainRelation.setLabel("Dominios del usuario");
			table.addAction(domainRelation);
		}

		BSAction changePassword = new BSAction("CH_PASS", BSActionType.Record);
		changePassword.setLabel("Cambio de clave");
		changePassword.setUrl("/servlet/system/changepassword/SearchPassword");
		table.addAction(changePassword);

		BSAction rolRelation = new BSAction("ROL_RELATION", null);

		Domain domain = (Domain) session.getAttribute("Domain");

		rolRelation.setNatTable(domain.getName(), "tR_UserRol", domain.getName(), "tRol");
		rolRelation.setLabel("Roles de usuario");
		table.addAction(rolRelation);

		return table;
	}
}
