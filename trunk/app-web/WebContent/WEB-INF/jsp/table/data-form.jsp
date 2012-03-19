<%@page import="cl.buildersoft.framework.util.BSWeb"%>
<%@page import="cl.buildersoft.framework.type.BSFieldType"%>
<%@page import="cl.buildersoft.framework.beans.BSField"%>
<%@page import="cl.buildersoft.framework.beans.BSTableConfig"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<%
	String nextServlet = (String) request.getAttribute("Action");
	if ("insert".equalsIgnoreCase(nextServlet)) {
		nextServlet = "InsertRecord";
	} else {
		nextServlet = "UpdateRecord";
	}
%>

<form
	action="${pageContext.request.contextPath}/servlet/table/<%=nextServlet%>"
	method="post" id="editForm">
	<table>
		<%
			for (BSField field : fields) {
		%>
		<tr>
			<td class="cLabel" valign='top'><%=field.getLabel()%>:</td>
			<td class="cData"><%=writeHTMLField(field, request)%></td>
		</tr>
		<%
			}
		%>
	</table>
</form>
<input type="button" value="Aceptar"
	onclick="javascript:$('#editForm').submit();">
&nbsp;&nbsp;&nbsp;
<a href="${pageContext.request.contextPath}/servlet/table/LoadTable">Cancelar</a>


<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>

<%!private String writeHTMLField(BSField field, HttpServletRequest request) {
		String out = "";
		BSFieldType type = field.getType();
		Object value = field.getValue();
		Integer maxlength = 0;
		String name = field.getName();
		String format = "";
		Integer size = 0;
		String afterInput = "";
		Boolean isPk = field.isPk();
		Boolean isReadOnly = isPk ? Boolean.TRUE : field.isReadonly();

		if (type.equals(BSFieldType.Boolean)) {
			out += "<SELECT name='" + name + "' ";
			out += isReadOnly ? " DISABLED " : "";
			out += ">";

			out += writeOptionHTML("true", "Si", value);
			out += writeOptionHTML("false", "No", value);

			out += "</SELECT>";
		} else {
			if (type.equals(BSFieldType.String)) {
				value = value == null ? "" : value;
				maxlength = field.getLength();
				size = maxlength;
				if (size > 75) {
					size = 75;
				}
			} else if (type.equals(BSFieldType.Date)) {
				maxlength = 10;
				format = BSWeb.getFormatDate(request);
				value = BSWeb.date2String(value, format);
				size = maxlength;
				afterInput = "(formato: " + format + ")";
			} else if (type.equals(BSFieldType.Datetime)) {
				maxlength = 16;
				format = BSWeb.getFormatDatetime(request);
				value = BSWeb.date2String(value, format);
				size = maxlength;
				afterInput = "(formato: " + format + ")";
			} else if (type.equals(BSFieldType.Double)) {
				maxlength = 15;
				format = BSWeb.getFormatNumber(request);
				value = BSWeb.number2String(value, format);
				size = maxlength;
			} else if (type.equals(BSFieldType.Integer)) {
				maxlength = 8;
				format = BSWeb.getFormatNumber(request);
				value = BSWeb.number2String(value, format);
				size = maxlength;
			} else if (type.equals(BSFieldType.Long)) {
				maxlength = 10;
				format = BSWeb.getFormatNumber(request);
				if (isPk && value == null) {
					value = "[Nuevo]";
				} else {
					value = value == null ? "" : BSWeb.number2String(value,
							format);
				}
				size = maxlength;
			}

			out += drawInputText("text", name, maxlength, isReadOnly, value,
					size, afterInput);
		}
		return out;
	}

	private String writeOptionHTML(String option, String display, Object value) {
		String out = "<OPTION value='" + option + "'";
		out += (value != null && value.toString().equals(option)
				? " selected"
				: "");
		out += ">" + display + "</OPTION>";
		return out;
	}

	/**<code>
	private String passwordField(BSField field) {
		String out = "<table>";
		out += "<tr><td>";
		out += drawInputText("password", field.getName() + "1", 15, false, "",
				15, "");
		out += "</td></tr><tr><td>";
		out += drawInputText("password", field.getName() + "2", 15, false, "",
				15, "(confirme clave)");
		out += "</td></tr></table>";

		return out;
	}
</code>*/
	private String drawInputText(String type, String name, Integer maxlength,
			Boolean isReadonly, Object value, Integer size, String afterInput) {
		String out = "<input type='" + type + "' name='";
		out += name;
		out += "' ";
		out += "maxlength='" + maxlength + "' ";
		out += isReadonly ? "READONLY " : "";
		out += "value='" + value + "' ";
		out += "size='" + size + "px'";
		out += ">&nbsp;" + afterInput;
		return out;
	}%>
