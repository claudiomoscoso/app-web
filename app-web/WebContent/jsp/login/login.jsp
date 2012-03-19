<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	request.getSession().invalidate();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<LINK rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/default.css" />
<title>Ingreso de usuario</title>
</head>
<body>

	<form
		action="${pageContext.request.contextPath}/login/ValidateLoginServlet"
		method="post">
		<span class="cLabel">Mail:</span> <input type="text" name="mail"><br>
		<span class="cLabel">Clave: </span> <input type="password"
			name="password"> <input type="submit">
	</form>

</body>
</html>