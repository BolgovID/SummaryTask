<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<style>
    <%@include file='../../../styles/general.css' %>
    <%@include file='../../../styles/registration.css' %>
</style>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="mainContainer">
    <div class="formContainer">
    <form action="controller" method="post">
        <input type="hidden" name="check" value="${random.nextInt()}">
        <input type="hidden" name="command" value="registration"/>
        <table>
            <tr>
                <td></td>
                <td>
                    <input placeholder="Login" name="login" value="${requestScope.login}" pattern="[A-Za-z]+"/>
                <td><span class="error"><c:out value="${requestScope.errors.login}"/></span></td>
            </tr>
            <tr>
                <td></td>
                <td><input placeholder="Password" type="password" name="password"/></td>
                <td><span class="error"><c:out value="${requestScope.errors.password}"/></span></td>
            </tr>
            <tr>
                <td></td>
                <td><input placeholder="Confirm" type="password" name="confirm"/></td>
                <td><span class="error"><c:out value="${requestScope.errors.confirm}"/></span></td>
            </tr>
            <tr>
                <td></td>
                <td><input placeholder="First name" name="firstname" value="${requestScope.firstname}" pattern="[A-Za-z]+"/></td>
                <td><span class="error"> <c:out value="${requestScope.errors.firstName}"/></span></td>
            </tr>
            <tr>
                <td></td>
                <td><input placeholder="Last name" name="lastname" value="${requestScope.lastname}" pattern="[A-Za-z]+"/></td>
                <td><span class="error"> <c:out value="${requestScope.errors.lastName}"/></span></td>
            </tr>
            <tr>
                <td></td>
                <td><input placeholder="Age" type="number" min="1" name="age" value="${requestScope.age}"/></td>
                <td><span class="error"><c:out value="${requestScope.errors.age}"/></span></td>
            </tr>
        </table>
        <input type="submit" value="Registration"/>
        <a href="controller?command=loginView">To authorization page</a>
    </form>
    </div>

</div>

</body>
</html>
