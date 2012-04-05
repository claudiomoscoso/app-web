package cl.buildersoft.web.servlet.testAsync;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestFloat
 */
@WebServlet("/TestFloat")
public class TestFloat extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TestFloat() {
		super();
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		final AsyncContext asyncContext = request.startAsync();

		asyncContext.start(new Runnable() {
			public void run() { // From here, its executed in a long thread.
				try {
					PrintWriter w = asyncContext.getResponse().getWriter();
					w.println("...some result...1");
					Thread.sleep(5000);
					w.println("...some result...2");
					w.flush();
					asyncContext.complete();
				} catch (Exception e) {
				}
			}
		});

	}

}
