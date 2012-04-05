package cl.buildersoft.web.servlet.testAsync;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestShout")
public class TestShout extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5404285019953279795L;
	Map<String, Set<AsyncContext>> auctionMapWatchers = new ConcurrentHashMap<String, Set<AsyncContext>>();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String auctionId = request.getParameter("auctionId");
		String price = request.getParameter("price");
		Set<AsyncContext> watchers = auctionMapWatchers.get(auctionId);
		if (watchers != null) { // Some browser is watching

			for (AsyncContext ac : watchers) {
				try {
					PrintWriter acWriter = ac.getResponse().getWriter();
					// This line should probably better print the new price as
					// JSON data
					acWriter.println("new price is " + price);
					acWriter.flush();
				} catch (IOException ex) { // The watcher does not watch
											// anymore.
					watchers.remove(ac);
				}
			}

			if (watchers.isEmpty()) { // No more watcher for that auction
				auctionMapWatchers.remove(auctionId);
			}
		}
		response.getWriter().print("OK " + price);
	}
}
