package cl.buildersoft.web.servlet.admin;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.framework.beans.BSAction;
import cl.buildersoft.framework.beans.BSField;
import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.framework.type.BSActionType;
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
	protected BSTableConfig getBSTableConfig(HttpServletRequest request) {
		BSTableConfig table = new BSTableConfig("bsframework", "tUser");
		table.setTitle("Mantenimiento de usuarios");

		BSField field = null;
		field = new BSField("cId", "ID");
		table.addField(field);

		field = new BSField("cMail", "Correo electrónico/usuario");
		field.setValidationOnBlur("validationUser");
		table.addField(field);

		field = new BSField("cName", "Nombre");
		table.addField(field);

		BSAction changePassword = new BSAction("CH_PASS", BSActionType.Record);
		changePassword.setLabel("Cambio de clave");
		changePassword.setUrl("/servlet/admin/SearchPassword");
		table.addAction(changePassword);

		BSAction rolRelation = new BSAction("ROL_RELATION", null);
		rolRelation.setNatTable("tR_UserRol", "tRol");
		rolRelation.setLabel("Roles de usuario");
		table.addAction(rolRelation);

		return table;
	}

	/** 	@Override <code>

	protected BSHeadConfig getBSHeadConfig() {
		BSHeadConfig head = new BSHeadConfig();
		BSScript bsScript = new BSScript("/js/user/");
		BSCss bsCss = new BSCss("/css/user/");

		List<String> listScripts = new ArrayList<String>();
		listScripts.add("UserScript");
		// listScripts.add("relation");

		List<String> listCss = new ArrayList<String>();
		listCss.add("UserCss");

		bsScript.setListScriptNames(listScripts);
		bsCss.setListCssNames(listCss);

		head.setCss(bsCss);
		head.setScript(bsScript);

		return head;
	}
	</code>*/
}
