<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="tab-pane fade in active" id="tab1default">
	<div class="product-box-main row">
		<div class="col-md-12 col-sm-18">
			<table class="my-profile-table">
				<tr>
					<td><b>First Name:</b></td>
					<td>${loggedInUser.firstName}</td>
				</tr>
				<tr>
					<td><b>Last Name:</b></td>
					<td>${loggedInUser.lastName}</td>
				</tr>
				<tr>
					<td><b>Email:</b></td>
					<td>${loggedInUser.email}</td>
				</tr>
				<tr>
					<td><b>Money:</b></td>
					<td>${loggedInUser.money}</td>
				</tr>
				<tr>
					<td><b>Role:</b></td>
					<td>${loggedInUser.roleName}</td>
				</tr>
				<tr>
					<td><b>Partner Code:</b></td>
					<td>${loggedInUser.partnerCode}</td>
				</tr>
				<tr>
					<td><b>Partner Link (Use this link to invite referrals):</b></td>
					<td><a href="${partnerLink}">${partnerLink}</a></td>
				</tr>
				
				
			</table>
			
		</div>

	</div>

</div>