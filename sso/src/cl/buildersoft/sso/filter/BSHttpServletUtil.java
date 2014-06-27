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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.buildersoft.framework.database.BSBeanUtils;
import cl.buildersoft.sso.bean.SessionBean;
import cl.buildersoft.sso.bean.SessionDataBean;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class BSHttpServletUtil extends HttpServlet {
	private static final long serialVersionUID = -3697395018919620104L;
	public static String SESSION_COOKIE_NAME = "SessionCookie";
	protected static final int MAX_AGE = 24 * 60 * 60;

	public void saveSession(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Connection conn = getConnection();
		Cookie cookie = readCookie(request);

		HttpSession session = request.getSession(false);
		if (session != null) {
			saveSessionToDB(conn, session, cookie.getValue());
			saveCookieToResponse(response, cookie);
		}
	}

	public void restoreSession(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Cookie cookie = readCookie(request);

		if (cookie == null) {
			request.getRequestDispatcher("Invaliduser.jsp").forward(request, response);
		} else {
			HttpSession session = request.getSession(true);
			Connection conn = getConnection();
			readSessionDataFromDB(conn, session, cookie.getValue());
		}

	}

	protected SessionBean saveSessionToDB(Connection conn, HttpSession session, String sessionId) throws Exception {
		SessionBean sessionBean = new SessionBean();
		SessionDataBean sessionDataBean = new SessionDataBean();
		BSBeanUtils bu = new BSBeanUtils();
		Enumeration<String> names = session.getAttributeNames();

		bu.search(conn, sessionBean, "cSessionId=?", sessionId);
		sessionBean.setLastAccess(new Timestamp(System.currentTimeMillis()));
		bu.save(conn, sessionBean);

		String name = null;
		String objectAsString = null;
		while (names.hasMoreElements()) {
			name = names.nextElement();
			objectAsString = objectToString(session.getAttribute(name));
			saveObjectToDB(conn, bu, sessionDataBean, sessionBean.getId(), name, objectAsString);
			// saveObjectToDB(conn, sessionId, name, objectAsString);
		}
		return sessionBean;
	}

	protected void saveObjectToDB(Connection conn, BSBeanUtils bu, SessionDataBean sessionDataBean, Long sessionId,
			String dataName, String object) {
		sessionDataBean.setId(sessionId);
		bu.search(conn, sessionDataBean);

		sessionDataBean.setName(dataName);
		sessionDataBean.setSession(sessionId);
		sessionDataBean.setData(object);

		bu.save(conn, sessionDataBean);
	}

	public Connection getConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bsframework", "root", "admin");

		return conn;
	}

	public Cookie readCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;

		for (Cookie currentCookie : cookies) {
			if (currentCookie.getName().equals(SESSION_COOKIE_NAME)) {
				cookie = currentCookie;
				break;
			}
		}
		return cookie;
	}

	public String objectToString(Object obj) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(obj);
		byte[] userAsBytes = baos.toByteArray();

		BASE64Encoder encoder = new BASE64Encoder();
		String out = encoder.encodeBuffer(userAsBytes);

		return out;
	}

	private Object stringToObject(String str) throws Exception {
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] objectAsBytes = decoder.decodeBuffer(str); // .encode(userAsBytes);

		ByteArrayInputStream bais = new ByteArrayInputStream(objectAsBytes);
		ObjectInputStream ois = new ObjectInputStream(bais);
		Object out = ois.readObject();

		return out;
	}

	public void saveCookieToResponse(HttpServletResponse response, Cookie cookie) {
		cookie.setPath("/");
		cookie.setMaxAge(MAX_AGE);
		response.addCookie(cookie);
	}

	private void readSessionDataFromDB(Connection conn, HttpSession session, String sessionValue) throws Exception {
		BSBeanUtils bu = new BSBeanUtils();
		SessionBean sessionBean = new SessionBean();
		SessionDataBean sessionDataBean = new SessionDataBean();

		bu.search(conn, sessionBean, "cSessionId=?", sessionValue);

		List<SessionDataBean> objectList = (List<SessionDataBean>) bu.list(conn, sessionDataBean, "cSession=?",
				sessionBean.getId());
		Object obj = null;
		for (SessionDataBean record : objectList) {
			obj = stringToObject(record.getData());
			session.setAttribute(record.getName(), obj);
		}
	}
}
