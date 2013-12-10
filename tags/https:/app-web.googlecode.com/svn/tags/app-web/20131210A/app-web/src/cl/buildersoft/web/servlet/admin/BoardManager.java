package cl.buildersoft.web.servlet.admin;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.web.servlet.BSHttpServlet;

@WebServlet("/servlet/admin/BoardManager")
public class BoardManager extends BSHttpServlet {

	private static final long serialVersionUID = -3771211715459399925L;

	@Override
	protected BSTableConfig getBSTableConfig(HttpServletRequest request) {
		BSTableConfig table = new BSTableConfig("bscommon", "tBoard");
		table.setTitle("Tablón (tBoard)");

		table.setSortField("cType, cKey");
		return table;
	}

 
}
