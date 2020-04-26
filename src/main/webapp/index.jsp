<%@ page contentType="text/html;charset=UTF-8" %>


<html>
<style>
    <%@include file='styles/general.css' %>
</style>

<head>
    <title>RentalCar</title>
</head>
<body>

<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="container">
    <form class="auth" action="controller" method="post">
        <input type="hidden" name="command" value="login"/>
        <table>
            <tr>
                <td><input placeholder="Your login" type="text" name="login" value="${requestScope.login}"
                           pattern="[A-Za-z]+"></td>
            </tr>
            <tr>
                <td><span class="error"><c:out value="${requestScope.errors.login}"/></span></td>
            </tr>
            <tr>
                <td><input placeholder="Your password" type="password" name="password"></td>
            </tr>
            <tr>
                <td><span class="error"><c:out value="${requestScope.errors.password}"/></span></td>
            </tr>
            <tr>
                <td><input type="submit" style="width: 100%" value="Log In"></td>
            </tr>
            <tr>
                <td><a href="controller?command=registrationView">Registration</a></td>
            </tr>
        </table>

    </form>


</div>
</body>
</html>
