<%@page import="cl.buildersoft.framework.util.BSWeb"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.Format"%>
<%@page import="cl.buildersoft.framework.type.BSFieldType"%>
<%@page import="cl.buildersoft.framework.beans.BSField"%>
<%@page import="cl.buildersoft.framework.beans.BSTableConfig"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	ResultSet rs = (ResultSet) request.getAttribute("Data");
	BSTableConfig table = (BSTableConfig) session
			.getAttribute("BSTable");

	BSAction[] tableActions = table.getActions(BSActionType.Table);
	BSAction[] recordActions = table.getActions(BSActionType.Record);
	BSAction[] multirecordActions = table
			.getActions(BSActionType.MultiRecord);

	Integer selectorType = 0;
	selectorType += recordActions.length > 0 ? 1 : 0;
	selectorType += multirecordActions.length > 0 ? 1 : 0;
%>

<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>
<%@ include file="/WEB-INF/jsp/table/functions.jsp"%>

<script
	src="${pageContext.request.contextPath}/js/table/table.js?<%=Math.random()%>"></script>
<h1 class="cTitle">
	<%=table.getTitle()%>
</h1>
<form method="post"
	action="${pageContext.request.contextPath}/servlet/table/DeleteRecords"
	id='frm'>


	<table class="cList" cellpadding="0" cellspacing="0">
		<%
			BSField[] fields = table.getFields();
			String name = null;
			String pkName = null;

			int rowCount = 0;
			Object[] values = null;
			out.println("<tr>");

			if (selectorType > 0) {
				String type = selectorType == 1 ? "radio" : "CHECKBOX";
				out.print("<td  align='center' class='cHeadTD'><input id='mainCheck' type='"
						+ type
						+ "' onclick='javascript:swapCheck(this);'></td>");
			}
			for (BSField field : fields) {
				if (showColumn(field)) {
					out.print("<td class='cHeadTD'");

					out.print(getAlign(field));

					out.print(">" + field.getLabel() + "</td>");
				}
				if (field.isPk()) {
					pkName = field.getName();
				}
			}
			out.println("</tr>");

			while (rs.next()) {
				values = values2Array(rs, pkName, fields);

				out.print(writeValues(values, fields, rowCount, 
						ctxPath, request, selectorType));
				rowCount++;
			}

			rs.close();
		%>
	</table>
</form>
<%
	out.print("<br>");

	for (BSAction action : tableActions) {
		String id = capitalize(action.getCode());
		out.print("<input type='button' ");
		out.print("value='" + action.getLabel() + "' ");
		out.print("id='o" + id + "' ");
		out.print("onclick='javascript:window.location.href=\""
				+ ctxPath + action.getUrl() + "\"'");
		out.print(">");

	}

	for (BSAction action : recordActions) {
		String id = capitalize(action.getCode());
		out.print("<input disabled type='button' ");
		out.print("value='" + action.getLabel() + "' ");
		out.print("id='o" + id + "' ");
		out.print("onclick='javascript:f" + id + "();'");
		out.print(">");
	}

	for (BSAction action : multirecordActions) {
		String id = capitalize(action.getCode());
		out.print("<input disabled type='button' ");
		out.print("value='" + action.getLabel() + "' ");
		out.print("id='o" + id + "' ");
		out.print("onclick='javascript:f" + id + "();'");
		out.print(">");
	}
%>


<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>

<%!private String writeValues(Object[] values, BSField[] fields,
			Integer rowCount, String ctxPath, HttpServletRequest request,
			Integer selectorType) {
		String out = "<tr>";
		Object value = null;
		int i = 1;
		BSFieldType type = null;
		String color = rowCount % 2 != 0 ? "cDataTD" : "cDataTD_odd";

		if (selectorType > 0) {
			out += "<td align='center' class='"
					+ color
					+ "'><input type='CHECKBOX' onclick='javascript:verifyDeleteButton();' name='cId' value='"
					+ values[0] + "'></td>";
		}

		for (BSField field : fields) {
			type = field.getType();

			if (showColumn(field)) {
				//			if (field.isVisible() && !type.equals(BSType .Password)) {

				value = field.isPk() ? values[0] : values[i++];

				out += "<td class='" + color + "'";
				out += getAlign(field);

				out += ">";

				if (selectorType>0) {
					out += "<a href='" + ctxPath
							+ "/servlet/table/SearchRecord?cId=" + values[0]
							+ "'>";
				}
				if (type.equals(BSFieldType.Boolean)) {
					Boolean b = (Boolean) value;
					if (b.booleanValue() == Boolean.TRUE) {
						out += "Si";
					} else {
						out += "No";
					}
				} else if (type.equals(BSFieldType.Date)) {
					String format = BSWeb.getFormatDate(request);
					Format formatter = new SimpleDateFormat(format);
					out += formatter.format(value);
				} else if (type.equals(BSFieldType.Datetime)) {
					String format = BSWeb.getFormatDatetime(request);
					Format formatter = new SimpleDateFormat(format);
					out += formatter.format(value);

				} else if (type.equals(BSFieldType.Double)) {
					String format = BSWeb.getFormatNumber(request);
					Format formatter = new DecimalFormat(format);
					out += formatter.format(value);

				} else {
					out += value;
				}
				if (selectorType>0) {
					out += "</a>";
				}
				out += "</td>";
			}
		}

		out += "</tr>";

		return out;
	}

	private Object[] values2Array(ResultSet rs, String pkName, BSField[] fields)
			throws Exception {
		String name = null;
		Object value = null;
		int i = 1;
		Object[] out = new Object[fields.length];

		for (BSField field : fields) {
			name = field.getName();
			value = rs.getObject(name);

			if (field.getName().equals(pkName)) {
				out[0] = value;
			} else {
				out[i++] = value;
			}

		}
		return out;
	}%>
