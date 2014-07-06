package cl.buildersoft.sso.filter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import cl.buildersoft.lib.database.BSBeanUtils;
import cl.buildersoft.lib.exception.BSConfigurationException;
import cl.buildersoft.lib.exception.BSSystemException;
import cl.buildersoft.lib.util.BSDataUtils;
import cl.buildersoft.sso.bean.SessionBean;
import cl.buildersoft.sso.bean.SessionDataBean;

public class BSHttpServletSSO extends HttpServlet {
	private static final String ROOT = "/";

	private static final long serialVersionUID = -3697395018919620104L;

	private static final String PASSWORD = "bsframework.database.password";
	private static final String USERNAME = "bsframework.database.username";
	private static final String DATABASE = "bsframework.database.database";
	private static final String PORT = "bsframework.database.port";
	private static final String SERVER = "bsframework.database.server";
	private static final String DRIVER = "bsframework.database.driver";
	private static final String DATASOURCE = "bsframework.datasource";
	public static String SESSION_COOKIE_NAME = "SESSION_SSO";
	protected static final int MAX_AGE = 24 * 60 * 60;
	private String dataSourceName = null;
	private String driver = null;
	private String server = null;
	private String port = null;
	private String database = null;
	private String username = null;
	private String password = null;

	protected void forward(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException,
			IOException {
		updateSession(request, response);
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void redirect(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException,
			IOException {
		updateSession(request, response);
		response.sendRedirect(url);
	}

	public void init(ServletConfig config) throws ServletException {
		ServletContext ctx = config.getServletContext();

		this.dataSourceName = (String) ctx.getAttribute(DATASOURCE);
		this.database = (String) ctx.getAttribute(DRIVER);
		this.server = (String) ctx.getAttribute(SERVER);
		this.port = (String) ctx.getAttribute(PORT);
		this.database = (String) ctx.getAttribute(DATABASE);
		this.username = (String) ctx.getAttribute(USERNAME);
		this.password = (String) ctx.getAttribute(PASSWORD);

		/**
		 * <code>
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
		</code>
		 */
	}

	private boolean thereDataSource() {
		return this.dataSourceName != null && this.dataSourceName.trim().length() > 0;
	}

	public Connection getConnection(HttpServletRequest request) throws BSConfigurationException {
		BSDataUtils dau = new BSDataUtils();

		String currentDataSource = (String) request.getSession().getAttribute("CurrentDatasource");

		Connection conn = dau.getConnection2(request.getServletContext(), currentDataSource);
		return conn;
	}

	public Connection getConnection() throws BSConfigurationException {
		Connection conn = null;

		if (thereDataSource()) {
			try {
				Context initContext = new InitialContext();
				Context envContext = (Context) initContext.lookup("java:/comp/env");
				DataSource ds = (DataSource) envContext.lookup(this.dataSourceName);
				conn = ds.getConnection();
			} catch (Exception e) {
				throw new BSConfigurationException(e);
			}
		} else {
			String uri = "jdbc:mysql://" + this.server + ":" + this.port + ROOT + database;
			try {
				Class.forName(this.driver);
				conn = DriverManager.getConnection(uri, this.username, this.password);
			} catch (Exception e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bsframework", "root", "admin");
				} catch (Exception ex) {
					throw new BSConfigurationException(ex);
				}
			}
		}
		return conn;
	}

	protected HttpSession createSession(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		String sessionId = session.getId();
		saveCookieToResponse(response, sessionId);
		request.setAttribute(SESSION_COOKIE_NAME, sessionId);
		saveSessionToDB(getConnection(), session, sessionId);
		return session;
	}

	protected void deleteSession(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String sessionId = session.getId();

			saveCookieToResponse(response, sessionId, true);

			deleteSessionOfDB(session, sessionId);
			session.invalidate();
		}
	}

	public void updateSession(HttpServletRequest request, HttpServletResponse response) {
		String cookie = readCookieValue(request);
		if (cookie != null) {
			HttpSession session = request.getSession(false);

			if (session != null) {
				Connection conn = getConnection();
				saveSessionToDB(conn, session, cookie);
			}
			saveCookieToResponse(response, cookie);
		}
	}

	public void restoreSession(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cookie = readCookieValue(request);

		if (cookie == null) {
			redirect(request, response, ROOT);
			// request.getRequestDispatcher("Invaliduser.jsp").forward(request,
			// response);
		} else {
			HttpSession session = request.getSession(true);
			Connection conn = getConnection();
			readSessionDataFromDB(conn, session, cookie);
		}

	}

	protected void deleteSessionOfDB(HttpSession session, String sessionId) {
		SessionBean sessionBean = new SessionBean();
		SessionDataBean sessionDataBean = new SessionDataBean();
		BSBeanUtils bu = new BSBeanUtils();
		Connection conn = getConnection();

		bu.search(conn, sessionBean, "cSessionId=?", sessionId);

		List<SessionDataBean> dataList = getSessionData(conn, bu, sessionBean, sessionDataBean);

		for (SessionDataBean data : dataList) {
			bu.delete(conn, data);
		}
		bu.delete(conn, sessionBean);

	}

	protected SessionBean saveSessionToDB(Connection conn, HttpSession session, String sessionId) {
		SessionBean sessionBean = new SessionBean();
		SessionDataBean sessionDataBean = null;
		BSBeanUtils bu = new BSBeanUtils();

		sessionBean.setSessionId(sessionId);
		bu.search(conn, sessionBean, "cSessionId=?", sessionId);
		sessionBean.setLastAccess(new Timestamp(System.currentTimeMillis()));
		bu.save(conn, sessionBean);

		Enumeration<String> names = session.getAttributeNames();
		String name = null;

		while (names.hasMoreElements()) {
			name = names.nextElement();
			sessionDataBean = new SessionDataBean();

			sessionDataBean.setSession(sessionBean.getId());
			bu.search(conn, sessionDataBean, "cSession=? AND cName=?", sessionBean.getId(), name);
			sessionDataBean.setSession(sessionBean.getId());

			sessionDataBean.setName(name);
			sessionDataBean.setData(objectToString(session.getAttribute(name)));

			// saveObjectToDB(conn, bu, sessionDataBean, sessionBean.getId(),
			// name, objectAsString);
			// saveObjectToDB(conn, sessionId, name, objectAsString);

			bu.save(conn, sessionDataBean);
		}
		return sessionBean;
	}

	/**
	 * <code>
	protected void saveObjectToDB(Connection conn, BSBeanUtils bu, SessionDataBean sessionDataBean, Long sessionId,
			String dataName, String object) {
		sessionDataBean.setId(sessionId);
		bu.search(conn, sessionDataBean);

		sessionDataBean.setName(dataName);
		sessionDataBean.setSession(sessionId);
		sessionDataBean.setData(object);

		bu.save(conn, sessionDataBean);
	}
</code>
	 */
	public String readCookieValue(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		String out = null;

		if (cookies != null) {
			for (Cookie currentCookie : cookies) {
				if (currentCookie.getName().equals(SESSION_COOKIE_NAME)) {
					out = currentCookie.getValue();
					break;
				}
			}
		}

		if (out == null) {
			Object obj = request.getAttribute(SESSION_COOKIE_NAME);
			request.setAttribute(SESSION_COOKIE_NAME, null);
			if (obj != null) {
				out = (String) obj;
			}
		}
		return out;
	}

	public String objectToString(Object obj) throws BSSystemException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
		} catch (IOException e) {
			throw new BSSystemException(e);
		}
		byte[] userAsBytes = baos.toByteArray();

		BASE64Encoder encoder = new BASE64Encoder();
		String out = encoder.encodeBuffer(userAsBytes);

		return out;
	}

	private Object stringToObject(String str) throws BSSystemException {
		BASE64Decoder decoder = new BASE64Decoder();
		ObjectInputStream ois = null;
		Object out = null;
		try {
			byte[] objectAsBytes = decoder.decodeBuffer(str);
			ByteArrayInputStream bais = new ByteArrayInputStream(objectAsBytes);
			ois = new ObjectInputStream(bais);
			out = ois.readObject();
		} catch (Exception e) {
			throw new BSSystemException(e);
		}

		return out;
	}

	public Cookie saveCookieToResponse(HttpServletResponse response, String sessionId) {
		return saveCookieToResponse(response, sessionId, false);
	}

	public Cookie saveCookieToResponse(HttpServletResponse response, String sessionId, Boolean delete) {
		Cookie cookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
		cookie.setPath(ROOT);
		cookie.setMaxAge(delete ? 0 : MAX_AGE);
		response.addCookie(cookie);
		return cookie;
	}

	private void readSessionDataFromDB(Connection conn, HttpSession session, String sessionValue) {
		BSBeanUtils bu = new BSBeanUtils();
		SessionBean sessionBean = new SessionBean();
		SessionDataBean sessionDataBean = new SessionDataBean();

		bu.search(conn, sessionBean, "cSessionId=?", sessionValue);

		List<SessionDataBean> objectList = getSessionData(conn, bu, sessionBean, sessionDataBean);
		Object obj = null;
		for (SessionDataBean record : objectList) {
			obj = stringToObject(record.getData());
			session.setAttribute(record.getName(), obj);
		}
	}

	@SuppressWarnings("unchecked")
	private List<SessionDataBean> getSessionData(Connection conn, BSBeanUtils bu, SessionBean sessionBean,
			SessionDataBean sessionDataBean) {

		return (List<SessionDataBean>) bu.list(conn, sessionDataBean, "cSession=?", sessionBean.getId());
	}
}