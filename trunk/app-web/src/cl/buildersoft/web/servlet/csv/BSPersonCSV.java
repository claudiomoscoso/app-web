package cl.buildersoft.web.servlet.csv;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.framework.beans.BSHeadConfig;
import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.web.servlet.BSCSVServlet;

@WebServlet("/servlet/csv/Upload")
public class BSPersonCSV extends BSCSVServlet {
	private static final long serialVersionUID = 1L;

	public BSPersonCSV() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/common/no-access.jsp")
				.forward(request, response);
	}


	@Override
	protected BSTableConfig getBSTableConfig(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException  {
		// TODO Auto-generated method stub
		// String desc = request.getParameter("desc");
		BSTableConfig table = new BSTableConfig("remu", "tPerson");
		

		return table;
	}

	@Override
	protected BSHeadConfig getBSHeadConfig() {
		// TODO Auto-generated method stub
		return null;
	}
}
