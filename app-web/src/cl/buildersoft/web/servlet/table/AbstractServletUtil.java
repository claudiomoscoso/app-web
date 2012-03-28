package cl.buildersoft.web.servlet.table;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;

import cl.buildersoft.framework.beans.BSField;

public class AbstractServletUtil extends HttpServlet {
	private static final long serialVersionUID = -34792656017725168L;

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
/**<code>
	protected Boolean isId(String s) {
		return "id".equalsIgnoreCase(s) || "cid".equalsIgnoreCase(s);
	}
</code>*/
	protected String getCommas(BSField[] fields) {
		String out = "";
		for (int i = 0; i < fields.length; i++) {
			out += "?,";
		}
		out = out.substring(0, out.length() - 1);
		return out;
	}

	protected String unSplit(BSField[] tableFields, String s) {
		String out = "";
		for (BSField f : tableFields) {
			out += f.getName() + s;
		}
		out = out.substring(0, out.length() - 1);
		return out;
	}

	protected List<Object> array2List(Object... prms) {
		List<Object> out = new ArrayList<Object>();

		for (Object o : prms) {
			out.add(o);
		}

		return out;
	}

}
