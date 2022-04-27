<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shop" tagdir="/WEB-INF/tags/shop"%>

<div class="col-md-6 col-sm-8">
	<div class="furniture-middle">
	
	
		<c:forEach items="${products}" var="product">
			<div class="big-box">
				<div class="big-img-box">
					<img src="images/product/${product.imgName}" alt="#" />
				</div>
				<div class="big-dit-b clearfix">
					<div class="col-md-6">
						<div class="left-big">
							<h3>${product.productName}</h3>
						</div>
					</div>
					<div class="col-md-6">
						<div class="right-big-b">
							<div class="tight-btn-b clearfix">
								<a class="view-btn" href="product?id=${product.id}">View</a> <a
									href="product?id=${product.id}">$${product.price}</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
		
		<shop:pagination/>
		
	</div>
</div>