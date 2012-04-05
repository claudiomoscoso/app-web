package cl.buildersoft.web.servlet.testAsync;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/TestListener" }, asyncSupported = true)
public class TestListener extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8677021697446198464L;
	Map<String, Set<AsyncContext>> auctionMapWatchers = new ConcurrentHashMap<String, Set<AsyncContext>>();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String auctionId = request.getParameter("auctionId");
		Set<AsyncContext> watchers = auctionMapWatchers.get(auctionId);
		if (watchers == null) { // First watcher for that auction.
			// watchers = new HashSet<AsyncContext>(); -- this Set is not
			// threadsafe.
			// The Set below is threadsafe.
			watchers = Collections
					.newSetFromMap(new ConcurrentHashMap<AsyncContext, Boolean>());
			auctionMapWatchers.put(auctionId, watchers);
		}

		final AsyncContext asyncContext = request.startAsync();
		watchers.add(asyncContext);
	}
}