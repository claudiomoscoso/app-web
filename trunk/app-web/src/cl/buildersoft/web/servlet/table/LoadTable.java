package cl.buildersoft.web.servlet.table;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.util.BSPaging;

/**
 * Servlet implementation class LoadTable
 */
@WebServlet("/servlet/table/LoadTable")
public class LoadTable extends AbstractServletUtil {
	private static final long serialVersionUID = 1L;

	public LoadTable() {
		super();
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		BSTableConfig table = null;
		synchronized (session) {
			table = (BSTableConfig) session.getAttribute("BSTable");
		}

		Connection conn = null;
		BSmySQL mySQL = new BSmySQL();

		conn = mySQL.getConnection(getServletContext(), table.getDatabase());

		table.configFields(conn, mySQL);

		BSPaging paging = new BSPaging(conn, mySQL, table, request);
		ResultSet rs = mySQL.queryResultSet(conn, table, paging);

		request.setAttribute("Data", rs);
		request.setAttribute("Paging", paging);
		request.setAttribute("Search", paging.getSearchValue(request));

		synchronized (session) {
			session.setAttribute("BSTable", table);
		}

		request.getRequestDispatcher("/WEB-INF/jsp/table/main.jsp").forward(
				request, response);
	}

}
