<%@page import="cl.buildersoft.framework.type.BSActionType"%>
<%@page import="cl.buildersoft.framework.beans.BSAction"%>
<%@page import="cl.buildersoft.framework.beans.BSTableConfig"%>
<%@page import="cl.buildersoft.framework.type.BSFieldType"%>
<%@page import="cl.buildersoft.framework.beans.BSField"%>
<%!private Boolean isNumber(BSField field) {
		return field.getType().equals(BSFieldType.Double)
				|| field.getType().equals(BSFieldType.Integer)
				|| field.getType().equals(BSFieldType.Long);
	}

	private Boolean isTime(BSField field) {
		return field.getType().equals(BSFieldType.Date)
				|| field.getType().equals(BSFieldType.Datetime);
	}

	private Boolean showColumn(BSField field) {
		return !field.isPk();
	}

	private String getAlign(BSField field) {
		String out = " align='left' ";
		if (isTime(field) || field.getType().equals(BSFieldType.Boolean)) {
			out = " align='center' ";
		} else if (isNumber(field)) {
			out = " align='right' ";
		}
		return out;
	}
/**
	private Boolean haveActions(BSTableConfig table, BSActionType actionType) {
		Boolean out = Boolean.FALSE;
		BSAction[] actions = table.getActions();
		for (BSAction action : actions) {
			if (action.getActionType().equals(BSActionType.Table)) {
				out = Boolean.TRUE;
				break;
			}
		}
		return out;
	}*/%>