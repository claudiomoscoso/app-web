<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%=getServletContext().getContextPath() %>
<%
request.getSession().invalidate();

response.sendRedirect(getServletContext().getContextPath());
%>