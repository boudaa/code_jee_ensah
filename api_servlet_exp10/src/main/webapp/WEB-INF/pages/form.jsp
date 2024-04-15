<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>Calculatrice</h1>


	<form action="/calculatrice/calcul" method="POST">
		x : <input type="text" name="x"><br>
		y : <input type="text" name="y"><br>
		choisir l'opération : 
		
		<select name="operation">
		 <option>+</option>
		 <option>*</option>
		 <option>/</option>
		 <option>-</option>
		</select>
		<br>
		 <input type="submit">


	</form>

</body>
</html>