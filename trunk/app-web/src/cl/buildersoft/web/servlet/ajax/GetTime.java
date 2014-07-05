package cl.buildersoft.web.servlet.ajax;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.lib.util.BSDateTimeUtil;

@WebServlet("/servlet/ajax/GetTime")
public class GetTime extends AbstractAjaxServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Calendar c = Calendar.getInstance();
		Integer sec = c.get(Calendar.SECOND);
		String out = null;

		setHeaders(response);
		if (sec < 10) {
			response.setStatus(400);
			out = "Error al obtener la hora";
		} else {
			out = BSDateTimeUtil.calendar2String(c, "dd/MM/yyyy hh:mm:ss");
		}

		endWrite(writeToBrowser(response, out));
	}

}
