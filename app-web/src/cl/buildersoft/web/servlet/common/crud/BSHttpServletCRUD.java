package cl.buildersoft.web.servlet.common.crud;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.buildersoft.lib.beans.Domain;
import cl.buildersoft.lib.database.BSmySQL;
import cl.buildersoft.lib.util.crud.BSTableConfig;
import cl.buildersoft.sso.filter.BSHttpServletSSO;
import cl.buildersoft.web.servlet.common.BSHttpServlet;

public abstract class BSHttpServletCRUD extends BSHttpServlet {
	private static final long serialVersionUID = 713819586332712332L;

	protected abstract BSTableConfig getBSTableConfig(HttpServletRequest request);

	public BSHttpServletCRUD() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BSTableConfig table = getBSTableConfig(request);

		String uri = request.getRequestURI().substring(request.getContextPath().length());

		table.setUri(uri);

		HttpSession session = request.getSession();
		synchronized (session) {
			session.setAttribute("BSTable", table);
		}

		forward(request, response, LoadTable.URL);
		// request.getRequestDispatcher(LoadTable.URL).forward(request,
		// response);
	}

	protected BSTableConfig initTable(HttpServletRequest request, String tableName) {
		Domain domain = (Domain) request.getSession().getAttribute("Domain");

		BSTableConfig table = new BSTableConfig(domain.getAlias(), tableName);
		BSmySQL mysql = new BSmySQL();
		
		BSHttpServletSSO utilServlet = new BSHttpServletSSO();
		Connection conn = utilServlet.getConnection(request);
		table.configFields(conn, mysql);
		mysql.closeConnection(conn);
		return table;
	}

	protected void hideFields(BSTableConfig table, String... hideFields) {
		for (String fieldName : hideFields) {
			table.getField(fieldName).setVisible(false);
		}
	}
	
	public String getCommas(String[] tableFields) {
		String out = "";
		for (int i = 0; i < tableFields.length; i++) {
			out += "?,";
		}
		out = out.substring(0, out.length() - 1);
		return out;
	}

}
