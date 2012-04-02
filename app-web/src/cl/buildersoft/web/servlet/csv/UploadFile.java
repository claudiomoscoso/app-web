package cl.buildersoft.web.servlet.csv;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.framework.database.BSmySQL;

@WebServlet("/servlet/csv/UploadFile")
public class UploadFile extends HttpServlet {

	private static final long serialVersionUID = 4426203065287341845L;

	public UploadFile() {
		super();
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request.getServletContext(),
				"remu");

		mysql.cloneTable(conn, request, "tPerson");

		request.getRequestDispatcher("/servlet/Home")
				.forward(request, response);
	}

}
