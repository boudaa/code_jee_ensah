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
		<h3>
			Mise à jour d'un
			<c:if test="${personModel.typePerson== 1}" var="variable">
					Prof
				</c:if>
			<c:if test="${personModel.typePerson== 2}" var="variable">
					Etudiant
				</c:if>
			<c:if test="${personModel.typePerson== 3}" var="variable">
					Cadre Admin
				</c:if>


		</h3>
	</div>
	<div>

		<c:if test="${not empty msg}">
			<div class="alert alert-success" role="alert">${msg}</div>
		</c:if>

		<f:form action="${pageContext.request.contextPath}/admin/updatePerson"
			method="POST" modelAttribute="personModel">
			<f:hidden path="idPersonne" />
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}">

			<f:hidden path="typePerson" />

			<div class="row">
				<div class="col">
					<label>CIN</label>
					<f:input path="cin" type="text" class="form-control"
						placeholder="cin" />
					<f:errors path="cin" class="text-danger" />
				</div>

				<div class="col">
					<label>Nom</label>
					<f:input path="nom" type="text" class="form-control"
						placeholder="nom" />
					<f:errors path="nom" class="text-danger" />
				</div>
			</div>


			<div class="row">
				<div class="col">
					<label>Prénom</label>
					<f:input path="prenom" type="text" class="form-control"
						placeholder="prenom" />
					<f:errors path="prenom" class="text-danger" />
				</div>

				<div class="col">
					<label>Télé</label>
					<f:input path="telephone" type="text" class="form-control"
						placeholder="telephone" />
					<f:errors path="telephone" class="text-danger" />
				</div>
			</div>


			<div class="row">
				<div class="col">
					<label>Email</label>
					<f:input path="email" class="form-control" placeholder="Email" />
					<f:errors path="email" class="text-danger" />
				</div>
				<c:if test="${personModel.typePerson == 1}" var="variable">
					<div class="col">
						<label>Spécialité</label>
						<f:input path="specialite" type="text" class="form-control"
							placeholder="Spécialité" />
						<f:errors path="specialite" class="text-danger" />
					</div>
				</c:if>
				<c:if test="${personModel.typePerson== 2}" var="variable">
					<div class="col">
						<label>CNE</label>
						<f:input path="cne" type="text" class="form-control"
							placeholder="cne" />
						<f:errors path="cne" class="text-danger" />
					</div>
				</c:if>
				<c:if test="${personModel.typePerson== 3}" var="variable">
					<div class="col">
						<label>Grade</label>
						<f:input path="grade" type="text" class="form-control"
							placeholder="grade" />
						<f:errors path="grade" class="text-danger" />
					</div>
				</c:if>

			</div>



			<div class="row">




				<div class="col">
					<label>Nom Arabe</label>
					<f:input path="nomArabe" type="text" class="form-control"
						placeholder="nomArabe" />
					<f:errors path="nomArabe" class="text-danger" />
				</div>
				<div class="col">
					<label>Prénom Arabe</label>
					<f:input path="prenomArabe" type="text" class="form-control"
						placeholder="prenomArabe" />
					<f:errors path="prenomArabe" class="text-danger" />
				</div>

			</div>
			<div style="text-align: right">
				<button type="submit" class="btn btn-primary">Update</button>
				<button type="reset" class="btn btn-secondary">Rest</button>
			</div>

		</f:form>
	</div>



	<jsp:include page="../fragments/adminfooter.jsp" />