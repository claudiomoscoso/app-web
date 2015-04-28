package cl.buildersoft.framework.util;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.framework.beans.User;
import cl.buildersoft.framework.database.BSmySQL;

public class BSHttpServlet extends HttpServlet {
	private static final long serialVersionUID = 7807647668104655759L;

	protected Connection getConnection(HttpServletRequest request) {
		Object connObject = request.getAttribute("Connection");
		Connection out = null;
		if (connObject == null) {
			BSmySQL mysql = new BSmySQL();
			out = mysql.getConnection(request);
			request.setAttribute("Connection", out);
		} else {
			out = (Connection) connObject;
		}
		return out;
	}

	protected void setApplicationValue(HttpServletRequest request, String name, Object object) {
		request.getServletContext().setAttribute(name, object);
	}

	protected Object getApplicationValue(HttpServletRequest request, String name) {
		return request.getServletContext().getAttribute(name);
	}

	protected void setSessionValue(HttpServletRequest request, String name, Object object) {
		request.getSession().setAttribute(name, object);
	}

	protected Object getSessionValue(HttpServletRequest request, String name) {
		return request.getSession().getAttribute(name);
	}

	protected void forward(HttpServletRequest request, HttpServletResponse response, String uri) throws ServletException,
			IOException {
		request.getRequestDispatcher(uri).forward(request, response);
	}

	protected String readParameterOrAttribute(HttpServletRequest request, String name) {
		String out = null;
		Object object = request.getAttribute(name);
		if (object != null) {
			out = (String) object;
		} else {
			out = request.getParameter(name);
		}
		return out;
	}

	protected User getCurrentUser(HttpServletRequest request) {
		return (User) request.getSession().getAttribute("User");
	}
}
