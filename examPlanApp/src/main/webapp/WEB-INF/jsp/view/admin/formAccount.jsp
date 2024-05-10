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
		<h3>Account Creation Form</h3>
	</div>
	<div>



		<f:form action="${pageContext.request.contextPath}/admin/addAccount"
			method="POST" modelAttribute="accountModel">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}">
			<f:hidden path="personId"/>

			<div class="row">

				<div class="col">
					<label>Please select the user role</label>

					<f:select path="roleId" multiple="false" size="1"
						class="form-control">
						<f:options items="${roleList}" itemValue="idRole"
							itemLabel="nomRole" />
					</f:select>


				</div>
			</div>


			<div style="text-align: right">
				<button type="submit" class="btn btn-primary">Create</button>
				<button type="reset" class="btn btn-secondary">Rest</button>
			</div>

		</f:form>
	</div>

	<!-- 	<div> -->

	<!-- 		<table class="table"> -->
	<!-- 			<thead> -->
	<!-- 				<tr> -->
	<!-- 					<th scope="col">Login</th> -->
	<!-- 					<th scope="col">Role</th> -->
	<!-- 					<th scope="col">Last Name</th> -->
	<!-- 					<th scope="col">First Name</th> -->
	<!-- 					<th scope="col">Email</th> -->

	<!-- 				</tr> -->
	<!-- 			</thead> -->
	<%-- 			<c:forEach items="${accountList}" var="a"> --%>
	<!-- 				<tr> -->
	<%-- 					<td><c:out value="${a.username}" /></td> --%>
	<%-- 					<td><c:out value="${a.role.roleName}" /></td> --%>
	<%-- 					<td><c:out value="${a.person.lastName}" /></td> --%>
	<%-- 					<td><c:out value="${a.person.firstName}" /></td> --%>
	<%-- 					<td><c:out value="${a.person.email}" /></td> --%>

	<!-- 				</tr> -->

	<%-- 			</c:forEach> --%>

	<!-- 		</table> -->
	<!-- 	</div> -->

	<jsp:include page="../fragments/adminfooter.jsp" />