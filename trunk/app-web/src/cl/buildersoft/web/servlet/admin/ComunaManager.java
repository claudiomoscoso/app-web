package cl.buildersoft.web.servlet.admin;

import javax.servlet.annotation.WebServlet;

import cl.buildersoft.framework.beans.BSHeadConfig;
import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.web.servlet.BSHttpServlet;

@WebServlet("/servlet/admin/ComunaManager")
public class ComunaManager extends BSHttpServlet {

	private static final long serialVersionUID = -3771211715459399925L;

	@Override
	protected BSTableConfig getBSTableConfig() {
		BSTableConfig table = new BSTableConfig("bscommon", "tComuna");
		table.setTitle("Comunas del país");

		table.setSortField("cName");
		return table;
	}

	@Override
	protected BSHeadConfig getBSHeadConfig() {
		return null;
	}

}
