<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>

<h1 class="cTitle">Reporte de reloj control</h1>

<table>
	<tr>
		<td class="cLabel">Rango de fechas:</td>
		<td><input type="date">&nbsp;<span class="cLabel">hasta</span>&nbsp;<input type="date"></td>
	</tr>
	<tr>
		<td class="cLabel">Empleado:</td>
		<td><select><option>[Todos]</option></select></td>
	</tr>
	<tr>
		<td class="cLabel">Tipo de marca:</td>
		<td><select><option>[Todas]</option>
				<option>Normales</option>
				<option>Extraordinarias</option></select></td>
	</tr>
</table>


<table class="cList" cellpadding="0" cellspacing="0">
<caption>Resumen</caption>
		<tr>
		<td class='cHeadTD'>Fecha</td>
		<td class='cHeadTD'>Horas Extras</td>
		<td class='cHeadTD'>Asistencia</td>
		<td class='cHeadTD'>Puntualidad</td>
	</tr>

	<tr>
		<td class='cDataTD'>01-09-2012</td>
		<td class='cDataTD'>48</td>
		<td class='cDataTD'>95%</td>
		<td class='cDataTD'>60%</td>
	</tr>
	<tr>
		<td class='cDataTD'>02-09-2012</td>
		<td class='cDataTD'>21</td>
		<td class='cDataTD'>90%</td>
		<td class='cDataTD'>46%</td>
	</tr>
	<tr>
		<td class='cDataTD'>03-09-2012</td>
		<td class='cDataTD'>30</td>
		<td class='cDataTD'>95%</td>
		<td class='cDataTD'>75%</td>
	</tr>
</table>
<br>
<hr>
<br> 	
<table class="cList" cellpadding="0" cellspacing="0">
<caption>Detalle</caption>
	<tr>
		<td class='cHeadTD'>Empleado</td>
		<td class='cHeadTD'>Fecha</td>
		<td class='cHeadTD'>Entrada</td>
		<td class='cHeadTD'>Salida</td>
		<td class='cHeadTD'>Horas Extras</td>
	</tr>

	<tr>
		<td class='cDataTD'>Hernesto Vilchez</td>
		<td class='cDataTD'>01-09-2012</td>
		<td class='cDataTD'>08:04</td>
		<td class='cDataTD'>18:01</td>
		<td class='cDataTD'>0.0</td>
	</tr>
	<tr>
		<td class='cDataTD'>Hernesto Vilchez</td>
		<td class='cDataTD'>02-09-2012</td>
		<td class='cDataTD'>07:57</td>
		<td class='cDataTD'>20:13</td>
		<td class='cDataTD'>2.0</td>
	</tr>
	<tr>
		<td class='cDataTD'>Hernesto Vilchez</td>
		<td class='cDataTD'>03-09-2012</td>
		<td class='cDataTD'>08:02</td>
		<td class='cDataTD'>18:15</td>
		<td class='cDataTD'>0.25</td>
	</tr>
</table>

<br>
<span class="cLabel">
<a href="#">Descargar para Microsoft Excel</a>
</span>



<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>

