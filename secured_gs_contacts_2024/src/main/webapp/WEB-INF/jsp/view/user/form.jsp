<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<title>Contact Management</title>
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

		<!-- Menu de l'application -->
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page"
						href="${pageContext.request.contextPath}/user/showForm">Home</a></li>
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/user/showForm">Add Contact
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/user/allContacts">All
							Contacts </a></li>
					<li class="nav-item"><form
							action="${pageContext.request.contextPath}/user/serachContact"
							class="d-flex" method="POST">
							<input name="keyword" class="form-control me-2" type="search"
								placeholder="First name" aria-label="Search">
							<button class="btn btn-outline-success" type="submit">Search</button>
						</form></li>
				</ul>
			</div>
		</nav>


		<div>
			<c:if test="${action=='user/addContact'}">
				<h3>New contact</h3>
			</c:if>
			<c:if test="${action=='user/updateContact'}">
				<h3>Update contact</h3>
			</c:if>

		</div>
		<div>

			<c:if test="${infoMsg!=null}">
				<div class="alert alert-success" role="alert">${infoMsg}</div>
			</c:if>
			<c:if test="${errorMsg!=null}">
				<div class="alert alert-danger" role="alert">${errorMsg}</div>
			</c:if>

			<c:if test="${showForm}">
				<f:form action="${pageContext.request.contextPath}/user/${action}"
					method="POST" modelAttribute="contactModel">

					<div class="row">
						<f:input path="idContact" type="hidden" />
						<div class="col">
							<label>First Name</label>
							<f:input path="firstName" type="text" class="form-control"
								placeholder="First Name" />
							<f:errors path="firstName" class="text-danger" />
						</div>

						<div class="col">
							<label>Last Name</label>
							<f:input path="lastName" type="text" class="form-control"
								placeholder="Last Name" />
							<f:errors path="lastName" class="text-danger" />
						</div>

						<div class="col">
							<label>Phone number</label>
							<f:input path="phoneNumber" type="text" class="form-control"
								placeholder="Phone Number" />
							<f:errors path="phoneNumber" class="text-danger" />
						</div>
					</div>

					<div class="row">
						<div class="col">
							<label>Email</label>
							<f:input path="email" class="form-control" placeholder="Email" />
							<f:errors path="email" class="text-danger" />
						</div>
						<div class="col">
							<label>Address</label>
							<f:input path="address" type="text" class="form-control"
								placeholder="...., Morocco" />
							<f:errors path="address" class="text-danger" />
						</div>

						<div class="col">
							<label>Gender</label>
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

						</div>

					</div>





					<div style="text-align: right">
						<button type="submit" class="btn btn-primary">Send</button>
						<button type="reset" class="btn btn-secondary">Rest</button>
					</div>

				</f:form>
			</c:if>

		</div>

		<div>

			<table class="table">
				<thead>
					<tr>
						<th scope="col">First Name</th>
						<th scope="col">Last Name</th>
						<th scope="col">Email</th>
						<th scope="col">Phone number</th>
						<th scope="col">Gender</th>
						<th scope="col">Actions</th>
					</tr>
				</thead>
				<c:forEach items="${contactList}" var="p">
					<tr>
						<td><c:out value="${p.firstName}" /></td>
						<td><c:out value="${p.lastName}" /></td>
						<td><c:out value="${p.email}" /></td>
						<td><c:out value="${p.phoneNumber}" /></td>
						<td><c:out value="${p.gender}" /></td>

						<td>
							<ul>
								<li><a href="deleteContact/${p.idContact}">Delete</a></li>

								<li><a href="updateContactForm/${p.idContact}">Update</a></li>
							</ul>
						</td>
					</tr>

				</c:forEach>

			</table>
		</div>
		<div>Spring Web App by Tarik BOUDAA, National School of Applied
			Science Al Hoceima</div>
	</div>
</body>
</html>