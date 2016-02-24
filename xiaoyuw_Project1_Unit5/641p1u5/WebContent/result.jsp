<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Here is what you selected:</title>
</head>
<body>
	<h1>Here is what you selected:</h1>
	<%@ page import="model.*"%>
	<%!Automobile at;%>

	<%
		at = (Automobile) request.getAttribute("model");
		String[] optsets = at.getOptsets();
		String opt = null;
		float price;
	%>

	<table BORDER=1>
		<tr>
			<td><%=at.getName()%></td>
			<td>base price</td>
			<td><%=at.getBaseprice()%></td>
			<%
				for (String optset : optsets) {
					opt = at.getOptionChoice(optset);
					price = at.getOptionChoicePrice(optset);
					out.println("<tr>");
					out.println("<td>" + optset + "</td>");
					out.println("<td>" + opt + "</td>");
					out.println("<td>" + price + "</td>");
					out.println("</tr>");
				}
				out.println("<tr>");
				out.println("<td>" + "Total Cost" + "</td>");
				out.println("<td>" + "" + "</td>");
				out.println("<td>" + at.getTotalPrice() + "</td>");
			%>
		
	</table>
</body>
</html>