package cl.buildersoft.web.servlet.common.crud;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.buildersoft.lib.util.crud.BSField;
import cl.buildersoft.lib.util.crud.BSTableConfig;
import cl.buildersoft.web.servlet.common.BSHttpServlet;

@WebServlet("/servlet/common/crud/NewRecord")
public class NewRecord extends BSHttpServlet {
	private static final long serialVersionUID = 6670045198123132551L;
	public static String URL = "/servlet/common/crud/NewRecord";

	public NewRecord() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		BSTableConfig table = null;
		synchronized (session) {
			table = (BSTableConfig) session.getAttribute("BSTable");
		}
		BSField[] fields = table.getFields();
		for (BSField f : fields) {
			f.setValue(null);
		}
		request.setAttribute("Action", "Insert");
		forward(request, response, "/WEB-INF/jsp/table/data-form.jsp");
		// request.getRequestDispatcher("/WEB-INF/jsp/table/data-form.jsp").forward(request,
		// response);
	}

}
