<%@ taglib prefix="shop" tagdir="/WEB-INF/tags/shop"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Edit Profile</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<shop:css-imports-main/>
	<shop:css-imports-signin/>
</head>
<body>
	
	<shop:header/>
	
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<form class="login100-form validate-form" action="edit-profile" method="POST">
					<span class="login100-form-title p-b-26">
						Update Profile 
					</span>
					
					<div class="wrap-input100 validate-input" data-validate = "Enter First Name">
						<input class="input100 has-val" type="text" name="firstName" value="${loggedInUser.firstName}">
						<span class="focus-input100" data-placeholder="First Name"></span>
					</div>
					
					<div class="wrap-input100 validate-input" data-validate = "Enter Last Name">
						<input class="input100 has-val" type="text" name="lastName" value="${loggedInUser.lastName}">
						<span class="focus-input100" data-placeholder="Last Name"></span>
					</div>
					
					<div class="wrap-input100 validate-input" data-validate = "Valid email is: a@b.c">
						<input class="input100 has-val" type="email" name="email" value="${loggedInUser.email}">
						<span class="focus-input100" data-placeholder="Email"></span>
					</div>

					<div class="wrap-input100 validate-input" data-validate="Enter old password">
						<span class="btn-show-pass">
							<i class="zmdi zmdi-eye"></i>
						</span>
						<input class="input100 has-val" type="password" name="password">
						<span class="focus-input100" data-placeholder="Enter old password"></span>
					</div>
					
					<div class="wrap-input100">
						<span class="btn-show-pass">
							<i class="zmdi zmdi-eye"></i>
						</span>
						<input class="input100 has-val" type="password" name="newPassword">
						<span class="focus-input100" data-placeholder="Enter new password (optional)"></span>
					</div>

					<div class="container-login100-form-btn">
						<div class="wrap-login100-form-btn">
							<div class="login100-form-bgbtn"></div>
							<button class="login100-form-btn">
								Update
							</button>
						</div>
					</div>
					
					<c:if test="${errMsg != null}">
						<div class="container-login100-form-btn">
							<span class="txtErr">${errMsg}</span>
						</div>
					</c:if>
					<c:remove var="errMsg"/>
					

					<div class="text-center p-t-115">
						<span class="txt1">
							Don't want to edit profile?
						</span>

						<a class="txt2" href="my-profile">
							Back to my profile
						</a>
					</div>
				</form>
			</div>
		</div>
	</div>

	
	<shop:footer/>
	<shop:js-imports-signin/>

</body>
</html>