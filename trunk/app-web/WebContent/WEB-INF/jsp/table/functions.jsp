<%@page import="cl.buildersoft.framework.dataType.BSDataTypeUtil"%>
<%@page import="cl.buildersoft.framework.util.crud.BSField"%>
<%@page import="cl.buildersoft.framework.dataType.BSDataType"%>
<%@page import="java.util.List"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="cl.buildersoft.framework.beans.DEPRECATED.BSCss"%>
<%@page import="cl.buildersoft.framework.beans.DEPRECATED.BSScript"%>
<%@page import="cl.buildersoft.framework.beans.DEPRECATED.BSHeadConfig"%>
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