<%@page import="java.sql.Connection"%>
<%@page import="cl.buildersoft.framework.database.BSmySQL"%>
<%@page import="cl.buildersoft.framework.util.BSConfig"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>
<%
	BSmySQL mysql = new BSmySQL();
	Connection conn = mysql.getConnection(request.getServletContext(),
			"bsframework");
	BSConfig myConfig = new BSConfig();
	char separator = myConfig.getSeparator(conn);
	String formatDate = myConfig.getString(conn, "FORMAT_DATE");
	String formatDateTime = myConfig.getString(conn, "FORMAT_DATETIME");
%>

<h1 class="cTitle">Carga de personas</h1>

<form action="${pageContext.request.contextPath}/servlet/csv/Upload"
	enctype="multipart/form-data" method="POST" accept-charset="UTF-8">
	<input type="file" name="file1"><br> <input type="Submit"
		value="Cargar archivo"><br>
</form>
<br>
<br>
<span class="cLabel">
Considere estos formatos para los archivos:
</span>
<ul>
	<li><span class="cLabel">Separador csv: </span> <span class="cData">"<%=separator%>"</span>
	<li><span class="cLabel">Formato de fechas:</span> <span class="cData"><%=formatDate%></span>
	<li><span class="cLabel">Formato de fechas/hora:</span> <span class="cData"><%=formatDateTime%></span>
</ul>

<!-- 
<br><br><br>
<a href="http://commons.apache.org/fileupload/using.html">Help here</a>
-->
<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>

