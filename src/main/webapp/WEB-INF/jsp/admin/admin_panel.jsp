<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<style>
    <%@include file='../../../styles/general.css' %>
    <%@include file='../../../styles/admin.css' %>
</style>

<html>
<head>
    <title>AdminPanel</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="mainContainer">
    <ul class="commandPanel">
        <li>
            <form action="controller" method="post">
                <input type="hidden" name="check" value="${random.nextInt()}">
                <div class="formContainer">
                    <input type="hidden" name="command" value="addCar"/>
                    <h3><cm:translate prefix="control" key="add" locale="${pageScope.locale}"/></h3>
                    <div class="oneLine">
                        <input name="model"
                               placeholder="<cm:translate prefix="field" key="model" locale="${pageScope.locale}"/>">
                        <input name="cost" type="number" min="1" step="0.01"
                               placeholder="<cm:translate prefix="field" key="cost" locale="${pageScope.locale}"/>">
                    </div>
                    <div class="oneLine">
                        <span class="error"><c:out value="${requestScope.errorsAdd.model}"/> </span>
                        <span class="error"><c:out value="${requestScope.errorsAdd.cost}"/> </span>
                    </div>
                    <div class="oneLine">
                        <div class="itemCenter">
                            <p><cm:translate prefix="control" key="brand" locale="${pageScope.locale}"/>:</p>
                            <select name="brand">
                                <c:forEach var="brand" items="${requestScope.brandList}">
                                    <option value="${brand.id}">${brand.brand}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="itemCenter">
                            <p><cm:translate prefix="control" key="category" locale="${pageScope.locale}"/>:</p>
                            <select name="category">
                                <c:forEach var="category" items="${requestScope.categoryList}">
                                    <option value="${category.id}">${category.category}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <input type="submit"
                       value="<cm:translate prefix="control" key="add_button" locale="${pageScope.locale}"/>">
            </form>
        </li>
        <li>
            <form action="controller" method="post">
                <input type="hidden" name="check" value="${random.nextInt()}">
                <div class="formContainer">
                    <input type="hidden" name="command" value="updateCar"/>
                    <h3><cm:translate prefix="control" key="update" locale="${pageScope.locale}"/></h3>
                    <div class="oneLine">
                        <input placeholder="ID" type="number" min="1" name="id_car">
                        <input placeholder="<cm:translate prefix="field" key="model" locale="${pageScope.locale}"/>"
                               name="model">
                        <input placeholder="<cm:translate prefix="field" key="cost" locale="${pageScope.locale}"/>"
                               type="number" min="1" step="0.01" name="cost">
                    </div>
                    <div class="oneLine">
                        <span class="error"><c:out value="${requestScope.errorsUpdate.id}"/> </span>
                        <span class="error"><c:out value="${requestScope.errorsUpdate.model}"/> </span>
                        <span class="error"><c:out value="${requestScope.errorsUpdate.cost}"/> </span>
                    </div>
                    <div class="oneLine">
                        <div class="itemCenter">
                            <p><cm:translate prefix="control" key="brand" locale="${pageScope.locale}"/>:</p>
                            <select name="brand">
                                <c:forEach var="brand" items="${requestScope.brandList}">
                                    <option value="${brand.id}">${brand.brand}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div>
                            <div class="itemCenter">
                                <p><cm:translate prefix="control" key="category" locale="${pageScope.locale}"/>:</p>
                                <select name="category">
                                    <c:forEach var="category" items="${requestScope.categoryList}">
                                        <option value="${category.id}">${category.category}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <input type="submit"
                       value="<cm:translate prefix="control" key="update_button" locale="${pageScope.locale}"/>">
            </form>
        </li>
        <li class="delete">
            <form action="controller" method="post">
                <input type="hidden" name="check" value="${random.nextInt()}">
                <div class="formContainer">
                    <input type="hidden" name="command" value="deleteCar"/>
                    <h3><cm:translate prefix="control" key="delete" locale="${pageScope.locale}"/></h3>
                    <input placeholder="ID" type="number" min="1" name="id_car">
                    <span class="error"> <c:out value="${requestScope.errorsDelete.id}"/></span>
                </div>
                <input type="submit"
                       value="<cm:translate prefix="control" key="delete_button" locale="${pageScope.locale}"/>">
            </form>
        </li>
        <li>
            <form action="controller" method="post">
                <input type="hidden" name="check" value="${random.nextInt()}">
                <div class="formContainer">
                    <input type="hidden" name="command" value="updateUserStatus"/>
                    <h3><cm:translate prefix="control" key="status" locale="${pageScope.locale}"/></h3>
                    <input placeholder="ID" type="number" min="1" name="id_user">
                    <span class="error"><c:out value="${requestScope.errorsStatus.id}"/></span>
                    <div class="itemCenter">
                        <p><cm:translate prefix="control" key="info" locale="${pageScope.locale}"/>:</p>
                        <select name="status">
                            <option value="1">unbanned</option>
                            <option value="2">banned</option>
                        </select>
                    </div>
                </div>
                <input type="submit"
                       value="<cm:translate prefix="control" key="update_button" locale="${pageScope.locale}"/>">
            </form>
        </li>
        <li>
            <form action="controller" method="post">
                <input type="hidden" name="check" value="${random.nextInt()}">
                <div class="formContainer">
                    <input type="hidden" name="command" value="addManager"/>
                    <h3><cm:translate prefix="control" key="manager" locale="${pageScope.locale}"/></h3>
                    <div class="oneLine">
                        <input placeholder="<cm:translate prefix="field" key="login" locale="${pageScope.locale}"/>"
                               name="login" pattern="[A-Za-z]+">
                        <input placeholder="<cm:translate prefix="field" key="password" locale="${pageScope.locale}"/>"
                               name="password">
                    </div>
                    <div class="oneLine">
                        <span class="error"><c:out value="${requestScope.errorsManager.login}"/> </span>
                        <span class="error"><c:out value="${requestScope.errorsManager.password}"/> </span>
                    </div>
                    <div class="oneLine">
                        <input placeholder="<cm:translate prefix="field" key="first_name" locale="${pageScope.locale}"/>"
                               name="firstname" pattern="[A-Za-z]+">
                        <input placeholder="<cm:translate prefix="field" key="last_name" locale="${pageScope.locale}"/>"
                               name="lastname" pattern="[A-Za-z]+">
                    </div>
                    <div class="oneLine">
                        <span class="error"><c:out value="${requestScope.errorsManager.firstName}"/> </span>
                        <span class="error"><c:out value="${requestScope.errorsManager.lastName}"/> </span>
                    </div>
                    <input placeholder="<cm:translate prefix="field" key="age" locale="${pageScope.locale}"/>"
                           type="number" min="1" name="age">
                    <span class="error"><c:out value="${requestScope.errorsManager.age}"/> </span>
                </div>
                <input type="submit"
                       value="<cm:translate prefix="control" key="manager_button" locale="${pageScope.locale}"/>">
            </form>
        </li>
    </ul>
    <div class="tableContainer">
        <div class="carListPanel">
            <c:if test="${not empty requestScope.carDTOList}">
                <table class="table search_list">
                    <thead>
                    <tr>
                        <td>Id</td>
                        <td><cm:translate prefix="control" key="model" locale="${pageScope.locale}"/></td>
                        <td><cm:translate prefix="control" key="brand" locale="${pageScope.locale}"/></td>
                        <td><cm:translate prefix="control" key="category" locale="${pageScope.locale}"/></td>
                        <td><cm:translate prefix="control" key="cost" locale="${pageScope.locale}"/></td>
                    </tr>
                    </thead>
                    <c:forEach var="carDTO" items="${requestScope.carDTOList}">
                        <tr>
                            <td>${carDTO.id}</td>
                            <td>${carDTO.model}</td>
                            <td>${carDTO.brand}</td>
                            <td>${carDTO.category}</td>
                            <td>${carDTO.cost}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
            <c:if test="${empty requestScope.carDTOList}">
                <p class="anything"><cm:translate prefix="control" key="no_cars" locale="${pageScope.locale}"/></p>
            </c:if>
        </div>
        <div class="userListPanel">
            <c:if test="${not empty requestScope.userDTOList}">
                <table class="table search_list">
                    <thead>
                    <tr>
                        <td>Id</td>
                        <td><cm:translate prefix="control" key="login" locale="${pageScope.locale}"/></td>
                        <td><cm:translate prefix="control" key="first_name" locale="${pageScope.locale}"/></td>
                        <td><cm:translate prefix="control" key="last_name" locale="${pageScope.locale}"/></td>
                        <td><cm:translate prefix="control" key="age" locale="${pageScope.locale}"/></td>
                        <td><cm:translate prefix="control" key="status_t" locale="${pageScope.locale}"/></td>
                        <td><cm:translate prefix="control" key="role" locale="${pageScope.locale}"/></td>
                    </tr>
                    </thead>
                    <c:forEach var="userDTO" items="${requestScope.userDTOList}">
                        <tr>
                            <td>${userDTO.id}</td>
                            <td>${userDTO.login}</td>
                            <td>${userDTO.firstName}</td>
                            <td>${userDTO.lastName}</td>
                            <td>${userDTO.age}</td>
                            <td>${userDTO.status}</td>
                            <td>${userDTO.role}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
            <c:if test="${empty requestScope.userDTOList}">
                <p class="anything"><cm:translate prefix="control" key="no_users" locale="${pageScope.locale}"/></p>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
