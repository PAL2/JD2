<%--
  Created by IntelliJ IDEA.
  User: Алексей
  Date: 02.10.2016
  Time: 23:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title>Ошибка</title>
</head>
<body>
<br/>
${errorDatabase} <br/>
<form method="post" action="controller">
    <input type="hidden" name="command" value="Logout" /> <input
        type="submit" value="Log out" />
</form>
</body>
</html>