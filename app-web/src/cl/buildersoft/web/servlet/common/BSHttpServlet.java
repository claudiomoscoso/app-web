package cl.buildersoft.web.servlet.common;

import cl.buildersoft.sso.filter.BSHttpServletSSO;

public abstract class BSHttpServlet extends BSHttpServletSSO {
	private static final long serialVersionUID = 1537122543903845585L;

	
	
	/**
	 * <code>
	protected abstract BSTableConfig getBSTableConfig(HttpServletRequest request);

	public BSHttpServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BSTableConfig table = getBSTableConfig(request);

		String uri = request.getRequestURI().substring(request.getContextPath().length());

		table.setUri(uri);

		HttpSession session = request.getSession();
		synchronized (session) {
			session.setAttribute("BSTable", table);
		}
		
		request.getRequestDispatcher("/servlet/common/LoadTable").forward(request, response);
	}

	protected BSTableConfig initTable(HttpServletRequest request, String tableName) {
		Domain domain = (Domain) request.getSession().getAttribute("Domain");

		BSTableConfig table = new BSTableConfig(domain.getAlias(), tableName);
		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);
		table.configFields(conn, mysql);
		mysql.closeConnection(conn);
		return table;
	}

	protected void hideFields(BSTableConfig table, String... hideFields) {
		for (String fieldName : hideFields) {
			table.getField(fieldName).setVisible(false);
		}
	}
</code>
	 */
}
