<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="tab-pane fade" id="tab2default">
	<div class="about-box">
		<h2>My Referrals</h2>

		<c:if test="${not empty referrals}">
			<table class="order-table">
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
				</tr>
				<c:forEach items="${referrals}" var="referral">
					<tr>
						<td>${referral.firstName}</td>
						<td>${referral.lastName}</td>
						<td>${referral.email}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		
		<c:if test="${empty referrals}">
			Sorry, you don't have referrals yet. Use your partner link and invite referrals.
		</c:if>

	</div>
</div>