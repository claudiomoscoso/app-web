<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>

<h1 class="cTitle">Cambio de clave</h1>

<form action="${pageContext.request.contextPath}/servlet/admin/ChangePassword" method="post">
<table>
	<tr>
		<td class="cLabel">Clave anterior:</td>
		<td><input type="password" name="OldPassword"></td>
	</tr>
	<tr>
		<td class="cLabel">Nueva clave:</td>
		<td><input type="password" name="NewPassword"></td>
	</tr>
	<tr>
		<td class="cLabel">Cconfirme clave:</td>
		<td><input type="password" name="CommitPassword"></td>
	</tr>
</table>
<input type="submit" value="Confirmar">
</form>

<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>

