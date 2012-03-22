<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true" import="java.io.PrintWriter"%>

<%@ include file="/WEB-INF/jsp/common/head.jsp"%>

<h1 class="cTitle" style="color: red">Error</h1>

<pre>
  <%
  	// unwrap ServletExceptions.
  	while (exception instanceof ServletException)
  		exception = ((ServletException) exception).getRootCause();

  	// print stack trace.
  	out.print(exception.getMessage());
  %>
  </pre>

<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>