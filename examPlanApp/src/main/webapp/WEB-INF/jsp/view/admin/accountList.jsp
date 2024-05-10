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
		<h3>Accounts</h3>
	</div>

	<div>


		<c:if test="${accountModel.password !=null}">
			<div class="alert alert-success col-6">User created. The
				password generated is : ${accountModel.password}</div>

		</c:if>

		<table class="table">
			<thead>
				<tr>
					<th scope="col">Login</th>
					<th scope="col">Role</th>
					<th scope="col">Name</th>
					<th scope="col">Email</th>

				</tr>
			</thead>
			<c:forEach items="${accountList}" var="a">
				<tr>
					<td><c:out value="${a.login}" /></td>

					<td><c:choose>
					  <c:when test="${a.role.nomRole== 'ROLE_ADMIN'}">
					     <span class="badge bg-primary">ADMINISTRATEUR</span>
					  </c:when>
					
					  <c:otherwise>
					   <span class="badge bg-warning"><c:out value="${a.role.nomRole}" /></span>
					  </c:otherwise>
					</c:choose>
					</td>
					<td><c:out value="${a.proprietaire.nom} ${a.proprietaire.prenom}" /></td>
					<td><c:out value="${a.proprietaire.email}" /></td>

				</tr>

			</c:forEach>

		</table>
	</div>

	<jsp:include page="../fragments/adminfooter.jsp" />