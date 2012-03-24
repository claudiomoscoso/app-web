package cl.buildersoft.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.buildersoft.framework.beans.BSHeadConfig;
import cl.buildersoft.framework.beans.BSTableConfig;

public abstract class BSHttpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected abstract BSTableConfig getBSTableConfig();

	protected abstract BSHeadConfig getBSHeadConfig();

	public BSHttpServlet() {
		super();
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		BSHeadConfig head = getBSHeadConfig();
		BSTableConfig table = getBSTableConfig();

		String uri = request.getRequestURI().substring(request.getContextPath().length());
		
		table.setUri(uri);

		HttpSession session = request.getSession();
		synchronized (session) {
			session.setAttribute("BSTable", table);
			session.setAttribute("BSHead", head);
		}

		request.getRequestDispatcher("/servlet/table/LoadTable").forward(
				request, response);
	}

}
