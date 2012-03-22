<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>
<%
	Boolean passwordIsNull = (Boolean) request
			.getAttribute("PASS_IS_NULL");
	String id = request.getParameter("cId");
%>

<h1 class="cTitle">Cambio de clave</h1>
<!-- 
action="${pageContext.request.contextPath}/servlet/changePassword/ChangePassword"
action="${pageContext.request.contextPath}/servlet/ShowParameters"
 -->
<form
	action="${pageContext.request.contextPath}/servlet/changePassword/ChangePassword"
	method="post">
	<input type="hidden" name="cId" value="<%=id%>">
	<table>
		<%
			if (!passwordIsNull) {
		%>
		<tr>
			<td class="cLabel">Clave anterior:</td>
			<td><input type="password" name="OldPassword"></td>
		</tr>
		<%
			}
		%>
		<tr>
			<td class="cLabel">Nueva clave:</td>
			<td><input type="password" name="NewPassword"></td>
		</tr>
		<tr>
			<td class="cLabel">Confirme clave:</td>
			<td><input type="password" name="CommitPassword"></td>
		</tr>
	</table>
	<input type="submit" value="Confirmar"> <a
		href="${pageContext.request.contextPath}/servlet/admin/UserManager">Cancelar</a>
</form>

<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>

