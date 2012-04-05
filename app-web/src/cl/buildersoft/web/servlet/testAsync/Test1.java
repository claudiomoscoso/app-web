package cl.buildersoft.web.servlet.testAsync;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/Test1" }, asyncSupported = true)
public class Test1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Test1() {
		super();
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		AsyncContext asyncContext = request.startAsync();
		PrintWriter w = asyncContext.getResponse().getWriter();
		w.print("Hola Mundo...");
		w.flush();
		asyncContext.complete();
		
	}

}
