<%@page import="cl.buildersoft.framework.util.BSDateTimeUtil"%>
<%@page import="java.sql.Connection"%>
<%@page import="cl.buildersoft.framework.database.BSmySQL"%>
<%@page import="cl.buildersoft.framework.type.BSFieldDataType"%>
<%@page import="cl.buildersoft.framework.type.BSTypeFactory"%>
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
	Connection conn = (Connection) request.getAttribute("Conn");
	BSTableConfig table = (BSTableConfig) session.getAttribute("BSTable");
	String ctxPath = request.getContextPath();

	BSAction[] tableActions = table.getActions(BSActionType.Table);
	BSAction[] recordActions = table.getActions(BSActionType.Record);
	BSAction[] multirecordActions = table.getActions(BSActionType.MultiRecord);

	Integer selectorType = getSelectorType(tableActions, recordActions, multirecordActions, request);
%>

<%!private Integer getSelectorType(BSAction[] tableActions, BSAction[] recordActions, BSAction[] multirecordActions,
			HttpServletRequest request) {

		Integer selectorType = 0;

		Boolean haveTableActions = Boolean.FALSE;
		Boolean haveRecordActions = Boolean.FALSE;
		Boolean haveMultirecordActions = Boolean.FALSE;

		for (BSAction action : tableActions) {
			if (BSWeb.canUse(action.getCode(), request)) {
				haveTableActions = Boolean.TRUE;
				break;
			}
		}

		for (BSAction action : multirecordActions) {
			if (BSWeb.canUse(action.getCode(), request)) {
				haveMultirecordActions = Boolean.TRUE;
				break;
			}
		}

		for (BSAction action : recordActions) {
			if (BSWeb.canUse(action.getCode(), request)) {
				haveRecordActions = Boolean.TRUE;
				break;
			}
		}

		selectorType += haveRecordActions ? 1 : 0;
		selectorType += haveMultirecordActions ? 2 : 0;

		return selectorType;
	}%>

<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>
<%@ include file="/WEB-INF/jsp/table/functions.jsp"%>

<script
	src="${pageContext.request.contextPath}/js/table/table.js?<%=Math.random()%>"></script>
<h1 class="cTitle">
	<%=table.getTitle()%>
</h1>

<%@ include file="/WEB-INF/jsp/table/search.jsp"%>
<form method="post"
	action="${pageContext.request.contextPath}/servlet/common/DeleteRecords"
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
				out.print("<td  align='center' class='cHeadTD'>");
				if (selectorType >= 2) {
					out.print("<input id='mainCheck' type='" + type + "' onclick='javascript:swapAllCheck(this);'>");
				}
				out.print("</td>");
			}

			for (BSField field : fields) {
				if (field.showField()) {
					out.print("<td class='cHeadTD'");

					out.print(getAlign(field));

					out.print(">" + field.getLabel() + "</td>");
				}
				if (field.isPK()) {
					pkName = field.getName();
				}
			}
			out.println("</tr>");

			while (rs.next()) {
				values = values2Array(rs, pkName, fields);

				out.println(writeValues(values, fields, rowCount, ctxPath, request, selectorType));
				rowCount++;
			}

			rs.close();
			new BSmySQL().closeConnection(conn);
		%>
	</table>

	<%@ include file="/WEB-INF/jsp/common/pagination.jsp"%>

	<%
		out.print("<br>");

		out.print("<div id='TableActions' style='float:left;'>");
		for (BSAction action : tableActions) {
			if (BSWeb.canUse(action.getCode(), request)) {
				String id = capitalize(action.getCode());
				out.print("<button type='button' ");
				out.print("id='o" + id + "' ");
				out.print(action.getDisabled() ? "disabled" : "");

				out.print(" onclick='javascript:window.location.href=\"" + ctxPath + action.getUrl() + "\"'");
				out.print(">" + action.getLabel() + "</button>");
			}

		}
		out.print("</div>");

		out.print("<div id='MultirecordActions' style='float:left;display:none;'>");
		for (BSAction action : multirecordActions) {
			if (BSWeb.canUse(action.getCode(), request)) {
				String id = capitalize(action.getDefaultCode());
				out.print("<button type='button' ");
				out.print("id='o" + id + "' ");
				out.print(action.getDisabled() ? "disabled" : "");

				out.print(" onclick='javascript:f" + id + "();'");
				out.print(">" + action.getLabel() + "</button>");
			}
		}
		out.print("</div>");

		out.print("<div id='RecordActions' style='float:left;display:none;'>");
		for (BSAction action : recordActions) {
			if (BSWeb.canUse(action.getCode(), request)) {
				String id = capitalize(action.getCode());
				String method = action.getMethod() != null ? "&Method=" + action.getMethod() : "";
				out.print("<button type='button' ");
				out.print("id='o" + id + "' ");

				out.print(action.getDisabled() ? "disabled" : "");

				out.print(" onclick='javascript:doAction(\"" + ctxPath + action.getUrl() + "\", \"" + action.getCode()
						+ method + "\");'");

				out.print(">" + action.getLabel() + "</button>");
			}
		}
		out.print("</div>");
	%>
</form>

<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>

<%!private String writeValues(Object[] values, BSField[] fields, Integer rowCount, String ctxPath, HttpServletRequest request,
			Integer selectorType) {
		String out = "<tr>";
		Object value = null;
		int i = 1;
		String color = rowCount % 2 != 0 ? "cDataTD" : "cDataTD_odd";

		if (selectorType > 0) {
			String type = selectorType == 1 ? "radio" : "CHECKBOX";
			out += "<td align='center' class='" + color + "'>";

			out += "<input type='" + type + "' ";
			out += "name='cId' ";
			out += "value='" + values[0] + "' ";
			if (selectorType == 1) {
				out += "onclick=\"javascript:$('#RecordActions').show(speed);\" ";
			} else {
				out += "onclick='javascript:swapCheck(this);' ";
			}
			out += ">";

			out += "</td>";
		}
		BSFieldType type = null;
		for (BSField field : fields) {
			type = field.getType();

			value = field.isPK() ? values[0] : values[i++];
			if (field.showField()) {
				out += "<td class='" + color + "'";
				out += getAlign(field);
				out += ">";

				if (field.isFK()) {
					out += getFKValue(field, value);
				} else {
					if (type.equals(BSFieldType.Boolean)) {
						Boolean b = (Boolean) value;
						if (b.booleanValue() == Boolean.TRUE) {
							out += "Si";
						} else {
							out += "No";
						}
					} else if (type.equals(BSFieldType.Double)) {
						out += BSWeb.formatDouble(request, (Double) value);
					} else if (type.equals(BSFieldType.Date)) {
						out += BSDateTimeUtil.date2String(request, value);
					} else if (type.equals(BSFieldType.Integer)) {
						out += BSWeb.formatInteger(request, (Integer) value);
					}

					else {
						out += value;
					}

				}
				out += "</td>";
			}
		}

		out += "</tr>";

		return out;
	}

	private String getFKValue(BSField field, Object code) {
		String out = "-";
		Long codeLong = (Long) code;

		List<Object[]> data = field.getFKData();
		Long id = null;
		for (Object[] row : data) {
			id = (Long) row[0];

			if (codeLong.equals(id)) {
				out = (String) row[1];
				break;
			}

		}

		return out;
	}

	private Object[] values2Array(ResultSet rs, String pkName, BSField[] fields) throws Exception {
		String name = null;
		Object value = null;
		int i = pkName == null ? 0 : 1;
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
