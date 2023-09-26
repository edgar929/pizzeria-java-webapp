<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <h1>Ordered Pizza</h1>
	<% String options[] = (String[]) request.getAttribute("ingredients");%>
<table>
	<tr>
		<td>Pizzeria:</td>
		<td>${pizzeria}</td>
	</tr>
	<tr>
	   <% for (String opt: options){ %>
		<td>-></td>
		<td><%=opt %></td>
		<% }%>
	</tr>
	<tr>
		<td>Delivery Mode:</td>
		<td>${deliveryMode}</td>
	</tr>
	<tr>
		<td>Size:</td>
		<td>${size}</td>
	</tr>
	<tr>
		<td>Total price:</td>
		<td>${totalPrice}</td>
	</tr>
</table>

</body>
</html>