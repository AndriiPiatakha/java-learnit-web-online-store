<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="col-md-12 col-sm-24">
	<div class="md-prod-page">
		<div class="description-box">
			<div class="dex-a">
				<table class="order-table">
					<tr>
						<th>Order Id</th>
						<th>User Email</th>
						<th>Order Status</th>
						<th>Action Button</th>
					</tr>

					<c:forEach items="${purchases}" var="purchase">
						<tr>
							<td>${purchase.id}</td>
							<td>${purchase.customer.email}</td>
							<td>${purchase.purchaseStatus.statusName}</td>
							<td>
								<form action="management-fulfilment" method="POST">
									<input type="hidden" value="${purchase.id}" name="purchaseId"/>
									<input class="custom-b" type="submit" value="Mark Fulfilment Stage as Completed"/>
								</form>
							</td>
						</tr>

					</c:forEach>

				</table>
			</div>

		</div>
	</div>
</div>