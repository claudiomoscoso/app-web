<%@page import="cl.buildersoft.framework.util.BSPaging"%>
<%
	BSPaging paging = (BSPaging) request.getAttribute("Paging");
%>

<br>
<div class="cLabel" align="center"><%=write_pagination_jsp(ctxPath, paging)%></div>
<script type="text/javascript">
<!--
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

<%!private String write_pagination_jsp(String ctxPath, BSPaging paging) {
		String out = "";
		//		BSPaging paging = (BSPaging) request.getAttribute("Paging");

		if (paging != null && paging.getRequiresPaging()) {
			String s = spaces();
			Integer currentPage = paging.getCurrentPage();
			out = "";
			if (paging.getCurrentPage() > 1) {
				out += linkToPage(ctxPath, 1, "First") + s;
				out += linkToPage(ctxPath, paging.getCurrentPage() - 1, "Prev")
						+ s;
			} else {

			}
			out += "Página" + s;
			//onkeypress='javascript:keyPressPaging(this)'
			out += "<input size='2' type='text' onkeypress='return keyPressPaging(this);' value='"
					+ currentPage + "'>" + s;
			out += " de " + s;
			out += paging.getPageCount() + s;

			if (paging.getCurrentPage() < paging.getPageCount()) {
				out += linkToPage(ctxPath, currentPage + 1, "Next") + s;
				out += linkToPage(ctxPath, paging.getPageCount(), "Last");
			}
		}
		return out;
	}

	private String linkToPage(String ctxPath, Integer page, String type) {
		String out = "<a href='?Page=" + page + "'><img height='15px' src='"
				+ ctxPath + "/img/common/button_page" + type + ".jpg'></a>";

		return out;
	}

	private String spaces() {
		return "&nbsp;&nbsp;&nbsp;";
	}%>