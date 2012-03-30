<%@page import="cl.buildersoft.framework.util.BSPaging"%>
<%
	//	BSPaging paging = (BSPaging) request.getAttribute("Paging");
	//	String uri = table.getUri();
%>

<br>
<div class="cLabel" align="center"><%=write_pagination_jsp(ctxPath, paging, uri, request)%></div>
<script type="text/javascript">
	function keyPressPaging(o, search) {
		var out = true;
		var key = window.event.keyCode;
		if (key >= 48 && key <= 57) {
			out = true;
		}else{
			if (key == '13') {
				var url = "<%=ctxPath + uri%>?Page=" + o.value + "&Search=" + search;
				self.location.href = url;
				out = false;
			}
		}
		return out;
	}
</script>

<%!private String write_pagination_jsp(String ctxPath, BSPaging paging,
			String uri, HttpServletRequest request) {
		String out = "";

		String search = getSearch(request);

		//		out += "+" + search + "+";

		if (paging != null && paging.getRequiresPaging()) {
			String s = spaces();
			Integer currentPage = paging.getCurrentPage();
			if (paging.getCurrentPage() > 1) {
				out += linkToPage(ctxPath, 1, "First", uri, search) + s;
				out += linkToPage(ctxPath, paging.getCurrentPage() - 1, "Prev",
						uri, search) + s;
			} else {

			}
			out += "Página" + s;
			out += "<input id='CurrentPage' size='2' type='text' onkeypress='return keyPressPaging(this, \""
					+ search + "\");' value='" + currentPage + "'>" + s;
			out += " de " + s;
			out += paging.getPageCount() + s;

			if (paging.getCurrentPage() < paging.getPageCount()) {
				out += linkToPage(ctxPath, currentPage + 1, "Next", uri, search)
						+ s;
				out += linkToPage(ctxPath, paging.getPageCount(), "Last", uri,
						search);
			}
		}
		return out;
	}

	private String linkToPage(String ctxPath, Integer page, String type,
			String uri, String search) {
		String out = "<a href='" + ctxPath + uri + "?Page=" + page + "&Search="
				+ search + "'><img height='15px' src='" + ctxPath
				+ "/img/common/button_page" + type + ".jpg'></a>";

		return out;
	}

	private String spaces() {
		return "&nbsp;&nbsp;&nbsp;";
	}

	private String getSearch(HttpServletRequest request) {
		String out = (String) request.getAttribute("Search");
		if (out == null) {
			out = "";
		} else {
			out = out.trim();
		}
		return out;
	}%>