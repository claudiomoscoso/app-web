package cl.buildersoft.web.servlet.common;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.lib.exception.BSProgrammerException;
import cl.buildersoft.lib.util.ReflectionUtils;
import cl.buildersoft.lib.util.crud.BSField;

public class AbstractServletUtil extends HttpServlet {
	private static final long serialVersionUID = -34792656017725168L;



	/**
	 * <code>
	protected Boolean isId(String s) {
		return "id".equalsIgnoreCase(s) || "cid".equalsIgnoreCase(s);
	}
</code>
	 */
	protected String getCommas(BSField[] fields) {
		String out = "";
		for (int i = 0; i < fields.length; i++) {
			out += "?,";
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

	public String getCommas(String[] tableFields) {
		String out = "";
		for (int i = 0; i < tableFields.length; i++) {
			out += "?,";
		}
		out = out.substring(0, out.length() - 1);
		return out;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object[] parameters = { request, response };
		String servletName = "cl.buildersoft.web." + request.getRequestURI().substring(12).replaceAll("/", ".");
		String methodName = request.getParameter("Method");
		Object servletClazz;

		try {
			servletClazz = Class.forName(servletName).newInstance();
			ReflectionUtils.execute(methodName, servletClazz, parameters);
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new BSProgrammerException(e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new BSProgrammerException(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new BSProgrammerException(e);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new BSProgrammerException(e);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			throw new BSProgrammerException(e);
		}

	}

	protected ServletOutputStream configHeaderAsCSV(HttpServletResponse response, String fileName) throws IOException {
		ServletOutputStream output = response.getOutputStream();
		response.setContentType("text/csv");
		String disposition = "attachment; fileName=" + fileName + ".csv";
		response.setHeader("Content-Disposition", disposition);
		return output;
	}

}
