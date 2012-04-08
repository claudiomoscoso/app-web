package cl.buildersoft.web.servlet.csv;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/csv/UploadFile")
public class UploadFile extends HttpServlet {

	private static final long serialVersionUID = 4426203065287341845L;

	public UploadFile() {
		super();
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/jsp/csv/uploadFile.jsp")
				.forward(request, response);
	}

}
