<%=getServletContext().getContextPath() %>
<%
request.getSession().invalidate();

response.sendRedirect(getServletContext().getContextPath());
%>