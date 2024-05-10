<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>


<jsp:include page="../fragments/adminHeader.jsp" />

<div class="container">

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">

			<jsp:include page="../fragments/menu.jsp" />

		</div>
	</nav>






	<div>
		<h3>List of Persons</h3>
	</div>

	<div>



		</div>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">CIN</th>
					<th scope="col">Nom</th>
					<th scope="col">Prénom</th>
					<th scope="col">Email</th>
					<th>Actions</th>
				</tr>
			</thead>

			<c:forEach items="${personList}" var="p">
				<tr>

				
					<td><c:out value="${p.cin}" /></td>
					<td><c:out value="${p.nom} " /></td>
					<td><c:out value="${p.prenom}" /></td>
					<td><c:out value="${p.email}" /></td>

					<td>
						<ul>
							<li><a
								href="${pageContext.request.contextPath}/admin/deletePerson/${p.idPersonne}">Delete</a></li>

							<li><a
								href="${pageContext.request.contextPath}/admin/updatePersonForm/${p.idPersonne}">Update</a></li>


						</ul>
					</td>

				</tr>

			</c:forEach>

		</table>
	
	<jsp:include page="../fragments/adminfooter.jsp" />