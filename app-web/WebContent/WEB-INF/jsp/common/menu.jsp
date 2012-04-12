<%@ page import="cl.buildersoft.framework.beans.Menu"%>
<%@ page import="cl.buildersoft.framework.beans.Submenu"%>
<%@ page import="cl.buildersoft.framework.beans.Option"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<ul>
	<%=write_menu_in_menu_jsp(session, request)%>
</ul>

</td>
<td valign="top" width="5%" colspan="2">


<%!private String write_menu_in_menu_jsp(HttpSession session,
			HttpServletRequest request) {
		Menu menuUser = (Menu) session.getAttribute("Menu");
		String out = "";
		String ctxPath = request.getContextPath();
		List<Submenu> main = menuUser.list();
		Option opt = null;
		String url = null;
		String label = null;
		for (Submenu submenu : main) {
			opt = submenu.getOption();

			out += option2String(opt, ctxPath);
			out += writeSubMenu(submenu, ctxPath);
		}
		return out;
	}

	private String writeSubMenu(Submenu menu, String contextPath) {
		Option opt = null;
		String url = null;
		String label = null;
		String out = "<ul>";

		List<Submenu> menuList = menu.list();
		for (Submenu submenu : menuList) {
			out += option2String(submenu.getOption(), contextPath);
			out += writeSubMenu(submenu, contextPath);
		}
		out += "</ul>";
		return out;
	}

	private String option2String(Option opt, String contextPath) {
		String out = "<li>";
		String url = opt.getUrl();
		String label = opt.getLabel();
		String urlPath = "";
		String endTag = "";
		String startTag = "";

		url = url == null ? "" : url;

		if (url.length() > 0) {
			startTag = "<a ";
			if (url.startsWith("/")) {
				url = contextPath + url;
				endTag = "</a>";
			}
			out += startTag;
			out += "href=\"" + url + "\">";
			endTag = "</a>";
		} else {
			out += "<a>";
			endTag = "</a>";
		}

		out += label + endTag + "</li>";

		return out;
	}%>