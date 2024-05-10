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
				<img style="float: left;" width="50" height="50"
					src="${pageContext.request.contextPath}/resources/img/error.png">
				<div class="col-md-7 col-lg-5">
				<h3> An Error has occurred	</h3>
				 <p style="margin-left: 10px"><strong>Request : </strong> ${url}</p>
					<p style="margin-left: 10px"><strong>Error message :</strong> ${message}</p>


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

