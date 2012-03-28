<%@page import="cl.buildersoft.framework.util.BSPaging"%>
<%
	BSPaging paging = (BSPaging) request.getAttribute("Paging");
	String uri = table.getUri();
%>

<br>
<div class="cLabel" align="center"><%=write_pagination_jsp(ctxPath, paging, uri)%></div>
<script type="text/javascript">
	function keyPressPaging(o) {
		var out = false;

		var key = window.event.keyCode;
		if (key >= 48 && key <= 57) {
			out = true;
		}

		if (key == '13') {
			self.location.href = "?Page=" + o.value;
		}
		return out;
	}
//-->
</script>

<%!private String write_pagination_jsp(String ctxPath, BSPaging paging,
			String uri) {
		String out = "";
		//		BSPaging paging = (BSPaging) request.getAttribute("Paging");

		if (paging != null && paging.getRequiresPaging()) {
			String s = spaces();
			Integer currentPage = paging.getCurrentPage();
			out = "";
			if (paging.getCurrentPage() > 1) {
				out += linkToPage(ctxPath, 1, "First", uri) + s;
				out += linkToPage(ctxPath, paging.getCurrentPage() - 1, "Prev",
						uri) + s;
			} else {

			}
			out += "Página" + s;
			//onkeypress='javascript:keyPressPaging(this)'
			out += "<input size='2' type='text' onkeypress='return keyPressPaging(this);' value='"
					+ currentPage + "'>" + s;
			out += " de " + s;
			out += paging.getPageCount() + s;

			if (paging.getCurrentPage() < paging.getPageCount()) {
				out += linkToPage(ctxPath, currentPage + 1, "Next", uri) + s;
				out += linkToPage(ctxPath, paging.getPageCount(), "Last", uri);
			}
		}
		return out;
	}

	private String linkToPage(String ctxPath, Integer page, String type,
			String uri) {
		String out = "<a href='" + ctxPath + uri + "?Page=" + page
				+ "'><img height='15px' src='" + ctxPath
				+ "/img/common/button_page" + type + ".jpg'></a>";

		return out;
	}

	private String spaces() {
		return "&nbsp;&nbsp;&nbsp;";
	}%>