package cl.buildersoft.web.servlet.admin;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.web.servlet.BSHttpServlet;

@WebServlet("/servlet/admin/ComunaManager")
public class ComunaManager extends BSHttpServlet {

	private static final long serialVersionUID = -3771211715459399925L;

	@Override
	protected BSTableConfig getBSTableConfig(HttpServletRequest request) {
		BSTableConfig table = new BSTableConfig("bscommon", "tComuna");
		table.setTitle("Comunas del país");

		table.setSortField("cName");
		return table;
	}

	 

}
