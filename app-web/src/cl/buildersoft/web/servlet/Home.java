package cl.buildersoft.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.sso.filter.BSHttpServletSSO;

@WebServlet("/servlet/Home")
public class Home extends BSHttpServletSSO {

	private static final long serialVersionUID = -3485155081742992753L;

	public Home() {
		super();

	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String page = "/WEB-INF/jsp/home/index.jsp";
		request.getRequestDispatcher(page).forward(request, response);

	}

}
