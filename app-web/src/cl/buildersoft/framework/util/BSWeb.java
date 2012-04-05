package cl.buildersoft.framework.util;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cl.buildersoft.framework.beans.BSField;
import cl.buildersoft.framework.beans.Option;
import cl.buildersoft.framework.beans.Rol;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.exception.BSProgrammerException;
import cl.buildersoft.framework.services.BSMenuService;
import cl.buildersoft.framework.services.impl.BSMenuServiceImpl;
import cl.buildersoft.framework.type.BSFieldType;

public class BSWeb {
	public static Object value2Object(Connection conn,
			HttpServletRequest request, BSField field, boolean fromWebPage) {
		Object out = null;
		String name = field.getName();
		String value = fromWebPage ? request.getParameter(name)
				: (String) field.getValue();
		BSFieldType type = field.getType();

		// System.out.println("name : " + field.getName() + " Valor : " +
		// field.getValue());

		out = evaluateType(conn, request, out, value, type);
		return out;
	}

	private static Object evaluateType(Connection conn,
			HttpServletRequest request, Object out, String value,
			BSFieldType type) {
		if (type.equals(BSFieldType.String)) {
			out = value;
		} else if (type.equals(BSFieldType.Boolean)) {
			out = Boolean.parseBoolean(value);
		} else if (type.equals(BSFieldType.Date)) {
			String formatDate = getFormatDate(request);

			// String formatDate = "yyyy-MM-dd";
			DateFormat formatter = new SimpleDateFormat(formatDate);

			try {
				out = (Date) formatter.parse(value);
			} catch (ParseException e) {
				throw new BSProgrammerException("0110",
						"No se pudo parsear el valor " + value + " como fecha");
			}

		} else if (type.equals(BSFieldType.Timestamp)) {
			String formatDate = getFormatDatetime(conn);
			SimpleDateFormat dateFormat = new SimpleDateFormat(formatDate);
			java.util.Date parsedDate;
			try {
				parsedDate = dateFormat.parse(value);
			} catch (ParseException e) {
				throw new BSProgrammerException("0110",
						"No se pudo parsear el valor " + value
								+ " como fecha/hora");
			}
			out = new java.sql.Timestamp(parsedDate.getTime());
		} else if (type.equals(BSFieldType.Text)) {
			throw new BSProgrammerException("0100");
		} else {
			value = value.replaceAll("[.]", "");
			// value = value.replaceAll(",", "");
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

	public static String getFormatDatetime(Connection conn) {
		BSConfig config = new BSConfig();
		return config.getString(conn, "FORMAT_DATETIME");
	}

	@Deprecated
	public static String getFormatDatetime(HttpServletRequest request) {
		String out = request.getServletContext().getInitParameter(
				"bsframework.datetimeFormat");
		if (out == null) {
			out = "dd/MM/yyyy HH:mm:ss";
		}
		return out;
	}

	public static String getFormatDate(Connection conn) {
		BSConfig config = new BSConfig();
		return config.getString(conn, "FORMAT_DATE");
	}

	@Deprecated
	public static String getFormatDate(HttpServletRequest request) {
		String out = request.getServletContext().getInitParameter(
				"bsframework.dateFormat");
		if (out == null) {
			out = "dd/MM/yyyy";
		}
		return out;
	}

	public static String getFormatNumber(Connection conn) {
		BSConfig config = new BSConfig();
		return config.getString(conn, "FORMAT_NUMBER");
	}

	@Deprecated
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

	public static Boolean canUse(String optionKey, HttpServletRequest request) {
		Boolean out = Boolean.TRUE;

		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request.getServletContext(),
				"bsframework");

		BSMenuService menuService = new BSMenuServiceImpl();
		Option option = menuService.searchResourceByKey(conn, optionKey);
		if (option != null) {
			List<Rol> rols = null;

			HttpSession session = request.getSession();
			synchronized (session) {
				rols = (List<Rol>) session.getAttribute("Rol");
			}

			for (Rol rol : rols) {
				out = validResourceByRol(conn, mysql, option.getId(),
						rol.getId());
				if (out) {
					break;
				}
			}
		}
		return out;
	}

	private static Boolean validResourceByRol(Connection conn, BSmySQL mysql,
			Long option, Long rol) {
		String sql = "SELECT COUNT(cOption) AS cnt FROM tR_RolOption WHERE cOption=? AND cRol=?";

		List<Object> prm = new ArrayList<Object>();
		prm.add(option);
		prm.add(rol);

		Integer cnt = Integer.parseInt(mysql.queryField(conn, sql, prm));

		Boolean out = cnt > 0;

		return out;
	}

}
