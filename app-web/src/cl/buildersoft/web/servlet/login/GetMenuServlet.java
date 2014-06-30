package cl.buildersoft.web.servlet.login;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.buildersoft.framework.beans.Menu;
import cl.buildersoft.framework.beans.Rol;
import cl.buildersoft.framework.services.BSMenuService;
import cl.buildersoft.framework.services.impl.BSMenuServiceImpl;
import cl.buildersoft.framework.util.BSDataUtils;
import cl.buildersoft.sso.filter.BSHttpServletSSO;

/**
 * Servlet implementation class GetMenu
 */
@WebServlet(urlPatterns = "/servlet/login/GetMenuServlet")
public class GetMenuServlet extends BSHttpServletSSO {

	private static final long serialVersionUID = -4457825801379197051L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ServletContext ctx = request.getServletContext();
		/** TODO: Debe ser el datasource de timecontrol */
		Connection conn = new BSDataUtils().getConnection2(ctx, (String) ctx.getAttribute("bsframework.datasource"));

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
