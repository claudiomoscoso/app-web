package cl.buildersoft.framework.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import cl.buildersoft.framework.exception.BSConfigurationException;

// @ WebServlet("/StartupServlet")
public class StartupServlet extends HttpServlet {
	private static final String RESOURCE_NAME = "/WEB-INF/app.properties";
	private static final long serialVersionUID = 5170373024798450924L;

	public StartupServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		readFromPropertiesFile(config);

		/**
		 * <code>
		BSFactory factory = new BSFactory();
		ConfigService cfg = factory.getConfigService(config.getServletContext());
		Feature login = cfg.getFeature("LOGIN");

		System.out.println(login.toString());
		 */

		/**
		 * <code>
		Enumeration<String> e = config.getServletContext().getAttributeNames();
		while (e.hasMoreElements()) {
			String value = (String) e.nextElement();
			if (!"org.apache.tomcat.util.scan.MergedWebXml".equals(value)) {
				System.out.println(value + " = " + config.getServletContext().getAttribute(value));
			}
		}
 		 </code>
		 */

	}

	private void readFromPropertiesFile(ServletConfig config) {
		ServletContext ctx = config.getServletContext();
		Properties props = new Properties();

		InputStream resource = ctx.getResourceAsStream(RESOURCE_NAME);
		if (resource != null) {
			try {
				props.load(resource);
			} catch (IOException e) {
				throw new BSConfigurationException(e);
			}

			Set<Object> keys = props.keySet();
			String key = null;

			Iterator<Object> itr = keys.iterator();
			while (itr.hasNext()) {
				key = (String) itr.next();
				ctx.setAttribute(key, props.getProperty(key));
			}
		} else {
			System.out.println("Resource [" + RESOURCE_NAME + "] not found.");
		}
	}

}
