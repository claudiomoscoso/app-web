package cl.buildersoft.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowParameters
 */
@WebServlet("/servlet/ShowParameters")
public class ShowParameters extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowParameters() {
		super();
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/jsp/common/head.jsp").include(
				request, response);
		request.getRequestDispatcher("/WEB-INF/jsp/common/menu.jsp").include(
				request, response);

		PrintWriter out = response.getWriter();
		Enumeration<String> names = request.getParameterNames();

		out.print("<ul class='cLabel'>");
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();

			out.println("<li>" + name);
			String[] values = request.getParameterValues(name);

			out.print("<ul>");
			for (String value : values) {
				out.println("<li class='cData'>" + value + "</li>");
			}
			out.print("</ul></li>");

		}
		out.print("</ul>");

		request.getRequestDispatcher("/WEB-INF/jsp/common/footer.jsp").include(
				request, response);

		out.flush();
	}

}
