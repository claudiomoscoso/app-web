package cl.buildersoft.web.servlet.login;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.buildersoft.framework.beans.Menu;
import cl.buildersoft.framework.beans.Rol;
import cl.buildersoft.framework.services.BSMenuService;
import cl.buildersoft.framework.services.impl.BSMenuServiceImpl;
import cl.buildersoft.framework.util.BSDataUtils;

/**
 * Servlet implementation class GetMenu
 */
@WebServlet(urlPatterns = "/servlet/login/GetMenuServlet")
public class GetMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetMenuServlet() {
		super();

	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		Connection conn = new BSDataUtils().getConnection(getServletContext(),
				"bsframework");

		List<Rol> rols = null;
		synchronized (session) {
			rols = (List<Rol>) session.getAttribute("Rol");
		}

		BSMenuService menuService = new BSMenuServiceImpl();

		Menu menu = menuService.getMenu(conn, rols, 1L);
		synchronized (session) {
			session.setAttribute("Menu", menu);
		}

		String page = "/servlet/Home";
		request.getRequestDispatcher(page).forward(request, response);
	}

}
