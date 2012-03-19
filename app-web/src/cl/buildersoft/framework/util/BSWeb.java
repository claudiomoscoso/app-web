package cl.buildersoft.framework.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.framework.beans.BSField;
import cl.buildersoft.framework.type.BSFieldType;

public class BSWeb {
	public static Object value2Object(HttpServletRequest request, BSField field)
			throws Exception {
		Object out = null;
		String name = field.getName();
		String value = request.getParameter(name);
		BSFieldType type = field.getType();
		
		if (type.equals(BSFieldType.String)) {
			out = value;
		} else if (type.equals(BSFieldType.Boolean)) {
			out = Boolean.parseBoolean(value);
		} else if (type.equals(BSFieldType.Date)) {
			String formatDate = getFormatDate(request);
			DateFormat formatter = new SimpleDateFormat(formatDate);
			out = (Date) formatter.parse(value);
		} else if (type.equals(BSFieldType.Datetime)) {
			String formatDate = getFormatDatetime(request);
			SimpleDateFormat dateFormat = new SimpleDateFormat(formatDate);
			java.util.Date parsedDate = dateFormat.parse(value);
			out = new java.sql.Timestamp(parsedDate.getTime());
		} else if (type.equals(BSFieldType.Text)) {
			throw new RuntimeException(
					"No se ha implementado la carga de archivos aun..");
		} else {
			value = value.replaceAll("[.]", "");
//			value = value.replaceAll(",", "");
			if (type.equals(BSFieldType.Double)) {
				out = Double.parseDouble(value);
			} else if (type.equals(BSFieldType.Integer)) {
				out = Integer.parseInt(value);
			} else if (type.equals(BSFieldType.Long)) {
				out = Long.parseLong(value);
			}

		}
		return out;
	}

	public static String getFormatDatetime(HttpServletRequest request) {
		String out = request.getServletContext().getInitParameter(
				"bsframework.datetimeFormat");
		if (out == null) {
			out = "dd/MM/yyyy HH:mm:ss";
		}
		return out;
	}

	public static String getFormatDate(HttpServletRequest request) {
		String out = request.getServletContext().getInitParameter(
				"bsframework.dateFormat");
		if (out == null) {
			out = "dd/MM/yyyy";
		}
		return out;
	}

	public static String getFormatNumber(HttpServletRequest request) {
		String out = request.getServletContext().getInitParameter(
				"bsframework.numberFormat");
		if (out == null) {
			out = "#,###,###,###";
		}
		return out;
	}

	public static String date2String(Object value, String format) {
		String out = "";
		if (value != null) {
			DateFormat formatter = new SimpleDateFormat(format);
			out = formatter.format((Date) value);
		}
		return out;
	}

	public static String number2String(Object value, String format) {
		String out = "";
		if (value != null) {
			Format formatter = new DecimalFormat(format);
			out = formatter.format(value);
		}
		return out;
	}
}
