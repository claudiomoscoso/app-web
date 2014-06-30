package cl.buildersoft.web.servlet.common.crud;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.util.crud.BSPaging;
import cl.buildersoft.framework.util.crud.BSTableConfig;
import cl.buildersoft.web.servlet.common.AbstractServletUtil;

@WebServlet("/servlet/common/crud/LoadTable")
public class LoadTable extends AbstractServletUtil {
	private static final long serialVersionUID = 1L;
	public static String URL = "/servlet/common/crud/LoadTable";

	public LoadTable() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		BSTableConfig table = null;
		synchronized (session) {
			table = (BSTableConfig) session.getAttribute("BSTable");
		}

		BSmySQL mysql = new BSmySQL();

		Connection conn = mysql.getConnection(request);

		table.configFields(conn, mysql);

		BSPaging paging = new BSPaging(conn, mysql, table, request);
		ResultSet rs = mysql.queryResultSet(conn, table, paging);

		request.setAttribute("Data", rs);
		request.setAttribute("Conn", conn);
		request.setAttribute("Paging", paging);
		request.setAttribute("Search", paging.getSearchValue(request));

		synchronized (session) {
			session.setAttribute("BSTable", table);
		}

		request.getRequestDispatcher("/WEB-INF/jsp/table/main2.jsp").forward(request, response);
	}

}
