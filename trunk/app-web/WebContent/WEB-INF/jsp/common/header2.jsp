<%@page import="cl.buildersoft.lib.beans.User"%>
<%@page import="cl.buildersoft.lib.beans.Domain"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Buildersoft &radic;</title>
<!-- Mobile viewport optimized -->
<meta name="viewport" content="width=device-width">
<!-- CSS -->
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css?<%=Math.random() %>" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css?<%=Math.random() %>" rel="stylesheet"
	type="text/css" />

<style type="text/css">
/*
*      body {
*        padding-top: 40px;
*        padding-bottom: 40px;
*       background-color: #f5f5f5;
*      }
*/
.border {
	border: 1px solid #e5e5e5;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
}
</style>

<script>
	var contextPath = "${pageContext.request.contextPath}";
	var speed = "slow";// "slow", "fast";
</script>
</head>
<body>
	<div class="container-fluid">

		<div class="row-fluid">
			<div class="span6">
				<img src="${pageContext.request.contextPath}/img/logo.jpg" />
			</div>

			<div class="span6 text-right">
				Dominio:&nbsp;<strong><%=getDomainName(session)%></strong>&nbsp;|&nbsp;Usuario:&nbsp;<strong><%=getUserName(session)%>
					- <%=getUserMail(session)%></strong>
			</div>
		</div>
	</div>

	<%!private String getDomainName(HttpSession session) {
		return ((Domain) session.getAttribute("Domain")).getAlias();
	}

	private String getUserName(HttpSession session) {
		return ((User) session.getAttribute("User")).getName();
	}

	private String getUserMail(HttpSession session) {
		return ((User) session.getAttribute("User")).getMail();
	}%>