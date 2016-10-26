<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Registration</title>



</head>
<body>
<form name="reg" action="controller" method="post" onsubmit="return check();">
    <fieldset>
        <div>
            <label for="firstName">First Name:</label><br> <input
                type="text" name="firstName" value="" required/>
        </div>
        <div>
            <label for="lastName">Last Name:</label><br> <input type="text"
                                                                name="lastName" value="" required/>
        </div>
        <div>
            <label for="login">Login:</label><br> <input type="text"
                                                         name="login" value="" required/>
        </div>
        <div>
            <label for="password">Password:</label><br> <input
                type="password" name="password" value="" required />
        </div>
        <div>
            <input type="hidden" name="command" value="reg"/> <input
                type="submit" value="Registration" onsubmit="return check()"/>
        </div>
    </fieldset>
</form>
</body>

<script type="text/javascript">
    var form = document.reg;
    document.write(form.firstName.value + "<br />");
    document.write(form.lastName.value + "<br />");
    document.write(form.login.value + "<br />");
    document.write(form.password.value + "<br />");

    function check(form) {
        var firstName = form.firstName.value;
        var lastName = form.lastName.value;
        var login = form.login.value;
        var password = form.password.value;
        var bad = "";
        if (firstName.length < 4)
            bad += "Имя слишком короткое" + "\n";
        if (firstName.length > 32)
            bad += "Имя слишком длинное" + "\n";
        if (lastName.length < 4)
            bad += "Фамилия слишком короткая" + "\n";
        if (lastName.length > 32)
            bad += "Фамилия слишком длинная" + "\n";
        if (password.length < 4)
            bad += "Пароль слишком короткий" + "\n";
        if (password.length > 32)
            bad += "Пароль слишком длинный" + "\n";
        if (bad != "") {
            bad = "Неверно заполнена форма:" + "\n" + bad;
            alert(bad);
            return false;
        }
        return true;
    }
</script>
</html>