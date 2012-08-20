package cl.buildersoft.framework.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cl.buildersoft.framework.exception.BSProgrammerException;

public class BSUtils {
	public static List<Object> array2List(Object... prms) {
		List<Object> out = new ArrayList<Object>();

		for (Object o : prms) {
			out.add(o);
		}

		return out;
	}

	public static Object[] list2Array(List<Object> prms) {
		Object[] out = new Object[prms.size()];

		int i = 0;
		for (Object o : prms) {
			out[i++] = o;
		}

		return out;
	}

	public static String calendar2String(Calendar calendar) {
		String out = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
				+ calendar.get(Calendar.DAY_OF_MONTH) + " " + calendar.get(Calendar.HOUR_OF_DAY) + ":"
				+ calendar.get(Calendar.MINUTE) + " - " + (new Date(calendar.getTimeInMillis()).toString());
		return out;
	}

	public static Calendar date2Calendar(java.util.Date date) {
		Calendar out = Calendar.getInstance();
		out.setTimeInMillis(date.getTime());
		return out;
	}

	public static Calendar string2Calendar(String dateString, String format) {
		DateFormat formatter = new SimpleDateFormat(format);
		Calendar out = null;
		try {
			java.util.Date date = (java.util.Date) formatter.parse(dateString);
			out = date2Calendar(date);
			date = null;
		} catch (ParseException e) {
			throw new BSProgrammerException(e);
		}
		// System.out.println(calendar2String(out));

		return out;
	}

	public static Boolean isValidDate(String date, String format) {
		Boolean out = Boolean.TRUE;
		SimpleDateFormat sdf = new SimpleDateFormat(format);

		Date testDate = null;

		try {
			testDate = sdf.parse(date);
		} catch (ParseException e) {
			out = Boolean.FALSE;
		}

		if (!sdf.format(testDate).equals(date)) {
			out = Boolean.FALSE;
		}

		return out;

	}
}
