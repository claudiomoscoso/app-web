package cl.buildersoft.lib.util;

import java.util.ArrayList;
import java.util.List;

import cl.buildersoft.lib.util.crud.BSField;

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

	public static String unSplitString(String[] names, String c) {
		String out = "";
		for (String s : names) {
			out += s + c;
		}
		out = out.substring(0, out.length() - c.length());
		return out;
	}

	public static String unSplitField(BSField[] fields, String c) {
		String out = "";
		for (BSField f : fields) {
			out += f.getName() + c;
		}
		out = out.substring(0, out.length() - c.length());
		return out;
	}
	
	
}
