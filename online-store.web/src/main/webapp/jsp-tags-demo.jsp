<%@ taglib prefix="g" tagdir="/WEB-INF/tags/general"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="custom" uri="/WEB-INF/custom.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP Tags Demo</title>
</head>
<body>

<g:footer/>

<p>==========</p>

<c:set var="arr" value="${['Apple', 'Banana', 'Orange']}"/>
<g:printArray coll="${arr}"/>

<p>==========</p>

<custom:helloTag/>
<custom:helloTag message="test message"/>
<custom:helloTag message="test message" coll="${arr}"/>

<p>==========</p>

<g:fragment-demo footerStyle="regular">
    <jsp:attribute name="footerRegular">
        This is regular footer fragment
    </jsp:attribute>
    <jsp:attribute name="footerContactForm">
        This is footer with contact form fragment
    </jsp:attribute>
    <jsp:body>
        This is body of JSP Custom Tag
    </jsp:body>
</g:fragment-demo>

<p>==========</p>

<g:fragment-demo footerStyle="contact-form">
    <jsp:attribute name="footerRegular">
        This is regular footer fragment
    </jsp:attribute>
    <jsp:attribute name="footerContactForm">
        This is footer with contact form fragment
    </jsp:attribute>
    <jsp:body>
        This is body of JSP Custom Tag
    </jsp:body>
</g:fragment-demo>

<p>==========</p>

<custom:coloredListItems item1="red" item2="green" item3="orange"/>

</body>
</html>