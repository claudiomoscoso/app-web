package cl.buildersoft.framework.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.util.Base64;

import cl.buildersoft.framework.beans.Option;
import cl.buildersoft.framework.beans.Rol;
import cl.buildersoft.framework.dataType.BSDataType;
import cl.buildersoft.framework.dataType.BSDataTypeUtil;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.exception.BSDataBaseException;
import cl.buildersoft.framework.exception.BSProgrammerException;
import cl.buildersoft.framework.services.BSMenuService;
import cl.buildersoft.framework.services.impl.BSMenuServiceImpl;
import cl.buildersoft.framework.util.crud.BSField;

public class BSWeb {
	private static final String LOCALE = "LOCALE";
	private static final String PATTERN_DECIMAL = "PATTERN_DECIMAL";
	private static final String PATTERN_INTEGER = "PATTERN_INTEGER";

	public static Object value2Object(Connection conn, HttpServletRequest request, BSField field, boolean fromWebPage) {
		Object out = null;
		String name = field.getName();
		String value = fromWebPage ? request.getParameter(name) : (String) field.getValue();

		out = evaluateType(conn, value, field);
		return out;
	}

	private static Object evaluateType(Connection conn, String value, BSField field) {
		BSDataType fieldDataType = BSDataTypeUtil.create(field.getType().toString());
		Object out = fieldDataType.parse(conn, value);

		return out;
	}
/**<code>
	private static Object evaluateType(Connection conn, HttpServletRequest request, Object out, String value, BSFieldType type,
			BSField field) {

		if (type.equals(BSFieldType.String)) {
			out = value;
		} else if (type.equals(BSFieldType.Boolean)) {
			out = Boolean.parseBoolean(value);
		} else if (type.equals(BSFieldType.Date)) {
			String formatDate = BSDateTimeUtil.getFormatDate(request);
			DateFormat formatter = new SimpleDateFormat(formatDate);

			try {
				out = (Date) formatter.parse(value);
			} catch (ParseException e) {
				throw new BSProgrammerException("0110", "No se pudo parsear el valor " + value + " como fecha");
			}

		} else if (type.equals(BSFieldType.Timestamp)) {
			String formatDate = BSDateTimeUtil.getFormatDatetime(conn);
			SimpleDateFormat dateFormat = new SimpleDateFormat(formatDate);
			java.util.Date parsedDate;
			try {
				parsedDate = dateFormat.parse(value);
			} catch (ParseException e) {
				throw new BSProgrammerException("0110", "No se pudo parsear el valor " + value + " como fecha/hora");
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
</code>*/
	/********************/

	private static Connection requestToConnection(HttpServletRequest request) {
		BSmySQL mysql = new BSmySQL();
		return mysql.getConnection(request);
	}

	private static String getConfig(Connection conn, String key) {
		BSConfig config = new BSConfig();
		return config.getString(conn, key);
	}

	public static String getLocale(Connection conn) {
		return getConfig(conn, LOCALE).toLowerCase();
	}

	public static String getLocale(HttpServletRequest request) {
		Connection conn = requestToConnection(request);
		String out = getLocale(conn);
		new BSmySQL().closeConnection(conn);
		return out;
	}

	public static String formatDouble(Connection conn, Double value) {
		return formatNumber(conn, value, getConfig(conn, PATTERN_DECIMAL));
	}

	public static String formatDouble(HttpServletRequest request, Double value) {
		Connection conn = requestToConnection(request);
		String out = formatDouble(conn, value);
		new BSmySQL().closeConnection(conn);
		return out;
	}

	public static String formatLong(HttpServletRequest request, Long value) {
		Connection conn = requestToConnection(request);
		String out = formatLong(conn, value);
		new BSmySQL().closeConnection(conn);
		return out;
	}

	public static String formatLong(Connection conn, Long value) {
		return formatNumber(conn, value, getConfig(conn, PATTERN_INTEGER));
	}

	public static String formatInteger(HttpServletRequest request, Integer value) {
		Connection conn = requestToConnection(request);
		String out = formatInteger(conn, value);
		new BSmySQL().closeConnection(conn);
		return out;
	}

	public static String formatInteger(Connection conn, Integer value) {
		return formatNumber(conn, value, getConfig(conn, PATTERN_INTEGER));
	}

	public static String formatNumber(HttpServletRequest request, Object value, String pattern) {
		Connection conn = requestToConnection(request);
		String out = formatNumber(conn, value, pattern);
		new BSmySQL().closeConnection(conn);
		return out;
	}

	public static String formatNumber(Connection conn, Object value, String pattern) {
		String out = "";
		if (value != null) {
			Locale locale = new Locale(getLocale(conn));

			if (pattern != null && pattern.length() > 0) {
				DecimalFormat format = (DecimalFormat) NumberFormat.getNumberInstance(locale);
				format.applyLocalizedPattern(pattern);
				out = format.format(value);
			} else {
				NumberFormat format = NumberFormat.getNumberInstance(locale);
				out = format.format(value);
			}
		}
		return out;
	}

	private static Object parseNumber(Connection conn, String value, String pattern, Class claz) {
		Locale locale = new Locale(getLocale(conn));
		Object out = null;

		ParsePosition parsePosition = new ParsePosition(0);

		if (pattern != null && pattern.length() > 0) {
			DecimalFormat format = (DecimalFormat) NumberFormat.getNumberInstance(locale);
			format.applyLocalizedPattern(pattern);

			Number num = format.parse(value, parsePosition);

			if (claz.equals(Integer.class)) {
				out = num.intValue();
			} else if (claz.equals(Double.class)) {
				out = num.doubleValue();
			} else {
				out = num.longValue();
			}

		} else {
			NumberFormat formatNumber = NumberFormat.getNumberInstance(locale);
			out = formatNumber.parse(value, parsePosition);
		}
		if (parsePosition.getIndex() < value.length()) {
			throw new BSProgrammerException("Number isn't correct " + value);
		}
		return out;
	}

	public static Double parseDouble(Connection conn, String value) {
		return (Double) parseNumber(conn, value, getConfig(conn, PATTERN_DECIMAL), Double.class);
	}

	public static Double parseDouble(HttpServletRequest request, String value) {
		Connection conn = requestToConnection(request);
		Double out = parseDouble(conn, value);
		new BSmySQL().closeConnection(conn);
		return out;
	}

	public static Integer parseInteger(Connection conn, String value) {
		return (Integer) parseNumber(conn, value, getConfig(conn, PATTERN_INTEGER), Integer.class);
	}

	public static Integer parseInteger(HttpServletRequest request, String value) {
		Connection conn = requestToConnection(request);
		Integer out = parseInteger(conn, value);
		new BSmySQL().closeConnection(conn);
		return out;

	}

	public static Long parseLong(Connection conn, String value) {
		return (Long) parseNumber(conn, value, getConfig(conn, PATTERN_INTEGER), Long.class);
	}

	public static Long parseLong(HttpServletRequest request, String value) {
		Connection conn = requestToConnection(request);
		Long out = parseLong(conn, value);
		new BSmySQL().closeConnection(conn);
		return out;
	}

	/**
	 * <code>
	public static String getFormatDecimal(Connection conn) {
		BSConfig config = new BSConfig();
		return config.getString(conn, "FORMAT_DECIMAL");
	}

	public static String getFormatDecimal(HttpServletRequest request) {
		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);
		return getFormatDecimal(conn);
	}

	public static String getFormatInteger(Connection conn) {
		BSConfig config = new BSConfig();
		return config.getString(conn, "FORMAT_INTEGER");
	}

	public static String getFormatInteger(HttpServletRequest request) {
		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);
		return getFormatInteger(conn);
	}

	public static String number2String(Object value, String format) {
		String out = "";
		if (value != null) {
			Format formatter = new DecimalFormat(format);
			out = formatter.format(value);
		}
		return out;
	}
</code>
	 */

	/********************/
	public static Boolean canUse(String optionKey, HttpServletRequest request) {
		Boolean out = Boolean.TRUE;

		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);

		BSMenuService menuService = new BSMenuServiceImpl();
		Option option = menuService.searchResourceByKey(conn, optionKey);
		if (option != null) {
			List<Rol> rols = null;

			HttpSession session = request.getSession();
			synchronized (session) {
				rols = (List<Rol>) session.getAttribute("Rol");
			}

			for (Rol rol : rols) {
				out = validResourceByRol(conn, mysql, option.getId(), rol.getId());
				if (out) {
					break;
				}
			}
		}
		return out;
	}

	private static Boolean validResourceByRol(Connection conn, BSmySQL mysql, Long option, Long rol) {
		String sql = "SELECT COUNT(cOption) AS cnt FROM tR_RolOption WHERE cOption=? AND cRol=?";

		List<Object> prm = new ArrayList<Object>();
		prm.add(option);
		prm.add(rol);

		Integer cnt = Integer.parseInt(mysql.queryField(conn, sql, prm));

		Boolean out = cnt > 0;

		return out;
	}

	public static String showResultSet(Connection conn, ResultSet rs) throws IOException {
		StringBuffer out = new StringBuffer(1024);
		String style = null;
		Boolean haveInfo = Boolean.FALSE;
		try {
			ResultSetMetaData metaData = rs.getMetaData();
			out.append("<table class='cList' cellpadding='0' cellspacing='0'>");
			out.append("<tr>");
			Integer index = 1, indexRow = 1;
			for (index = 1; index <= metaData.getColumnCount(); index++) {
				out.append("<td class='cHeadTD' nowrap align='center'>" + metaData.getColumnLabel(index) + "</td>");
			}
			out.append("</tr>\n");

			while (rs.next()) {
				out.append("<tr>");

				style = (indexRow++ % 2 == 1 ? "cDataTD" : "cDataTD_odd");
				String type = null;
				for (index = 1; index <= metaData.getColumnCount(); index++) {
					type = metaData.getColumnTypeName(index);
					String[] value = null;
					String data = null;
					data = rs.getString(index);
					value = formatData(conn, data, type);
					out.append("<td class='" + style + "' nowrap align='" + value[1] + "'>" + value[0] + "</td>");
				}

				haveInfo = Boolean.TRUE;
				out.append("</tr>\n");

			}
			if (!haveInfo) {
				out.append("<tr><td class='cDataTD' colspan='" + metaData.getColumnCount() + "'>No hay información</td></tr>");
			}
		} catch (SQLException e) {
			throw new BSDataBaseException(e);
		}

		out.append("</tr>");
		return out.toString();
	}

	private static String[] formatData(Connection conn, String data, String type) {
		String[] out = { "", "" };// type + " - " + data;
		String format = null;
		if (data == null) {
			out[0] = "";
		} else {
			if ("date".equalsIgnoreCase(type)) {
				Calendar cal = BSDateTimeUtil.string2Calendar(data, "yyyy-MM-dd");
				format = BSDateTimeUtil.getFormatDate(conn);
				out[0] = BSDateTimeUtil.calendar2String(cal, format);
				out[1] = "center";
			} else if ("double".equalsIgnoreCase(type)) {
				// format = getFormatDecimal(conn);
				Double dataDouble = Double.parseDouble(data);
				out[0] = formatDouble(conn, dataDouble);
				out[1] = "right";
			} else if ("int".equalsIgnoreCase(type)) {
				// format = getFormatInteger(conn);
				Integer dataInteger = Integer.parseInt(data);
				out[0] = formatInteger(conn, dataInteger);
				out[1] = "right";
			} else if ("bit".equalsIgnoreCase(type)) {
				if ("1".equals(data)) {
					out[0] = "Si";
				} else {
					out[0] = "No";
				}
				out[1] = "center";
			} else {
				out[0] = data; // + "(" + type + ")";
				out[1] = "left";
			}
		}

		return out;
	}

	static public String randomString() {
		long l = System.currentTimeMillis();
		String out = String.valueOf(l);
		out = Base64.encode(out.getBytes());
		return out;
	}
}
