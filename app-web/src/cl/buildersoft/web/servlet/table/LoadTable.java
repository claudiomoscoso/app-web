package cl.buildersoft.web.servlet.table;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.exception.BSDataBaseException;
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
		/**
		 * request.getRequestDispatcher("/WEB-INF/jsp/common/pagination.jsp")
		 * .include(request, response);
		 */

		String sql = table.getSQL4SelectAll();

		Connection conn = null;
		BSmySQL mySQL = new BSmySQL();
		try {
			conn = mySQL.getConnection(getServletContext(), "bsframework");
			BSPaging paging = new BSPaging(conn, mySQL, table, request);

			ResultSet rs;
			if (paging.getRequiresPaging()) {
				rs = mySQL.queryResultSet(conn, sql, null,
						paging.getFirstRecord(), paging.getRecordPerPage());
			} else {
				rs = mySQL.queryResultSet(conn, sql, null);
			}

			request.setAttribute("Data", rs);
			request.setAttribute("Paging", paging);

			table.configFields(rs);
			table.configFKFields(conn, mySQL);

			synchronized (session) {
				session.setAttribute("BSTable", table);
			}

		} catch (SQLException e) {
			throw new BSDataBaseException("0300", e.getMessage());
		}

		request.getRequestDispatcher("/WEB-INF/jsp/table/main.jsp").forward(
				request, response);
	}

}
