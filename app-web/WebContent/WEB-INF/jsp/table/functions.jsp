<%@page import="cl.buildersoft.lib.dataType.BSDataTypeUtil"%>
<%@page import="cl.buildersoft.lib.util.crud.BSField"%>
<%@page import="cl.buildersoft.lib.dataType.BSDataType"%>
<%@page import="java.util.List"%>
<%@page import="java.io.PrintWriter"%>
<%!private String getAlign(BSField field) {
		String out = " align='left' ";

		if (BSDataTypeUtil.isTime(field.getType()) || field.getType().equals(BSDataType.BOOLEAN) || field.isFK()) {
			out = " align='center' ";
		} else if (BSDataTypeUtil.isNumber(field.getType())) {
			out = " align='right' ";
		}
		return out;
	}

	private String capitalize(String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
	}%>