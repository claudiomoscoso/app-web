<%@page import="cl.buildersoft.framework.util.BSWeb"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.getSession().invalidate();
%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<link
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css"
	rel="stylesheet">

<!--      
<LINK rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/default.css?<%=Math.random()%>" />
 -->

<style type="text/css">
/*
*      body {
*        padding-top: 40px;
*        padding-bottom: 40px;
*       background-color: #f5f5f5;
*      }
*/
.form-signin {
	max-width: 300px;
	padding: 19px 29px 29px;
	margin: 0 auto 20px;
	background-color: #fff;
	border: 1px solid #e5e5e5;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
}

.form-signin .form-signin-heading,.form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin input[type="text"],.form-signin input[type="password"] {
	font-size: 16px;
	height: auto;
	margin-bottom: 15px;
	padding: 7px 9px;
}
</style>
<title>Ingreso de usuario</title>
<!-- 
<script>
	function posImage() {
		return;
		var img = document.getElementById("html5");
		img.style.position= 'absolute';
		img.style.pixelTop = (screen.height-300);
	}
</<script>
-->
</head>
<body>

	<div class="container">

		<div class="row">&nbsp;</div>
		<div class="row">&nbsp;</div>

		<form class="form-signin"
			action="${pageContext.request.contextPath}/login/ValidateLoginServlet?<%=BSWeb.randomString() %>"
			method="post">
			<h2 class="form-signin-heading">Buildersoft</h2>

			<input type="text" name="mail" class="input-block-level"
				placeholder="Email address"> <input type="password"
				name="password" class="input-block-level" placeholder="Password">

			<button class="btn btn-large btn-primary" type="submit">Acceder...</button>

			<!-- 
		<span class="cLabel">Mail:</span> <input type="text" name="mail" required
			placeholder="Correo personal"><br> <span class="cLabel">Clave:
		</span> <input type="password" required placeholder="Clave de acceso" name="password"><br>

		<button type="submit">Acceder...</button>
		 -->
		</form>

	</div>
	<script src="${pageContext.request.contextPath}/bootstrap/js/jquery.js"></script>
	<script
		src="${pageContext.request.contextPath}/bootstrap/js/bootstrap-transition.js"></script>
	<script
		src="${pageContext.request.contextPath}/bootstrap/js/bootstrap-alert.js"></script>
	<script
		src="${pageContext.request.contextPath}/bootstrap/js/bootstrap-modal.js"></script>
	<script
		src="${pageContext.request.contextPath}/bootstrap/js/bootstrap-dropdown.js"></script>
	<script
		src="${pageContext.request.contextPath}/bootstrap/js/bootstrap-scrollspy.js"></script>
	<script
		src="${pageContext.request.contextPath}/bootstrap/js/bootstrap-tab.js"></script>
	<script
		src="${pageContext.request.contextPath}/bootstrap/js/bootstrap-tooltip.js"></script>
	<script
		src="${pageContext.request.contextPath}/bootstrap/js/bootstrap-popover.js"></script>
	<script
		src="${pageContext.request.contextPath}/bootstrap/js/bootstrap-button.js"></script>
	<script
		src="${pageContext.request.contextPath}/bootstrap/js/bootstrap-collapse.js"></script>
	<script
		src="${pageContext.request.contextPath}/bootstrap/js/bootstrap-carousel.js"></script>
	<script
		src="${pageContext.request.contextPath}/bootstrap/js/bootstrap-typeahead.js"></script>
</body>

</html>