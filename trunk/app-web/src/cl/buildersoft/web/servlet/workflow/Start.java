package cl.buildersoft.web.servlet.workflow;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.business.workflow.beans.Flow;
import cl.buildersoft.business.workflow.service.Workflow;
import cl.buildersoft.business.workflow.service.impl.WorkflowImpl;
import cl.buildersoft.lib.beans.User;

@WebServlet("/servlet/workflow/Start")
public class Start extends HttpServlet {
	private static final long serialVersionUID = -4732856857403554178L;

	public Start() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("User");

		Workflow wf = new WorkflowImpl();
		List<Flow> flows = wf.getFlows(user);

		request.setAttribute("Flows", flows);

		request.getRequestDispatcher("/WEB-INF/jsp/workflow/start.jsp").forward(request, response);
	}

}
