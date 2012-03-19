package cl.buildersoft.web.servlet.login;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.buildersoft.framework.beans.Menu;
import cl.buildersoft.framework.beans.Rol;
import cl.buildersoft.framework.services.BSUserService;
import cl.buildersoft.framework.services.impl.BSUserServiceImpl;
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
		try {
			HttpSession session = request.getSession(false);
			Connection conn = new BSDataUtils().getConnection(
					getServletContext(), "bsframework");

			Rol rol = null;
			synchronized (session) {
				rol = (Rol) session.getAttribute("Rol");
			}

			BSUserService userService = new BSUserServiceImpl();
			
			Menu menu = userService.getMenu(conn, rol);
			synchronized (session) {
				session.setAttribute("Menu", menu);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		String page = "/WEB-INF/jsp/home/index.jsp";
		request.getRequestDispatcher(page).forward(request, response);
	}

}
