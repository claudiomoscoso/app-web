<%@page import="cl.buildersoft.lib.beans.Option"%>
<%@page import="cl.buildersoft.lib.beans.Submenu"%>
<%@page import="cl.buildersoft.lib.beans.Menu"%>
<%@page import="java.util.List"%>
<%@page import="cl.buildersoft.lib.util.BSWeb"%>

<div class="row-fluid">
<div class="span12">
	<div class="navbar navbar-inverse">
		<div class="navbar-inner">
			 
				<!-- Boton para moviles -->
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-tasks icon-white" />
				</a>

				<div class="nav-collapse collapse">
					<ul class="nav">
						<li><a href="${pageContext.request.contextPath}/servlet/Home?<%=BSWeb.randomString()%>">Inicio</a></li>

						<%=write_menu_in_menu_jsp(session, request)%>

						<li><a href="${pageContext.request.contextPath}/jsp/login/logout.jsp?<%=BSWeb.randomString()%>">Salir</a></li>
					</ul>
				</div>
			 
		</div>
	</div>
	</div>
</div>
</header>

<!-- 
<ul class="jd_menu" style="z-index: 1">
	<li><a href="${pageContext.request.contextPath}/servlet/Home">Inicio</a></li>
	< % =write_menu_in_menu_jsp(session, request) % >
	<li><a href="${pageContext.request.contextPath}/jsp/login/logout.jsp">Salir</a></li>
</ul>
-->

<!--  
		<div class="row-fluid">
				<div class="navbar navbar-inverse">
					<div class="navbar-inner">
						<div class="container-fluid">
							<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span> <span
								class="icon-bar"></span> <span class="icon-bar"></span>
							</a>
							<div class="nav-collapse collapse">

								<ul class="nav">
									<li class="active"><a href="#"><i class="icon-home icon-white"></i> Home</a></li>
-->

<!-- 
<ul class="nav pull-right">
  <li class="dropdown">
<ul class="dropdown-menu">
	<li><a href="#">Home</a></li>
	<li><a href="#">Tutorials</a></li>
	<li><a href="#">Practice Editor </a></li>
	<li><a href="#">Gallery</a></li>
	<li><a href="#">Contact</a>
		<ul class="dropdown-menu">
			<li><a href="#">0</a></li>
			<li><a href="#">1</a></li>
			<li><a href="#">2 </a></li>
			<li><a href="#">3</a></li>
			<li><a href="#">4</a></li>
		</ul></li>
</ul>
</li></ul>
 -->



<%!private String write_menu_in_menu_jsp(HttpSession session, HttpServletRequest request) {
		Menu menuUser = (Menu) session.getAttribute("Menu");
		String out = "";
		//Boolean haveMore = null;
		if (menuUser != null) {
			String ctxPath = request.getContextPath();
			List<Submenu> main = menuUser.list();
			Option opt = null;
			String url = null;
			String label = null;
			for (Submenu submenu : main) {
				opt = submenu.getOption();
				out += "<li" + (submenu.list().size() > 0 ? " class='dropdown'" : "") + ">";
				out += option2String(opt, ctxPath, true);
				out += writeSubMenu(submenu, ctxPath);
				out += "</li>\n";
			}
		}
		return out;
	}

	private String option2String(Option opt, String contextPath, Boolean isRoot) {
		String out = "";
		String url = opt.getUrl();
		String label = opt.getLabel();
		//String urlPath = "";
		String endTag = "</a>";
		//String startTag = "";

		//System.out.println("url: "+ url);

		url = url == null ? "" : url;

		if (url.length() > 0) {
			out = "<a ";
			if (url.startsWith("/")) {
				url = contextPath + url;
			}
			//out += startTag;
			out += "href='" + url + "?" + BSWeb.randomString() + "'";
			//endTag = "</a>";

		} else {
			out = "<a href=\"#\" ";
		}

		if (isRoot) {
			out += " class=\'dropdown-toggle\' data-toggle=\'dropdown\'";
		}
		out += ">";

		out += label +  (isRoot? "<b class='caret'></b>":"")+ endTag;
		return out;
	}

	private String writeSubMenu(Submenu menu, String contextPath) {
		Option opt = null;
		String url = null;
		String label = null;
		List<Submenu> menuList = menu.list();
		Integer count = menuList.size();

		String out = count > 0 ? "\n<ul class='dropdown-menu'>" : "";

		for (Submenu submenu : menuList) {
			out += "<li>";
			out += option2String(submenu.getOption(), contextPath, false);
			//out += writeSubMenu(submenu, contextPath);
			out += "</li>\n";
		}
		out += count > 0 ? "</ul>\n" : "\n";
		return out;
	}%>

