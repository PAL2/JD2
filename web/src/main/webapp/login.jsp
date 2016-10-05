<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>Login</title>
</head>
<body>
<h3>${regSuccess}</h3>
	<form name="LoginForm" method="post" action="controller">
		<input type="hidden" name="command" value="login"/>
		 Login:<br />
		  <input type="text" name="login" value="" />
		  <br/>Password:<br/> 
		  <input type="password" name="password" value="" />
			<br/>
			<a href=reg.jsp>Registration</a>
		<h4>${errorLoginPassMessage}</h4>
		${wrongAction}
		${nullPage}
		<input type="submit" value="Log in"/>
	</form>
</body>
</html>