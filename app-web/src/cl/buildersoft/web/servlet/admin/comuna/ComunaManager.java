package cl.buildersoft.web.servlet.admin.comuna;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.lib.util.crud.BSTableConfig;
import cl.buildersoft.web.servlet.common.BSHttpServlet;
import cl.buildersoft.web.servlet.common.crud.BSHttpServletCRUD;

@WebServlet("/servlet/admin/comuna/ComunaManager")
public class ComunaManager extends BSHttpServletCRUD {

	private static final long serialVersionUID = -3771211715459399925L;

	@Override
	protected BSTableConfig getBSTableConfig(HttpServletRequest request) {
		BSTableConfig table =  initTable(request, "tComuna");
		table.setTitle("Comunas del país");

		table.setSortField("cName");
		return table;
	}

	 

}
