<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<style>
    <%@include file='../../../styles/general.css' %>
    <%@include file='../../../styles/car_list.css' %>
</style>
<script>
    <%@include file='../../../scripts/script.js' %>
</script>
<html>
<head>
    <title>Choose car</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="mainContainer">
    <div class="grid">
        <div class="box mainBox">
            <form action="controller" method="post">
                <input type="hidden" name="command" value="searchCar"/>
                <ul class="search_line">
                    <li>
                        <p><cm:translate prefix="search" key="choose_brand" locale="${pageScope.locale}"/>:</p>
                        <select name="brand">
                            <option value="all">
                                <cm:translate prefix="search" key="all" locale="${pageScope.locale}"/></option>
                            <c:forEach var="brand" items="${requestScope.brandList}">
                                <option value="${brand.brand}"
                                    ${brand.brand == requestScope.selectedBrand ? 'selected':''} >${brand.brand}
                                </option>
                            </c:forEach>
                        </select>
                    </li>
                    <li>
                        <p><cm:translate prefix="search" key="choose_category" locale="${pageScope.locale}"/>:</p>
                        <select name="category">
                            <option value="all">
                                <cm:translate prefix="search" key="all" locale="${pageScope.locale}"/></option>
                            <c:forEach var="category" items="${requestScope.categoryList}">
                                <option value="${category.category}"
                                    ${category.category == requestScope.selectedCategory ? 'selected':''}>
                                        ${category.category}</option>
                            </c:forEach>
                        </select>
                    </li>
                    <li>
                        <p><cm:translate prefix="search" key="sort_by" locale="${pageScope.locale}"/></p>
                        <select name="filter">
                            <option value="noFilter">
                                <cm:translate prefix="search" key="nosort" locale="${pageScope.locale}"/>
                            </option>
                            <option value="cost" ${"cost" == requestScope.selectedFilter ? 'selected':''}>
                                <cm:translate prefix="search" key="sort_cost" locale="${pageScope.locale}"/>
                            </option>
                            <option value="name" ${"name" == requestScope.selectedFilter ? 'selected':''}>
                                <cm:translate prefix="search" key="sort_name" locale="${pageScope.locale}"/>
                            </option>
                        </select>
                    </li>
                    <li>
                        <input type="submit" value="<cm:translate prefix="search" key="search_button"
                        locale="${pageScope.locale}"/>">
                    </li>
                </ul>
            </form>
        </div>
        <div class="box mainBox">
            <span class="error"><c:out value="${requestScope.errors.car}"/></span>
            <form class="formInput" action="controller" method="post">
                <input type="hidden" name="command" value="makeOrder"/>
                <input type="hidden" name="check" value="${random.nextInt()}">
                <div class="main-container">
                    <c:if test="${not empty requestScope.carDTOList}">
                        <table class="table search_list">
                            <thead>
                            <tr>
                                <td>№</td>
                                <td><cm:translate prefix="search" key="table_model" locale="${pageScope.locale}"/></td>
                                <td><cm:translate prefix="search" key="table_brand" locale="${pageScope.locale}"/></td>
                                <td><cm:translate prefix="search" key="table_category"
                                                  locale="${pageScope.locale}"/></td>
                                <td><cm:translate prefix="search" key="table_cost" locale="${pageScope.locale}"/></td>
                                <td><cm:translate prefix="search" key="table_order" locale="${pageScope.locale}"/></td>
                            </tr>
                            </thead>
                            <c:set var="k" value="0"/>
                            <c:forEach var="carDTO" items="${requestScope.carDTOList}">
                                <c:set var="k" value="${k+1}"/>
                                <tr>
                                    <td><label for="car_${k}"><c:out value="${k}"/> </label></td>
                                    <td><label for="car_${k}">${carDTO.model}</label></td>
                                    <td><label for="car_${k}">${carDTO.brand}</label></td>
                                    <td><label for="car_${k}">${carDTO.category}</label></td>
                                    <td><label for="car_${k}">${carDTO.cost}</label></td>
                                    <td><input type="radio" id="car_${k}" name="chooseCar" value="${carDTO.id}"/>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>
                    <c:if test="${empty requestScope.carDTOList}">
                        <p class="anything">
                            <cm:translate prefix="search" key="cannot_find_car" locale="${pageScope.locale}"/>
                        </p>
                    </c:if>
                </div>
                <div class="input-data">
                    <h3><cm:translate prefix="search" key="order_info_theme" locale="${pageScope.locale}"/></h3>
                    <p><cm:translate prefix="search" key="order_info_driver" locale="${pageScope.locale}"/></p>
                    <input type="radio" id="driver_id_true" name="driver" value="true">
                    <label for="driver_id_true">
                        <cm:translate prefix="search" key="order_driver_yes" locale="${pageScope.locale}"/>
                    </label>
                    <input type="radio" id="driver_id_false" name="driver" value="false" checked>
                    <label for="driver_id_false">
                        <cm:translate prefix="search" key="order_driver_no" locale="${pageScope.locale}"/>
                    </label>
                    <h4><cm:translate prefix="search" key="order_info_period" locale="${pageScope.locale}"/></h4>
                    <p><cm:translate prefix="search" key="order_from" locale="${pageScope.locale}"/>:
                        <input id="fromDate" name="fromDate" onchange="dateFrom(this)" type="date"
                               min="${requestScope.minDate}"></p>
                    <p><cm:translate prefix="search" key="order_to" locale="${pageScope.locale}"/>:
                        <input id="toDate" name="toDate" type="date" disabled>
                    </p>
                    <p><span class="error"><c:out value="${requestScope.errors.date}"/></span></p>
                    <input type="submit"
                           value="<cm:translate prefix="search" key="order_button" locale="${pageScope.locale}"/>">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
