<%@page import="java.util.Arrays"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cl.buildersoft.framework.beans.BSTableConfig"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>
<%
	ResultSet relation = (ResultSet) request.getAttribute("Relation");
	ResultSet list = (ResultSet) request.getAttribute("List");

	BSTableConfig table = (BSTableConfig) session
			.getAttribute("BSTable");

	List<String[]> listArray = resultSet2Matrix(list);
	list.close();

	List<String[]> relationArray = resultSet2Matrix(relation);
	relation.close();
%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/table/relation.js?<%=Math.random()%>"></script>

<h1 class="cTitle"><%=table.getTitle()%></h1>

<!--
<form action="${pageContext.request.contextPath}/servlet/ShowParameters">
  -->
<form
	action="${pageContext.request.contextPath}/servlet/table/SaveRelation"
	id="frm" method="post">
	<input type="hidden" name="cId"
		value="<%=request.getParameter("cId")%>">
	<table border="1">
		<tr>
			<td style="width: 30%" align="center"><span class="cLabel">Disponibles</span><br>
				<select SIZE="10" id="left" style="width: 100%">
					<%
						for (String[] row : listArray) {
							if (!exists(row, relationArray)) {
					%>
					<option value="<%=row[0]%>"><%=row[1]%></option>
					<%
						}
						}
					%>
			</select></td>

			<td style="width: 10%" align="center"><input type="button"
				value="->" onclick="javascript:add();" style="width: 100%"><br>
				<br> <br> <input type="button" value="<-"
				onclick="javascript:remove();" style="width: 100%"></td>

			<td style="width: 30%" align="center"><span class="cLabel"
				align="center">Seleccionados</span><br> <select name="Relation"
				SIZE="10" id="right" style="width: 100%">
					<%
						for (String[] row : relationArray) {
					%>
					<option value="<%=row[0]%>"><%=row[1]%></option>
					<%
						}
					%>
			</select></td>
		</tr>
		<tr>
			<td align="center"><input type="button" value="Aceptar"
				onclick="javascript:save();"></td>
			<td>&nbsp;</td>
			<td align="center"><a href="<%=ctxPath + table.getUri()%>">Cancelar</a></td>
		</tr>
	</table>
</form>
<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>

<%!private List<String[]> resultSet2Matrix(ResultSet rs) {
		List<String[]> out = new ArrayList<String[]>();

		try {
			Integer i = 0;
			ResultSetMetaData metaData = rs.getMetaData();
			Integer colCount = metaData.getColumnCount();
			String[] colNames = new String[colCount];
			for (i = 1; i <= colCount; i++) {
				colNames[i - 1] = metaData.getColumnName(i);
			}

			String[] innerArray = null;
			while (rs.next()) {
				i = 0;
				innerArray = new String[colCount];
				for (String colName : colNames) {
					innerArray[i] = rs.getString(colName);
					i++;
				}
				out.add(innerArray);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return out;
	}

	private Boolean exists(String[] element, List<String[]> list) {
		Boolean out = Boolean.FALSE;
		for (String[] e : list) {
			//showCompares(element, e);

			if (Arrays.equals(element, e)) {
				//			if (element.toString().equals(e.toString())) {
				out = Boolean.TRUE;
				break;
			}
		}
		/*
		System.out.println(Arrays.toString(element) + "in" + showList(list)
			+ "->" + out);
		 */
		return out;
	}

	private void showCompares(String[] a, String[] b) {
		System.out.println(Arrays.toString(a) + " == " + Arrays.toString(b)
				+ "->" + Arrays.equals(a, b));
	}

	private String showList(List<String[]> l) {
		String out = "";

		for (String[] e : l) {
			out += Arrays.toString(e) + ", ";
		}
		return out;
	}%>