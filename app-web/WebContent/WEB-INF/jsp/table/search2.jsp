<%@page import="cl.buildersoft.lib.util.crud.BSTableConfig"%>
<%@page import="cl.buildersoft.lib.util.crud.BSPaging"%>

<div class="row-fluid">
	<div class="span4 offset8 text-right">
		<!-- Busqueda:-->

		<form class="form-search">
			<div class="input-append">
				<%=write_input_field_for_search(request)%>
			</div>
		</form>

		<script type="text/javascript">
			function keyPressSearch(o, path, isButton) {
				//alert(o + ", " + path);
				var out = true;

				if (isButton) {
					var url = path + "?Search=" + o.value + "&Page=" + $("#CurrentPage").val();
					//			alert(url);
					self.location.href = url;
					out = false;
				} else {
					var key = window.event.keyCode;
					/**
					if (key >= 48 && key <= 57) {
						out = true;
					}
					 */
					if (key == '13') {
						var url = path + "?Search=" + o.value + "&Page=" + $("#CurrentPage").val();
						//			alert(url);
						self.location.href = url;
						out = false;
					}
				}
				return out;
			}
		//-->
		</script>
	</div>
</div>

<%!private String write_input_field_for_search(HttpServletRequest request) {
		BSTableConfig table = (BSTableConfig) request.getSession().getAttribute("BSTable");
		String uri = table.getUri();
		String ctxPath = request.getContextPath();
		String searchValue = (String) request.getAttribute("Search");

		String path = ctxPath + uri;

		//	 BSPaging paging = (BSPaging) request.getAttribute("Paging");

		String out = "<input name='Search' size='30' maxlength='50' type='search' placeholder='busqueda...' onkeypress='return keyPressSearch(this, \""
				+ path + "\");' value='" + searchValue + "'>";

		out = "<input type='search' name='Search' placeholder='busqueda...' id='searchInputText' class='search-query' value='"
				+ searchValue + "' onkeypress='return keyPressSearch(this, \"" + path + "\", false);' value='" + searchValue
				+ "'>";

		out += "<button type='button' onclick='javascript:keyPressSearch(document.getElementById(\"searchInputText\"),\"" + path
				+ "\", true);' class='btn'>Buscar</button>";

		return out;
	}%>