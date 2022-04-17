<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JSTL Demo</title>
</head>
<body>

	<p>========== Set and If Tags Demo ==========</p>
	<%-- scopes: page, request, session, application --%>
	<c:set var="testVar" value="100" scope="request"/>
	
	<c:if test="${testVar > 5}"> 	 	
		<p>Test Var exist on the page and it is more than 5</p>
	</c:if>
	
	
	<p>========== For each loop Demo ==========</p>
	<c:if test="${users != null}">
		<table border="1">
		<c:forEach items="${users}" var="user">
			<tr>
				<td>
					${user.id}
				</td>
				<td>
					${user.firstName}
				</td>
				<td>
					${user.lastName}
				</td>
				<td>
					${user.email}
				</td>
			</tr>
		</c:forEach>
		</table>
	</c:if>
	
	
	<p>========== Remove Tag Demo ==========</p>
	<c:remove var="testVar" />
	<p>After Remove Value: <c:out value="${testVar}" /></p>
	

	<p>========== Out Tag Demo ==========</p>
	<p>
		Let's print "<html> text" with "escapeXml" set to "false": 
		<c:out value="<html> text" escapeXml="false"/>
	</p>
	<p>
		Let's print "<html> text" with "escapeXml" set to "true":
		<c:out value="<html> text"  escapeXml="true"/>
	</p>
	<p>
		Let's print null value with "deafult" set to "default":
		<c:out value="${null}" default="default"></c:out>
	</p>
	
	
	
	<p>========== Exception Handling Demo ==========</p>
	<%-- CATCH DEMO --%>
	<c:catch var="catchException">
		<%
			int x = 5 / 0;
		%>
	</c:catch>

	<c:if test="${not empty catchException}">
		<p>
			The exception is : ${catchException} <br /> There is an exception:
			${catchException.message}
		</p>
	</c:if>
	
	
	<p>========== Choose Tag Demo ==========</p>
	<c:set var="salary" scope="session" value="10000" />
	<p>
		Your salary is :
		<c:out value="${salary}" />
	</p>
	<c:choose>
		<c:when test="${salary le 1000}">
            You have a good salary. 
         </c:when>

		<c:when test="${(salary gt 1000) and (salary lt 5000)}">
            Salary is very good.
         </c:when>

		<c:otherwise>
            I'm proud of you, sir :)
         </c:otherwise>
	</c:choose>
	
	<p>========== URL and Import Tags Demo ==========</p>
	<c:url value="jsp-include-demo.jsp" var="url" scope="session">
		<c:param name="partnerCode" value="123456" />
		<c:param name="couponCode" value="ASDFG" />
	</c:url>
	<p>URL is = ${url}</p>
	<c:import url="${url}" />
	
	
	<p>========== Formatting Tags Demo ==========</p>
	<h3>Number Format:</h3>
    <c:set var = "balance" value = "120000.2309" />
        
    <p>Formatted Number (1): <fmt:formatNumber value = "${balance}" 
       type = "currency"/></p>
       
    <p>Formatted Number (2): <fmt:formatNumber type = "number" 
       maxIntegerDigits = "3" value = "${balance}" /></p>
       
    <p>Formatted Number (3): <fmt:formatNumber type = "number" 
       maxFractionDigits = "3" value = "${balance}" /></p>
       
    <p>Formatted Number (4): <fmt:formatNumber type = "number" 
       groupingUsed = "false" value = "${balance}" /></p>
       
    <p>Formatted Number (5): <fmt:formatNumber type = "percent" 
       maxIntegerDigits="3" value = "${balance}" /></p>
       
    <p>Formatted Number (6): <fmt:formatNumber type = "percent" 
       minFractionDigits = "10" value = "${balance}" /></p>
       
    <p>Formatted Number (7): <fmt:formatNumber type = "percent" 
       maxIntegerDigits = "3" value = "${balance}" /></p>
       
    <p>Formatted Number (8): <fmt:formatNumber type = "number" 
       pattern = "###.###E0" value = "${balance}" /></p>
       
    <p>Currency in USA :
       <fmt:setLocale value = "en_US"/>
       <fmt:formatNumber value = "${balance}" type = "currency"/>
    </p>
    
    <%-- FORMAT DATE DEMO --%>
    <h3>Date Format:</h3>
    <c:set var = "now" value = "<%= new java.util.Date()%>" />

    <p>Formatted Date (1): <fmt:formatDate type = "time" 
       value = "${now}" /></p>
    
    <p>Formatted Date (2): <fmt:formatDate type = "date" 
       value = "${now}" /></p>
    
    <p>Formatted Date (3): <fmt:formatDate type = "both" 
       value = "${now}" /></p>
    
    <p>Formatted Date (4): <fmt:formatDate type = "both" 
       dateStyle = "short" timeStyle = "short" value = "${now}" /></p>
    
    <p>Formatted Date (5): <fmt:formatDate type = "both" 
       dateStyle = "medium" timeStyle = "medium" value = "${now}" /></p>
    
    <p>Formatted Date (6): <fmt:formatDate type = "both" 
       dateStyle = "long" timeStyle = "long" value = "${now}" /></p>
    
    <p>Formatted Date (7): <fmt:formatDate pattern = "yyyy-MM-dd" 
       value = "${now}" /></p>
       
       
     
    <p>========== Function Tags Demo ==========</p>
    <%--FN DEMO contains --%>
    <c:set var = "theString" value = "I am a test String"/>
    
    <c:if test = "${fn:contains(theString, 'test')}">
       <p>Found test string<p>
    </c:if>

    <c:if test = "${fn:contains(theString, 'TEST')}">
       <p>Found TEST string<p>
    </c:if>
    
     <c:if test = "${fn:containsIgnoreCase(theString, 'test')}">
       <p>Found test string<p>
    </c:if>

    <c:if test = "${fn:containsIgnoreCase(theString, 'TEST')}">
       <p>Found TEST string<p>
    </c:if>
    
    <%--FN ends with --%>
    <c:set var = "theString" value = "I am a test String 123"/>

    <c:if test = "${fn:endsWith(theString, '123')}">
       <p>String ends with 123<p>
    </c:if>

    <c:if test = "${fn:endsWith(theString, 'TEST')}">
       <p>String ends with TEST<p>
    </c:if>
    
    <%-- DEMO index of --%>
    <c:set var = "string1" value = "This is first String."/>
    <c:set var = "string2" value = "This <abc>is second String.</abc>"/>
    <p>indexOf tag Demo in String 1: ${fn:indexOf(string1, "first")}</p>
    <p>indexOf tag Demo in String 2: ${fn:indexOf(string2, "second")}</p>
    
    <%-- SPLIT and JOIN FN --%>
    <c:set var = "string1" value = "This is first String."/>
    <c:set var = "string2" value = "${fn:split(string1, ' ')}" />
    <c:set var = "string3" value = "${fn:join(string2, '-')}" />
    <p>Split and Join tags Demo: ${string3}</p>
    
    <%--FN LENGTH --%>
    
    <c:set var = "string1" value = "This is first String."/>
    <c:set var = "string2" value = "This is second String." />
    <p>Length of String (1) : ${fn:length(string1)}</p>
    <p>Length of String (2) : ${fn:length(string2)}</p>
    
    
    <%-- FN replace --%>
    <c:set var = "string1" value = "This is first String."/>
    <c:set var = "string2" value = "${fn:replace(string1, 'first', 'second')}" />
    <p>Replace tag demo: ${string2}</p>
    
    <%-- FN starts with --%>
    <c:set var = "string" value = "Second: This is first String."/>
    
    <c:if test = "${fn:startsWith(string, 'First')}">
       <p>String starts with First</p>
    </c:if>
    
    <c:if test = "${fn:startsWith(string, 'Second')}">
       <p>String starts with Second</p>
    </c:if>
    
    
    <p>========== SQL Tags Demo ==========</p>
    <sql:setDataSource var="dataSource" driver="com.mysql.cj.jdbc.Driver"
  			url="jdbc:mysql://localhost:3306/learn_it_db" user="root" password="root"/>
  			
  	<sql:query dataSource="${dataSource}" var="rs">
	    SELECT * FROM user;
	</sql:query>
	
	<table border="1">  
		<tr>  
			<th>ID</th>  
			<th>First Name</th>  
			<th>Last Name</th>  
			<th>Email</th>  
		</tr>  
	<c:forEach var="row" items="${rs.rows}">  
		<tr>  
			<td><c:out value="${row.id}"/></td>  
			<td><c:out value="${row.first_name}"/></td>  
			<td><c:out value="${row.last_name}"/></td>  
			<td><c:out value="${row.email}"/></td>  
		</tr>  
	</c:forEach>  
	</table>  
	
	
	
	<p>========== XML Tags Demo ==========</p>
	<c:set var="employees"> 
		<employees>
			<employee>
		        <firstname>John</firstname>
		        <lastname>Smith</lastname>
		        <role>Employee</role>
		        <salary>110000</salary>
		    </employee>
		    <employee>
		        <firstname>Jack</firstname>
		        <lastname>Jackson</lastname>
		        <role>Manager</role>
		        <salary>170000</salary>
		    </employee>
		</employees>
	</c:set> 
	<x:parse xml="${employees}" var="xml"/> 
	<p>The first name of the first employee from the XML:
		<x:out select="$xml/employees/employee[1]/firstname/text()" />
	</p>
	<p>The last name of the first employee from the XML:
		<x:out select="$xml/employees/employee[1]/lastname/text()" /> 
	</p>
		
		
	
	
</body>
</html>