package cl.buildersoft.web.servlet.system.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/system/user/UserNew")
public class UserNew extends HttpServlet {
	private static final long serialVersionUID = 1073693533198008317L;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/jsp/system/user/user-form.jsp")
				.forward(request, response);
	}
}
