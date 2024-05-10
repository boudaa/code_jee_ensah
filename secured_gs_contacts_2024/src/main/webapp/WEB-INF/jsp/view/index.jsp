<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<title>Application</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">


</head>
<body>
	<div class="container">



		<div>
			<h3> Spring Web Java Application </h3>
			<p>This application was developed in an educational context at ENSAH Al Hoceima by <a target="_blank" href="https://boudaa.github.io/"> Tarik BOUDAA</a> </p>
			<p> It is based on a layered JEE architecture and it is implemented with the following tools: </p>
			<ul>
			<li>Spring</li>
			<li>Spring MVC</li>
			<li>Hibernate</li>
			<li>Spring Security</li>
			<li>MariaDB</li>
			<li>Passay</li>
			<li>Log4j</li>
			</ul>
			
		</div>
		<div>
		

		<p>You can access the application 
		<a href="${pageContext.request.contextPath}/showMyLoginPage"> by clicking here </a>  </p>


		</div>


	</div>
</body>
</html>