<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<title>Update form</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">

<style>
h3 {
	margin-top: 20px;
}

#navbarNav div {
	height: 0;
}

#navbarNav form {
	margin: 0;
	padding: 0;
}

form {
	margin-bottom: 60px;
	margin-top: 10px;
	padding: 10px;
}
</style>

</head>
<body>
	<div class="container">


		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page"
						href="${pageContext.request.contextPath}/showForm">Home</a></li>





					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/showForm">Add Persons
					</a></li>


					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/managePersons">Manage
							Persons </a></li>



					<li class="nav-item"><form
							action="${pageContext.request.contextPath}/serachPerson"
							class="d-flex" method="POST">
							<input name="nid" class="form-control me-2" type="search"
								placeholder="National ID" aria-label="Search">
							<button class="btn btn-outline-success" type="submit">Search</button>
						</form></li>



				</ul>



			</div>

		</nav>


		<div>
			<h3>Update Person</h3>
		</div>
		<div>



			<f:form action="${pageContext.request.contextPath}/updatePerson"
				method="POST" modelAttribute="personModel">
				<f:hidden path="idPersonne" />
				<div class="row">
					<div class="col">
						<label>National Identity Number</label>
						<f:input path="nationalIdNumber" type="text" class="form-control"
							placeholder="National Identity Number" />
						<f:errors path="nationalIdNumber" class="text-danger" />
					</div>

					<div class="col">
						<label>First Name</label>
						<f:input path="firstName" type="text" class="form-control"
							placeholder="First Name" />
						<f:errors path="firstName" class="text-danger" />
					</div>
				</div>


				<div class="row">
					<div class="col">
						<label>Last Name</label>
						<f:input path="lastName" type="text" class="form-control"
							placeholder="Last Name" />
						<f:errors path="lastName" class="text-danger" />
					</div>

					<div class="col">
						<label>Age</label>
						<f:input path="age" type="text" class="form-control"
							placeholder="Age" />
						<f:errors path="age" class="text-danger" />
					</div>
				</div>


				<div class="row">
					<div class="col">
						<label>Email</label>
						<f:input path="email" class="form-control" placeholder="Email" />
						<f:errors path="email" class="text-danger" />
					</div>

					<div class="col">
						<label for="inputPassword4">Password</label>
						<f:input path="password" type="password" class="form-control"
							placeholder="Password" />
						<f:errors path="password" class="text-danger" />
					</div>
				</div>



				<div class="row">
					<div class="col">
						<label>Address</label>
						<f:input path="address" type="text" class="form-control"
							placeholder="...., Morocco" />
						<f:errors path="address" class="text-danger" />
					</div>

					<div class="col">
						<label>State</label>
						<f:select path="state" items="${countryList}" class="form-control" />
						<f:errors path="state" class="text-danger" />
					</div>
				</div>

				<div class="row">
					<div class="col">
						<legend class="col-form-label col-sm-2 pt-0">Community</legend>
						<div class="form-check">
							<f:checkbox path="community" value="Spring"
								class="form-check-input" />
							<label class="form-check-label"> Spring </label>

						</div>

						<div class="form-check">
							<f:checkbox path="community" value="JAKARTA EE"
								class="form-check-input" />
							<label class="form-check-label"> JAKARTA EE </label>

						</div>

						<div class="form-check">
							<f:checkbox path="community" value="Hibernate"
								class="form-check-input" />
							<label class="form-check-label"> Hibernate </label>

						</div>

						<f:errors path="community" class="text-danger" />
						<%-- 				<form:checkboxes path="abc" items="${object.elementList}"/>   --%>

					</div>
					<div class="col">
						<legend class="col-form-label col-sm-2 pt-0">Gender</legend>
						<div class="form-check">
							<f:radiobutton path="gender" class="form-check-input"
								value="Female" />
							<label class="form-check-label">Female </label>
						</div>
						<div class="form-check">
							<f:radiobutton path="gender" class="form-check-input"
								value="Male " />
							<label class="form-check-label">Male </label>
						</div>
						<f:errors path="gender" class="text-danger" />
						<%-- <form:radiobuttons path="abc" items="${xyz}"/>   --%>

					</div>

				</div>
				<div style="text-align: right">
					<button type="submit" class="btn btn-primary">Update</button>
					<button type="reset" class="btn btn-secondary">Rest</button>
				</div>

			</f:form>
		</div>


	</div>
</body>
</html>