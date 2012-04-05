<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="cl.buildersoft.framework.type.BSData"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>
<%@ include file="/WEB-INF/jsp/table/functions.jsp"%>


<h1 class="cTitle">Respuesta del CSV</h1>
<div style="overflow: scroll; width: 1080px; height: 450px">
<%

	List<Map<String,BSData>> respCSV = (List<Map<String,BSData>>)session.getAttribute("respCSV");
	out.println("<table class='cList' cellpadding='0' cellspacing='0'>");
	out.println("<tr>");	
	for(int i=0;i<15;i++)
	{
		out.println("<td class='cHeadTD'>encabezado</td>");		
	}
	out.println("</tr>");
	Integer rowCount = 0;

for (Map<String, BSData> map : respCSV) {
	String color = ++rowCount % 2 != 0 ? "cDataTD" : "cDataTD_odd";
	out.println("<tr>");
	Iterator it = map.entrySet().iterator();
	while (it.hasNext()) {
		Map.Entry<String, BSData> e = (Map.Entry<String, BSData>)it.next();
		if(!e.getKey().toString().equalsIgnoreCase("result"))
		{
			out.println("<td  class='"+(!e.getValue().isState() ? "cDataErrorTD" : color)+"' align='left'>");
			out.print(e.getValue().getValue());
			out.println("</td>");
		}
	}
	if(!map.get("result").isState())
	{
		out.println("<td  class='cDataErrorTD' align='left'>");
		out.print("NOK");
		out.println("</td>");			
	}
	else
	{
		out.println("<td  class='cDataTD' align='left'>");
		out.print("OK");
		out.println("</td>");
	}	
	out.println("</tr>");
}
out.println("</table>");


/*
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

*/
%>
</div>


<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>

