<%@page import="cl.buildersoft.framework.util.BSPaging"%>
<%
	BSPaging paging = (BSPaging) request.getAttribute("Paging");
	String uri = table.getUri();
%>
<div align="right" width="100%" class="cLabel">
Busqueda:<input name="Search" size='20' type='text'
	onkeypress='return keyPressSearch(this);' value='<%=request.getAttribute("Search")%>'>
</div>
<script type="text/javascript">
	function keyPressSearch(o) {
		var out = true;

		var key = window.event.keyCode;
		/**
		if (key >= 48 && key <= 57) {
			out = true;
		}
*/
		if (key == '13') {
			var url = "<%=ctxPath + uri%>?Search=" + o.value + "&Page=" + $("#CurrentPage").val();
//			alert(url);
			self.location.href = url;
			out = false;
		}
		return out;
	}
//-->
</script>

