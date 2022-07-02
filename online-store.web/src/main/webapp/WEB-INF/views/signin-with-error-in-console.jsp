<%@ taglib prefix="shop" tagdir="/WEB-INF/tags/shop"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Sign In</title>
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
				<form class="login100-form validate-form" action="signin-security-misconfiguration" method="POST">
					<span class="login100-form-title p-b-26">
						Welcome
					</span>

					<div class="wrap-input100 validate-input" data-validate = "Valid email is: a@b.c">
						<input class="input100 has-val" type="text" name="email">
						<span class="focus-input100" data-placeholder="Email"></span>
					</div>

					<div class="wrap-input100 validate-input" data-validate="Enter password">
						<span class="btn-show-pass">
							<i class="zmdi zmdi-eye"></i>
						</span>
						<input class="input100 has-val" type="password" name="password">
						<span class="focus-input100" data-placeholder="Password"></span>
					</div>

					<div class="container-login100-form-btn">
						<div class="wrap-login100-form-btn">
							<div class="login100-form-bgbtn"></div>
							<button id="sign-in-btn" class="login100-form-btn">
								Sign In
							</button>
						</div>
					</div>

					<div class="text-center p-t-115">
						<span class="txt1">
							Don't have an account?
						</span>

						<a class="txt2" href="signup">
							Sign Up
						</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<shop:footer/>
	<shop:js-imports-signin/>

	<script type="text/javascript">
		$('#sign-in-btn').click(function(event) {
			event.preventDefault();
			// this code is for the demo and example purposes only. It prints hardcoded values just for the sake of example
			console.log('Password "testtest*asd" for user {email: "manager@manager.com", password: "testtest*"} is not correct');
		});
	</script>
</body>
</html>