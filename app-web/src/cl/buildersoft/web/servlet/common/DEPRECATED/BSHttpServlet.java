package cl.buildersoft.web.servlet.common.DEPRECATED;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.buildersoft.lib.beans.Domain;
import cl.buildersoft.lib.database.BSmySQL;
import cl.buildersoft.lib.util.crud.BSTableConfig;

public abstract class BSHttpServlet extends HttpServlet {
	private static final long serialVersionUID = 713819586332712332L;

	protected abstract BSTableConfig getBSTableConfig(HttpServletRequest request);

	public BSHttpServlet() {
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
		
		request.getRequestDispatcher("/servlet/common/LoadTable").forward(request, response);
	}

	protected BSTableConfig initTable(HttpServletRequest request, String tableName) {
		Domain domain = (Domain) request.getSession().getAttribute("Domain");

		BSTableConfig table = new BSTableConfig(domain.getAlias(), tableName);
		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);
		table.configFields(conn, mysql);
		mysql.closeConnection(conn);
		return table;
	}

	protected void hideFields(BSTableConfig table, String... hideFields) {
		for (String fieldName : hideFields) {
			table.getField(fieldName).setVisible(false);
		}
	}

}
