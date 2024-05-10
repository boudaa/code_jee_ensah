<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
<!doctype html>
<html lang="en">
<head>
<title>Login page</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link
	href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">

</head>
<body>
	<section class="ftco-section">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-md-6 text-center mb-5">
					<h2 class="heading-section">Absence Management App</h2>
				</div>
			</div>
			<div class="row justify-content-center">
				<div class="col-md-7 col-lg-5">
					<div class="login-wrap p-4 p-md-5">
						<div class="icon d-flex align-items-center justify-content-center">
							<span class="fa fa-user-o"></span>
						</div>
						<h3 class="text-center mb-4">Sign In</h3>
						<form
							action="${pageContext.request.contextPath}/authenticateTheUser"
							class="login-form" method="POST">
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}">
							<!-- CSRF token -->

							<c:if test="${param.error!=null}">

								<c:choose>
									<c:when test="${param.error=='disabled'}">
										<div class="alert alert-danger col-12">Account Disabled</div>
									</c:when>
									<c:when test="${param.error=='locked'}">
										<div class="alert alert-danger col-12">Account Locked</div>
									</c:when>
									<c:when test="${param.error=='expired'}">
										<div class="alert alert-danger col-12">Account Expired</div>
									</c:when>

									<c:otherwise>
										<div class="alert alert-danger col-12">Please verify
											your login or password</div>
									</c:otherwise>
								</c:choose>
							</c:if>










							<c:if test="${param.logout != null}">

								<div class="alert alert-success col-12">You are logged out
									of the system</div>

							</c:if>
							<div class="form-group">
								<input name="username" type="text"
									class="form-control rounded-left" placeholder="Username"
									required>
							</div>
							<div class="form-group d-flex">
								<input name="password" type="password"
									class="form-control rounded-left" placeholder="Password"
									required>
							</div>
							<div class="form-group">
								<button type="submit"
									class="form-control btn btn-primary rounded submit px-3">Login</button>
							</div>
							<div class="form-group d-md-flex">
								<div class="w-50">
									<label class="checkbox-wrap checkbox-primary">Remember
										Me <input type="checkbox" checked> <span
										class="checkmark"></span>
									</label>
								</div>
								<div class="w-50 text-md-right">
									<a href="#">Forgot Password</a>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>

	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/popper.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>

</body>
</html>

