<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>

<h1 class="cTitle">Lista de archivos</h1>
<!-- 
<input type="text" name="SomeObject" id="SomeObject"
onfocus="javascript:doubleFocus(this);"
onblur="javascript:doubleBlur(this);"
value="<%=BSWeb.formatDouble(request, 1234.567)%>">
 -->
<table class="cList" cellpadding="0" cellspacing="0">
	<tr>
		<td class='cHeadTD'>Archivo</td>
		<td class='cHeadTD'>Fecha de carga</td>
		<td class='cHeadTD'>Tamaño</td>
		<td class='cHeadTD'>Acciones</td>
	</tr>
	
	<tr>
		<td class='cDataTD'>file-20120901.txt</td>
		<td class='cDataTD'>01-09-2012 20:12</td>
		<td class='cDataTD'>120kb</td>
		<td class='cDataTD'><a href="#">descargar...</a></td>
	</tr>
	<tr>
		<td class='cDataTD'>file-20120902.txt</td>
		<td class='cDataTD'>02-09-2012 20:11</td>
		<td class='cDataTD'>121kb</td>
		<td class='cDataTD'><a href="#">descargar...</a></td>
	</tr>
	<tr>
		<td class='cDataTD'>file-20120903.txt</td>
		<td class='cDataTD'>03-09-2012 20:12</td>
		<td class='cDataTD'>119kb</td>
		<td class='cDataTD'><a href="#">descargar...</a></td>
	</tr>
	
</table>
<!-- 
<a class="cCancel" href="${pageContext.request.contextPath}/servlet/...">Cancelar</a>
 -->
<div id="divShowDetail" style="display: none">
	<h2 class="cTitle2">Detalle de valores</h2>

	<div class="contentScroll">
		<table class="cList" cellpadding="0" cellspacing="0" id="movesTable">
			<tr>
				<td class="cHeadTD">Detalle</td>
				<td class="cHeadTD">Comentario</td>
				<td class="cHeadTD">Tipo</td>
				<td class="cHeadTD">Monto</td>
			</tr>
		</table>
	</div>
	<br /> 

	<input type="button" value="Cancelar"
		onclick="javascript:closeTooltip()" />

</div>


<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>

