<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>
<%@ page import="com.itbulls.learnit.onlinestore.persistence.enteties.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP Demo</title>
</head>
<body>

<%-- JSP Comment --%>
<!-- HTML Comment -->


<%! int tenIntValue = 10; 
	
	int increaseByTen(int intValue) {
		return intValue + this.tenIntValue;
	}
	
	String convertArrayToString(Object[] arr) {
		return Arrays.toString(arr);
	}
%>



<p>Result of "increaseByTen(20)" method invocation: <%= increaseByTen(20) %> </p>
<p>Result of "convertArrayToString({"Apple", "Orange", "Banana"})" method invocation: <%= convertArrayToString(new String[]{"Apple", "Orange", "Banana"}) %> </p>




<% if (request.getAttribute("users") != null) {%>
<table border="1">
	<% 
	
	List<User> users = (List<User>)request.getAttribute("users");
	for (User user : users ) {
	
	%>
	
	<tr>
		<td>
			<%= user.getId() %>
		</td>
		<td>
			<%= user.getFirstName() %>
		</td>
		<td>
			<%= user.getLastName() %>
		</td>
		<td>
			<%= user.getEmail() %>
		</td>
	</tr>
	
	<% } %>

</table>
<% } %>


<jsp:include page="jsp-include-demo.jsp"/>

<p>Addition in the EL 3 + 5 : ${3 + 5}</p>
<p>Extracting of "hello" property from requestScope with EL: ${requestScope.hello}</p>
<p>Extracting of "hello" property with EL: ${hello}</p>
<p>Verification if users in request scope are empty with EL: ${empty users} </p>
<p>Verification if users in request scope are not empty with EL: ${not empty users} </p>
<p>Email property of the user attribute: ${user.email} </p>



</body>
</html>