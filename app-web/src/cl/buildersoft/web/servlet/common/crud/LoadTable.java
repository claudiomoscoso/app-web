package cl.buildersoft.web.servlet.common.crud;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.buildersoft.lib.database.BSmySQL;
import cl.buildersoft.lib.util.crud.BSPaging;
import cl.buildersoft.lib.util.crud.BSTableConfig;
import cl.buildersoft.web.servlet.common.BSHttpServlet;

@WebServlet("/servlet/common/crud/LoadTable")
public class LoadTable extends BSHttpServlet {  
	private static final long serialVersionUID = -5652978400823425729L;
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

		Connection conn = getConnection(request);

		BSmySQL mysql = new BSmySQL();
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

		forward(request, response, "/WEB-INF/jsp/table/main.jsp");
		// request.getRequestDispatcher("/WEB-INF/jsp/table/main2.jsp").forward(request,
		// response);
	}

}
