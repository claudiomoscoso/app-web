<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>

<h1 class="cTitle">Contenido...</h1>

<form action="${pageContext.request.contextPath}/servlet/ShowParameters">
	<input type="radio" name="sex" value="male" /> Male<br /> 
	<input type="radio" name="sex" value="female" /> Female<br />
		 <input type="submit">
</form>

<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>

