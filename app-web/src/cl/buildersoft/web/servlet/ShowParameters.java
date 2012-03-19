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
		PrintWriter out = response.getWriter();
		Enumeration<String> names = request.getParameterNames();

		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();

			out.println(name + "<br>");
			String[] values = request.getParameterValues(name);

			for (String value : values) {
				out.println(value);
			}

		}
		out.flush();
	}

}
