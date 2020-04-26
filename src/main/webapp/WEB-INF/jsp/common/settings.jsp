<%--
  Created by IntelliJ IDEA.
  User: xiaomi
  Date: 11.04.2020
  Time: 18:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<style>
    <%@include file='../../../styles/general.css' %>
    <%@include file='../../../styles/settings.css' %>
</style>

<html>
<head>
    <title>Settings</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="mainContainer">
    <div class="formContainer">
        <h1><cm:translate prefix="settings" key="settings" locale="${pageScope.locale}"/></h1>
        <h2><cm:translate prefix="settings" key="profile" locale="${pageScope.locale}"/></h2>
        <table class="tableProfile">
            <tr>
                <td><cm:translate prefix="settings" key="name" locale="${pageScope.locale}"/></td>
                <td>${sessionScope.user.firstName}</td>
            </tr>
            <tr>
                <td><cm:translate prefix="settings" key="last_name" locale="${pageScope.locale}"/></td>
                <td>${sessionScope.user.lastName}</td>
            </tr>
            <tr>
                <td><cm:translate prefix="settings" key="age" locale="${pageScope.locale}"/></td>
                <td>${sessionScope.user.age}</td>
            </tr>
        </table>

        <h4><cm:translate prefix="settings" key="change" locale="${pageScope.locale}"/></h4>
        <form action="controller" method="post">
            <input type="hidden" name="check" value="${random.nextInt()}">
            <input type="hidden" name="command" value="settingsChange">
            <table>
                <tr>
                    <td>
                        <input placeholder="<cm:translate prefix="field" key="first_name" locale="${pageScope.locale}"/>"
                               pattern="[A-Za-zА-Яа-я]+" name="firstname">
                    </td>
                    <td><span class="error">${requestScope.errors.firstName} </span></td>
                </tr>
                <tr>
                    <td>
                        <input placeholder="<cm:translate prefix="field" key="last_name" locale="${pageScope.locale}"/>"
                               pattern="[A-Za-zА-Яа-я]+" name="lastname"></td>
                    <td><span class="error">${requestScope.errors.lastName} </span></td>
                </tr>
                <tr>
                    <td>
                        <input placeholder="<cm:translate prefix="field" key="age" locale="${pageScope.locale}"/>"
                               type="number" min="1" name="age"></td>
                    <td><span class="error">${requestScope.errors.age} </span></td>
                </tr>
            </table>
            <input type="submit" value="<cm:translate prefix="settings" key="update" locale="${pageScope.locale}"/>">
        </form>
    </div>
</div>
</body>
</html>
