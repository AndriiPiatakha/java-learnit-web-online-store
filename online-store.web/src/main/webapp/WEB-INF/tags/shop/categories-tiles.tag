<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="page-content-product">
	<div class="main-product">
		<div class="container">

			<div class="row clearfix">
				<c:forEach items="${categories}" var="category">
					<div class="col-lg-3 col-sm-6 col-md-3">
						<a href="category?id=${category.id}&page=1">
							<div class="box-img">
								<h4>${category.categoryName}</h4>
								<img src="images/category/${category.imgName}" alt="" />
							</div>
						</a>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>