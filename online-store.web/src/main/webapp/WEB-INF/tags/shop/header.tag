<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="OnlineShopResourceBundle" var="rb"/>
<header id="header" class="top-head">
	<!-- Static navbar -->
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-4 col-sm-12 left-rs">
					<div class="navbar-header">
						<button type="button" id="top-menu"
							class="navbar-toggle collapsed" data-toggle="collapse"
							data-target="#navbar" aria-expanded="false">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a href="homepage" class="navbar-brand"><img
							src="images/logo.png" alt="" /></a>
					</div>
					<form class="navbar-form navbar-left web-sh" action="search"
						method="GET">
						<div class="form">
						<fmt:message key='search.placeholder' bundle='${rb}' var="searchPlaceholder"/>
							<input type="text" class="form-control" name="searchQuery"
								placeholder="${searchPlaceholder}"> 
							<input type="hidden" name="page" value="1">
						</div>
					</form>
				</div>
				<div class="col-md-8 col-sm-12">
					<div class="right-nav">
						<div class="login-sr">
							<div class="login-signup">
								<ul>
									<c:if test="${loggedInUser != null}">
										<li><a href="my-profile"><fmt:message key="welcome.lbl" bundle="${rb}"/> ${loggedInUser.firstName}</a></li>
										<li><a class="custom-b" href="signout"><fmt:message key="signout.btn" bundle="${rb}"/></a></li>
									</c:if>
									<c:if test="${loggedInUser == null}">
										<li><a href="signin"><fmt:message key="signin.btn" bundle="${rb}"/></a></li>
										<li><a class="custom-b" href="signup"><fmt:message key="signup.btn" bundle="${rb}"/></a></li>
									</c:if>
								</ul>
							</div>
						</div>
						<div class="help-r hidden-xs">
							<div class="help-box">
								<ul>
									<li><a data-toggle="modal" data-target="#myModal" href="#">
											<span><fmt:message key="change.lang.lbl" bundle="${rb}"/> </span> 
											<c:choose>
												<c:when test="${locale == 'fr'}">
													<img src="images/flag-fr.png" alt="" />
												</c:when>
												<c:otherwise>
													<img src="images/flag.png" alt="" />
												</c:otherwise>
											</c:choose>
											
									</a></li>

									<c:if test="${not empty loggedInUser}">
										<li><a href="my-profile"><fmt:message key="my.profile.lbl" bundle="${rb}"/></a></li>
									</c:if>

								</ul>
							</div>
						</div>
						<div class="nav-b hidden-xs"></div>
					</div>
				</div>
			</div>
		</div>
		<!--/.container-fluid -->
	</nav>
</header>

<!-- Modal -->
<div class="modal fade lug" id="myModal" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Change</h4>
			</div>
			<div class="modal-body">
				<ul>
					<li><a href="change-locale"><img src="images/flag-up-1.png" alt="" />
							United States</a></li>
					<li><a href="change-locale?locale=fr"><img src="images/flag-up-2.png" alt="" />
							France </a></li>
				</ul>
			</div>

		</div>
	</div>
</div>
<div id="sidebar" class="top-nav">
	<ul id="sidebar-nav" class="sidebar-nav">
		<li><a href="#">Help</a></li>
		<li><a href="#">How it works</a></li>
		<li><a href="#">Chamb for Business</a></li>
	</ul>
</div>