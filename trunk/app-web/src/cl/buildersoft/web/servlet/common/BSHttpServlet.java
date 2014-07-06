package cl.buildersoft.web.servlet.common;

import java.util.ArrayList;
import java.util.List;

import cl.buildersoft.lib.util.crud.BSField;
import cl.buildersoft.sso.filter.BSHttpServletSSO;

public abstract class BSHttpServlet extends BSHttpServletSSO {
	private static final long serialVersionUID = 1537122543903845585L;

	protected String unSplit(BSField[] tableFields, String s) {
		String out = "";
		for (BSField f : tableFields) {
			out += f.getName() + s;
		}
		out = out.substring(0, out.length() - 1);
		return out;
	}

	public List<Object> array2List(Object... prms) {
		List<Object> out = new ArrayList<Object>();

		for (Object o : prms) {
			out.add(o);
		}

		return out;
	}

	protected String getFieldsNamesWithCommas(BSField[] fields) {
		String out = "";
		if (fields.length == 0) {
			out = "*";
		} else {
			for (BSField field : fields) {
				out += field.getName() + ",";
			}
			out = out.substring(0, out.length() - 1);
		}
		return out;
	}
}
