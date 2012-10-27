<%@page import="cl.buildersoft.framework.beans.Domain"%>
<%@page import="cl.buildersoft.framework.beans.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<LINK rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/default.css?<%=Math.random() %>" />
<LINK rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/jdMenu.css?<%=Math.random() %>" />

<title>Buildersoft &radic;</title>
<script>
	var contextPath = "${pageContext.request.contextPath}";
	var speed = "fast";// "slow";
</script>
<script
	src="${pageContext.request.contextPath}/js/common/framework.js?<%=Math.random() %>"></script>

<script
	src="${pageContext.request.contextPath}/js/common/jquery-1.7.1.js"></script>

<script
	src="${pageContext.request.contextPath}/js/common/jdMenu/jquery.dimensions.js"></script>
<script
	src="${pageContext.request.contextPath}/js/common/jdMenu/jquery.jdMenu.js"></script>
<script
	src="${pageContext.request.contextPath}/js/common/jdMenu/jquery.jdMenu.packed.js"></script>

<script type="text/javascript">
	$(function() {
		$('ul.jd_menu').jdMenu();
	});
</script>
</head>

<body marginwidth="25px" marginheight="5px"
	onload="javaScript:loadFormat();try{onLoadPage();}catch(e){}">
	<!-- 
loadFormat();
 -->
	<div id="TOOLTIP_CONTAINER"
		style="display:none;position:absolute;top:0;left:0;height:0;width:0;background-color:#C3C3C2;opacity:0.5;z-index:1;"
		align="center">
	</div>

	<div id="TOOLTIP_CONTENT"
		style="display: none; z-index: 2; position: absolute;" align="center">
		<br /> <br />
		<table border="0"
			style="background-color: white; border: #000000 solid" width="60%"
			height="50%">
			<tr>
				<td align="center" width="10">&nbsp;</td>
				<td>&nbsp;</td>
				<td align="center" width="10"><a class="cLabel" style="cursor:pointer"
					href="javascript:closeTooltip()"
					onmousemove="javascript:window.status='Cerrar'"> (X) </a></td>
			</tr>
			<tr>
				<td align="center" width="10">&nbsp;</td>
				<td height="90%" align="center" valign="top"
					id="TOOLTIP_CELL_CONTAINER"></td>
				<td align="center" width="10">&nbsp;</td>
			</tr>
			<tr>
				<td align="center" width="10">&nbsp;</td>
				<td>&nbsp;</td>
				<td align="center" width="10">&nbsp;</td>
			</tr>
		</table>
	</div>


	<table border="0" style="width: 100%">
		<tr>
			<td align="right"><span class="cLabel">Dominio:</span><span
				class="cData">&nbsp;<%=getDomainName(session)%></span>&nbsp;&nbsp;|&nbsp;&nbsp;
				<span class="cLabel">Usuario:</span><span class="cData">&nbsp;<%=getUserName(session)%>
					- <%=getUserMail(session)%></span></td>
		<tr>
			<td valign="top">
			
			
			
			
			
			
			
			
			
			
			
	<%!private String getDomainName(HttpSession session) {
		return ((Domain) session.getAttribute("Domain")).getAlias();
	}

	private String getUserName(HttpSession session) {
		return ((User) session.getAttribute("User")).getName();
	}

	private String getUserMail(HttpSession session) {
		return ((User) session.getAttribute("User")).getMail();
	}
%>