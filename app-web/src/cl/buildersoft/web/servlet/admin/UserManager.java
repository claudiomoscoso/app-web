package cl.buildersoft.web.servlet.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import cl.buildersoft.framework.beans.BSCss;
import cl.buildersoft.framework.beans.BSField;
import cl.buildersoft.framework.beans.BSHeadConfig;
import cl.buildersoft.framework.beans.BSScript;
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
		field.setValidationOnBlur("validationUser");
		table.addField(field);

		field = new BSField("cName", "Nombre");
		table.addField(field);
		
//		table.removeAction("DELETE");
		/**		table.removeAction("EDIT");
		*/
		return table;
	}

	@Override
	protected BSHeadConfig getBSHeadConfig() {
		BSHeadConfig head = new BSHeadConfig();
		BSScript bsScript = new BSScript("/js/user/");
		BSCss bsCss = new BSCss("/css/user/");
		
		List<String> listScripts = new ArrayList<String>();
		listScripts.add("UserScript");
		List<String> listCss = new ArrayList<String>();
		listCss.add("UserCss");
		
		bsScript.setListScriptNames(listScripts);
		bsCss.setListCssNames(listCss);		
		
		head.setCss(bsCss);
		head.setScript(bsScript);
		
		return head;
	}
}
