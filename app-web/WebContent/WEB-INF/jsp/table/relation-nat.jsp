<%@page import="cl.buildersoft.framework.beans.BSTableConfig"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>
<%
	ResultSet relation = (ResultSet) request.getAttribute("Relation");
	ResultSet list = (ResultSet) request.getAttribute("List");

	BSTableConfig table = (BSTableConfig) session
			.getAttribute("BSTable");
%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/table/relation.js?<%=Math.random()%>"></script>

<h1 class="cTitle"><%=table.getTitle()%></h1>

<table border="1">
	<tr>
		<td style="width: 30%"><span class="cLabel" align="center">Disponibles</span><br>
			<select SIZE="10" id="left" style="width: 100%">
				<%
					while (list.next()) {
				%>
				<option value="<%=list.getString(1)%>"><%=list.getString(2)%></option>
				<%
					}
					list.close();
				%>
		</select></td>
		<td style="width: 10%" align="center"><input type="button"
			value="->" onclick="javascript:add();" style="width: 100%"><br>
		<br>
		<br> <input type="button" value="<-"
			onclick="javascript:remove();" style="width: 100%"></td>
		<td style="width: 30%"><span class="cLabel" align="center">Seleccionados</span><br>
			<select SIZE="10" id="right" style="width: 100%">
				<%
					while (relation.next()) {
				%>
				<option value="<%=relation.getString(1)%>"><%=relation.getString(2)%></option>
				<%
					}
					relation.close();
				%>
		</select></td>
	</tr>
	<tr>
		<td align="center"><input type="button" value="Aceptar"></td>
		<td>&nbsp;</td>
		<td align="center"><a href="#">Cancelar</a></td>
	</tr>
</table>

<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>

