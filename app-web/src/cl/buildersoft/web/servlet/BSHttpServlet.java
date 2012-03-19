package cl.buildersoft.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.buildersoft.framework.beans.BSTableConfig;

public abstract class BSHttpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected abstract BSTableConfig getBSTableConfig();

	public BSHttpServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		BSTableConfig table = getBSTableConfig();

		HttpSession session = request.getSession();
		synchronized (session) {
			session.setAttribute("BSTable", table);
		}

		request.getRequestDispatcher("/servlet/table/LoadTable").forward(request,
				response);
	}

}
