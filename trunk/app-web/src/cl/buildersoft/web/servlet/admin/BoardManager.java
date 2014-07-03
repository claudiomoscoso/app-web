package cl.buildersoft.web.servlet.admin;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.framework.util.crud.BSTableConfig;
import cl.buildersoft.web.servlet.common.crud.BSHttpServletCRUD;

@WebServlet("/servlet/admin/BoardManager")
public class BoardManager extends BSHttpServletCRUD {

	private static final long serialVersionUID = -3771211715459399925L;

	@Override
	protected BSTableConfig getBSTableConfig(HttpServletRequest request) {
		BSTableConfig table = new BSTableConfig("bscommon", "tBoard");
		table.setTitle("Tabl�n (tBoard)");

		table.setSortField("cType, cKey");
		return table;
	}

 
}
