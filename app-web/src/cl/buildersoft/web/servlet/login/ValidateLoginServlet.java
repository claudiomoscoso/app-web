package cl.buildersoft.web.servlet.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.buildersoft.lib.beans.Domain;
import cl.buildersoft.lib.beans.DomainAttribute;
import cl.buildersoft.lib.beans.Rol;
import cl.buildersoft.lib.beans.User;
import cl.buildersoft.lib.database.BSBeanUtils;
import cl.buildersoft.lib.database.BSmySQL;
import cl.buildersoft.lib.exception.BSDataBaseException;
import cl.buildersoft.lib.exception.BSUserException;
import cl.buildersoft.lib.services.BSUserService;
import cl.buildersoft.lib.services.impl.BSUserServiceImpl;
import cl.buildersoft.lib.util.BSConfig;
import cl.buildersoft.lib.util.BSDataUtils;
import cl.buildersoft.sso.filter.BSHttpServletSSO;

/**
 * Servlet implementation class ValidateServlet
 */

@WebServlet(urlPatterns = "/login/ValidateLoginServlet")
public class ValidateLoginServlet extends BSHttpServletSSO {
	private static final long serialVersionUID = -4481703270849068766L;

	public ValidateLoginServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mail = request.getParameter("mail");
		String password = request.getParameter("password");
		String page = "/";
		ServletContext ctx = request.getServletContext();
		String dataSource = (String) ctx.getAttribute("bsframework.datasource");

		mail = "".equals(mail) ? null : mail;
		password = "".equals(password) ? null : password;
		if (mail != null && password != null) {
			BSUserService userService = new BSUserServiceImpl();
			BSDataUtils dau = new BSDataUtils();

			User user = null;
			List<Rol> rols = null;
			Connection connDomain = null;

			Connection connBSframework = dau.getConnection2(ctx, dataSource);
			user = userService.login(connBSframework, mail, password);

			List<Domain> domains = null;
			Domain defaultDomain = null;
			Map<String, DomainAttribute> domainAttributes = null;
			if (user != null) {
				domains = getDomains(connBSframework, user);
				if (domains.size() == 0) {
					throw new BSUserException("", "El usuario '" + user.getMail() + "' no esta completamente configurado");
				}
				defaultDomain = domains.get(0);
				domainAttributes = getDomainAttributes(connBSframework, defaultDomain);
				connDomain = dau.getConnection2(ctx, domainAttributes.get("datasource").getValue());

				rols = userService.getRols(connDomain, user);
				if (rols.size() == 0) {
					throw new BSUserException("0001", "Usuario no tiene roles configurados");
				}
			}

			if (user != null) {
				HttpSession session = createSession(request, response);
				BSConfig config = new BSConfig();
				Boolean useBootstrap = config.getBoolean(connBSframework, "USE_BOOTSTRAP");

				synchronized (session) {
					session.setAttribute("User", user);
					session.setAttribute("Rol", rols);
					session.setAttribute("Menu", true);
					session.setAttribute("Domains", domains);
					session.setAttribute("Domain", defaultDomain);
					session.setAttribute("DomainAttribute", domainAttributes);
					session.setAttribute("UseBootrstap", useBootstrap);
					session.setAttribute("CurrentDatasource", domainAttributes.get("datasource").getValue());
				}
//				Cookie cookie = saveCookieToResponse(response, null);

				page = "/servlet/login/GetMenuServlet";
			} else {
				page = "/WEB-INF/jsp/login/not-found.jsp";
			}
		}
		// request.getRequestDispatcher(page).forward(request, response);
		super.fordward(request, response, page);
	}

	private Map<String, DomainAttribute> getDomainAttributes(Connection conn, Domain defaultDomain) {
		BSmySQL mysql = new BSmySQL();
		ResultSet rs = mysql.callSingleSP(conn, "pListDomainAttributes", defaultDomain.getId());

		BSBeanUtils bu = new BSBeanUtils();
		Map<String, DomainAttribute> out = new HashMap<String, DomainAttribute>();

		DomainAttribute domainAttribute = null;
		try {
			while (rs.next()) {
				domainAttribute = new DomainAttribute();
				domainAttribute.setId(rs.getLong("cId"));

				bu.search(conn, domainAttribute);

				out.put(domainAttribute.getKey(), domainAttribute);
			}
			mysql.closeSQL(rs);
		} catch (SQLException e) {
			throw new BSDataBaseException(e);
		}

		return out;
	}

	private List<Domain> getDomains(Connection conn, User user) {
		BSmySQL mysql = new BSmySQL();

		ResultSet rs = mysql.callSingleSP(conn, "pListDomainsForUser", user.getId());

		BSBeanUtils bu = new BSBeanUtils();
		List<Domain> out = new ArrayList<Domain>();
		Domain domain = null;
		try {
			while (rs.next()) {
				domain = new Domain();
				domain.setId(rs.getLong("cId"));
				bu.search(conn, domain);
				out.add(domain);
			}
			mysql.closeSQL(rs);
		} catch (SQLException e) {
			throw new BSDataBaseException("1000", e.getMessage());
		}

		return out;
	}
}
