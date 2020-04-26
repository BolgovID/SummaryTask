<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: xiaomi
  Date: 11.03.2020
  Time: 0:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>ERROR</title>
</head>
<body>
<h1>ERROR</h1>
<c:out value="${requestScope.errorMessage}"/>
</body>
</html>
