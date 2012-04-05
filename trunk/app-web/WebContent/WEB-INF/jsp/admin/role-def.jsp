<%@page import="cl.buildersoft.framework.beans.BSTableConfig"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>
<%
	List<Object[]> rols = (List<Object[]>) request.getAttribute("Rols");
	//Long id = Long.parseLong(request.getParameter("cId"));

	Menu menuAux = (Menu) request.getAttribute("FullMenu");
	List<Submenu> fullMenu = menuAux.list();
	menuAux = (Menu) request.getAttribute("RolMenu");
	List<Submenu> rolMenu = menuAux.list();
%>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/admin/role-def.js"></script>

<h1 class="cTitle">Definición de Roles</h1>
 
<form action="${pageContext.request.contextPath}/servlet/admin/SaveRoleDef" id="frm" method="post">
<!--  
<form action="${pageContext.request.contextPath}/servlet/ShowParameters" method="post">
 -->
	<table border="0" width="50%">
		<tr>
			<td class="cLabel">Roles:</td>
			<td><select name="Rol" onchange="javascript:changeSelect(this);">
					<%
						for (Object[] row : rols) {
					%>
					<option value="<%=row[0]%>" <%=getSelected(row, request)%>><%=row[1]%></option>
					<%
						}
					%>
			</select></td>
		</tr>
		<tr>
			<td colspan="2">
				<ul>
					<%=write(fullMenu, rolMenu)%>
				</ul>

			</td>
		</tr>
	</table>
	<input type="submit" value="Grabar">
</form>

<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>

<%!private String getSelected(Object[] row, HttpServletRequest request) {
		Long id = (Long) request.getAttribute("cId");
		return ((Long)row[0]).equals(id) ? "selected" : "";
	}

	private String write(List<Submenu> fullMenu, List<Submenu> rolMenu) {
		String out = "";
		Option option = null;
		for (Submenu menu : fullMenu) {
			option = menu.getOption();
			out += "<li class='cLabel' type='none'>"
					+ drowCheckbox(option, rolMenu)
					+ menu.getOption().getLabel() + "</li>";

			out += writeSubOption(menu, rolMenu);
		}

		return out;
	}

	private String drowCheckbox(Option option, List<Submenu> rolMenu) {
		String out = "<input type='checkbox' ";
		out += "value='" + option.getId() + "' ";
		out += "name='Option' ";
		out += getChecked(option, rolMenu);
		out += ">";
		return out;
	}

	private String getChecked(Option option, List<Submenu> rolMenu) {
		Boolean isChecked = isChecked(option, rolMenu);
		return isChecked ? "checked " : "";
	}

	private Boolean isChecked(Option option, List<Submenu> main) {
		Boolean out = Boolean.FALSE;

		for (Submenu sub : main) {
			if (sub.getOption().getId().equals(option.getId())) {
				out = Boolean.TRUE;
				break;
			} else {
				out = isChecked(option, sub.list());
				if (out) {
					break;
				}
			}
		}

		return out;
	}

	private String writeSubOption(Submenu menu, List<Submenu> rolMenu) {
		String out = "";
		List<Submenu> main = menu.list();
		if (main.size() > 0) {
			out += "<ul>";
			Option option = null;
			for (Submenu sub : main) {
				option = sub.getOption();
				out += "<li class='cLabel' type='none'>"
						+ drowCheckbox(option, rolMenu) + option.getLabel()
						+ "</li>";
				out += writeSubOption(sub, rolMenu);
			}
			out += "</ul>";
		}
		return out;
	}%>