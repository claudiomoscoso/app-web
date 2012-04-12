package cl.buildersoft.web.servlet.common;

import java.sql.Connection;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.web.servlet.BSHttpServlet;

@WebServlet("/servlet/common/ParameterManager")
public class ParameterManager extends BSHttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	public ParameterManager() {
		super();
	}

	@Override
	protected BSTableConfig getBSTableConfig(HttpServletRequest request) {
		BSTableConfig table = new BSTableConfig("bsframework", "tParameter");
		
		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request.getServletContext(), "bsframework");
		table.configFields(conn, mysql);
//		BSField field;

		table.setTitle("Par�metros del sistema");
		
		
		
//		field = new BSField("cId", "Id");
//		table.addField(field);

//		field = new BSField("cSystem", "Sistema");
//		field.setFK("bscommon", "vSystem", "cName");
//		table.addField(field);

//		field = new BSField("cKey", "Llave");
//		table.addField(field);

//		field = new BSField("cLabel", "Nombre");
//		table.addField(field);

//		field = new BSField("cValue", "Valor");
//		table.addField(field);
//
//		field = new BSField("cType", "Tipo");
//		field.setFK("bscommon", "vType", "cName");
//		table.addField(field);

		// table.setSortField("cNombre");

		// table.setTitle("Par�metros del sistema");
		table.renameAction("INSERT", "ADD_PARAMS");
		table.renameAction("EDIT", "MOD_PARAMS");
		table.renameAction("DELETE", "DEL_PARAMS");

		return table;
	}

	 

}
