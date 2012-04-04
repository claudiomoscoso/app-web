<%@page import="cl.buildersoft.framework.type.BSData"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>
<%@ include file="/WEB-INF/jsp/table/functions.jsp"%>


<h1 class="cTitle">Respuesta del CSV</h1>
<div style="overflow: scroll; width: 900px; height: 450px">
<%
	List<List<BSData>> respCSV = (List<List<BSData>>)session.getAttribute("respCSV");
	
	out.println("<table class='cList' cellpadding='0' cellspacing='0'>");
	out.println("<tr>");	
	for(int i=0;i<15;i++)
	{
		out.println("<td class='cHeadTD'>encabezado</td>");		
	}
	out.println("</tr>");
	Integer rowCount = 0;
	for (List<BSData> fila : respCSV) {
		String color = ++rowCount % 2 != 0 ? "cDataTD" : "cDataTD_odd";
		out.println("<tr>");	
		int longRow = fila.size();
		for (int i = 0; i < longRow; i++) {
			BSData celda = fila.get(i);
			
			out.println("<td  class='"+(!celda.isState() ? "cDataErrorTD" : color)+"' align='left'>");
			out.print(celda.getValue());
			out.println("</td>");
		}
		out.println("</tr>");
	}
	out.println("</table>");


%>
</div>


<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>

