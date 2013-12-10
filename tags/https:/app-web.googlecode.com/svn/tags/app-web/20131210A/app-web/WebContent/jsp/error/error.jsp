<%@page import="cl.buildersoft.framework.exception.BSSystemException"%>
<%@page
	import="cl.buildersoft.framework.exception.BSProgrammerException"%>
<%@page import="cl.buildersoft.framework.exception.BSDataBaseException"%>
<%@page
	import="cl.buildersoft.framework.exception.BSConfigurationException"%>
<%@page import="cl.buildersoft.framework.exception.BSUserException"%>
<%@page import="cl.buildersoft.framework.exception.BSException"%>
<%@page import="java.util.Properties"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.net.URL"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true" import="java.io.PrintWriter"%>

<%
	ServletContext ctx = request.getServletContext();
	InputStream in = ctx.getResourceAsStream("/WEB-INF/errors.properties");
	Properties properties = new Properties();
	properties.load(in);

	String title = "";
	String desc = "";

	if (exception instanceof BSUserException) {
		BSUserException e = (BSUserException) exception;
		title = "Error de usuario";
		desc = properties.getProperty(e.getCode());
		if (desc == null) {
	desc = e.getMessage();
		}
	} else if (exception instanceof BSConfigurationException) {
		BSConfigurationException e = (BSConfigurationException) exception;
		title = "Error de Configuración";
		desc = properties.getProperty(e.getCode());
		if (desc == null) {
	desc = e.getMessage();
		}

	} else if (exception instanceof BSDataBaseException) {
		BSDataBaseException e = (BSDataBaseException) exception;
		title = "Error en Base de Datos";
		desc = properties.getProperty(e.getCode());
		if (desc == null) {
	desc = e.getMessage();
		}

	} else if (exception instanceof BSProgrammerException) {
		BSProgrammerException e = (BSProgrammerException) exception;
		title = "Error de programación";
		desc = properties.getProperty(e.getCode());
		if (desc == null) {
	desc = e.getMessage();
		}

	} else if (exception instanceof BSSystemException) {
		BSSystemException e = (BSSystemException) exception;
		title = "Error de sistema";
		desc = properties.getProperty(e.getCode());
		if (desc == null) {
	desc = e.getMessage();
		}

	} else if (exception instanceof RuntimeException) {
		RuntimeException e = (RuntimeException) exception;
		title = "Error";
		desc = e.getMessage();
	} else {
		Throwable root = exception.getCause();
		title = "Ha ocurrido un error desconocido";
		if(root!=null){
		desc = root.getMessage();}
	}
%>

<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>
<script>
	var showed = false;
	function showHideInfo(o) {
		if (showed) {
			$(document.getElementById('detail')).fadeOut(speed);
			o.innerHTML = 'Detalles >>';
		} else {
			$(document.getElementById('detail')).fadeIn(speed);
			o.innerHTML = 'Ocultar <<';
		}
		showed = !showed;
	}
</script>
<h1 class="cTitle" style="color: red"><%=title%></h1>
<pre>
  <%=desc%>
  </pre>

<a href="#" onclick='javascript:showHideInfo(this)' style='cursor:pointer' class="cError">Detalles
	>></a>
<div style="display: none" id="detail">
	<br> <span class="cError"> <%
 	for (StackTraceElement se : exception.getStackTrace()) {
 %> <%=se.toString()%><br> <%
 	}
 %>
	</span>
</div>


<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>