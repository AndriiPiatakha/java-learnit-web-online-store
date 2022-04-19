<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ attribute name="coll" type="java.util.Collection" required="true"%>

<table border="1">
<c:forEach items="${coll}" var="item">
	<tr>
		<td>
			${item}
		</td>
	</tr>
</c:forEach>
</table>