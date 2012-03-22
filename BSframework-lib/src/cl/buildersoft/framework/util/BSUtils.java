package cl.buildersoft.framework.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BSUtils {
	protected List<Object> array2List(Object... prms) {
		List<Object> out = new ArrayList<Object>();

		for (Object o : prms) {
			out.add(o);
		}

		return out;
	}

	protected Object[] list2Array(List<Object> prms) {
		Object[] out = new Object[prms.size()];

		int i = 0;
		for (Object o : prms) {
			out[i++] = o;
		}

		return out;
	}

	protected String calendar2String(Calendar calendar) {
		String out = calendar.get(Calendar.YEAR) + "-"
				+ (calendar.get(Calendar.MONTH) + 1) + "-"
				+ calendar.get(Calendar.DAY_OF_MONTH) + " "
				+ calendar.get(Calendar.HOUR_OF_DAY) + ":"
				+ calendar.get(Calendar.MINUTE) + " - "
				+ (new Date(calendar.getTimeInMillis()).toString());
		return out;
	}

	protected Calendar date2Calendar(java.util.Date date) {
		Calendar out = Calendar.getInstance();
		out.setTimeInMillis(date.getTime());
		return out;
	}

	protected Calendar string2Calendar(String dateString, String format)
			throws ParseException {
		DateFormat formatter = new SimpleDateFormat(format);
		java.util.Date date = (java.util.Date) formatter.parse(dateString);

		Calendar out = date2Calendar(date);

		// System.out.println(calendar2String(out));
		date = null;
		return out;
	}

}
