<%@page import="cl.buildersoft.lib.dataType.BSDataType"%>
<%@page import="cl.buildersoft.lib.util.crud.BSField"%>
<%@page import="cl.buildersoft.lib.util.crud.BSTableConfig"%>
<%@page import="java.sql.ResultSet"%>
<%
	BSTableConfig table = (BSTableConfig) session
	.getAttribute("BSTable");
	BSField[] fields = table.getFields();
%>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>
<h1 class="cTitle">Creacion de información</h1>
<script>
	function onLoadPage() {

	}
</script>

<form action="${pageContext.request.contextPath}/admin/InsertRecord"
	method="post" id="editForm">
	<table>
		<%
			for (BSField field : fields) {
		%>
		<tr>
			<%
				if (field.isPK()) {
			%>
			<td class="cLabel"><%=field.getLabel()%>:</td>
			<td class="cData">[Nuevo]</td>
			<%
				} else {
			%>
			<td class="cLabel"><%=field.getLabel()%>:</td>
			<td class="cData"><%=writeHTMLField(field)%></td>
		</tr>
		<%
			}
			}
		%>
	</table>
</form>
<input type="button" value="Aceptar"
	onclick="javascript:$('#editForm').submit();">
&nbsp;&nbsp;&nbsp;
<a href="${pageContext.request.contextPath}/admin/LoadTable">Cancelar</a>


<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>

<%!private String writeHTMLField(BSField field) {
		String out = "";
		Integer len = 8;
		Object value = field.getValue();

		if (field.getType().equals(BSDataType.STRING)) {
			len = field.getLength();
			out += "<input type='text' name=' ";
			out += field.getName();
			out += "' ";

			out += "maxlength='" + len + "' ";
			Integer size = len;
			if (size > 75) {
				size = 75;
			}
			out += "size='" + size + "px' ";
			if (value != null) {
				out += "value='" + value + "' ";
			}
			out += ">";
		} else if (field.getType().equals(BSDataType.BOOLEAN)) {
			out += "<SELECT name='" + field.getName() + "'>";

			out += writeOptionHTML("true", "Si", value);
			out += writeOptionHTML("false", "No", value);

			out += "</SELECT>";
		} else if (field.getType().equals(BSDataType.DATE)) {
			len = 10;
			out += "<input type='text' name='";
			out += field.getName();
			out += "' ";
			out += "maxlength='" + len + "'";
			out += " size='" + len + "px'";
			out += "> (formato: dd/mm/aaaa)";
		} else if (field.getType().equals(BSDataType.TIMESTAMP)) {
			len = 16;
			out += "<input type='text' name='";
			out += field.getName();
			out += "' ";
			out += "maxlength='" + len + "'";
			out += " size='" + len + "px'";
			out += "> (formato: dd/mm/aaaa hh:mm)";
		} else if (field.getType().equals(BSDataType.DOUBLE)) {
			len = 15;
			out += "<input type='text' name='";
			out += field.getName();
			out += "' ";
			out += "maxlength='" + len + "'";
			out += " size='" + len + "px'";
			out += ">";
		} else if (field.getType().equals(BSDataType.INTEGER)) {
			len = 8;
			out += "<input type='text' name='";
			out += field.getName();
			out += "' ";
			out += "maxlength='" + len + "'";
			out += " size='" + len + "px'";
			out += ">";
		}

		return out;
	}

	private String writeOptionHTML(String option, String display, Object value) {
		String out = "";
		out += "<OPTION value='" + option + "'";
		out += (value != null && value.equals(option) ? "selected" : "");
		out += ">" + display + "</OPTION>";
		return out;
	}%>
