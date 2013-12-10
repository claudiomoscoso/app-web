<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String random = "" + Math.random();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=random%></title>

</head>
<body>
título
	<table border="1">
		<tr>
			<td>hola</td>
			<td>mundo</td>
		</tr>
		<LINK rel="stylesheet" type="text/css" href="css/1.css?<%=random%>" />
		<tr>			
			<td colspan=2>&nbsp;</td>
		</tr>
	</table>

</body>
</html>