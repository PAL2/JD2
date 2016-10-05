<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Registration</title>
</head>
<body>
	<form action="controller" method="post">
		<fieldset>
			<div>
				<label for="firstName">First Name:</label><br> <input
					type="text" name="firstName" value="" required />
			</div>
			<div>
				<label for="lastName">Last Name:</label><br> <input type="text"
					name="lastName" value="" required />
			</div>
			<div>
				<label for="login">Login:</label><br> <input type="text"
					name="login" value="" />
			</div>
			<div>
				<label for="password">Password:</label><br> <input
					type="password" name="password" value="" required />
			</div>
			<div>
				<input type="hidden" name="command" value="reg" /> <input
					type="submit" value="Registration" />
			</div>
		</fieldset>
	</form>
</body>
</html>