<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="footerStyle" required="true" %>
<%@ attribute name="footerRegular" fragment="true"%>
<%@ attribute name="footerContactForm" fragment="true"%>

<div>
    <div>    
        <jsp:doBody/>                            
    </div>
	<c:if test="${footerStyle eq 'regular'}">
		<div>
        	<jsp:invoke fragment="footerRegular"/>
    	</div>  
	</c:if>
	
	<c:if test="${footerStyle eq 'contact-form'}">
		<div>
        	<jsp:invoke fragment="footerContactForm"/>
    	</div>  
	</c:if>
</div>