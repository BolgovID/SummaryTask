<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<style>
    <%@include file='../../../styles/general.css' %>
    <%@include file='../../../styles/return_car.css' %>
</style>
<script>
    <%@include file='../../../scripts/script.js' %>
</script>
<html>
<head>
    <title>Your orders</title>
</head>
<body>

<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div class="mainContainer">
    <form action="controller" method="post">
        <input type="hidden" name="check" value="${random.nextInt()}">
        <input type="hidden" name="command" value="acceptCar">
        <c:if test="${empty requestScope.orderDTOList}">
            <p class="noCars"><cm:translate prefix="return" key="no_cars" locale="${pageScope.locale}"/></p>
        </c:if>
        <c:if test="${not empty requestScope.orderDTOList}">
            <table class="table">
                <thead>
                <tr>
                    <td>№</td>
                    <td><cm:translate prefix="returns" key="model" locale="${pageScope.locale}"/></td>
                    <td><cm:translate prefix="returns" key="status" locale="${pageScope.locale}"/></td>
                    <td><cm:translate prefix="return" key="with_driver" locale="${pageScope.locale}"/></td>
                    <td><cm:translate prefix="return" key="from" locale="${pageScope.locale}"/></td>
                    <td><cm:translate prefix="return" key="to" locale="${pageScope.locale}"/></td>
                    <td colspan="4"><cm:translate prefix="return" key="actions" locale="${pageScope.locale}"/></td>
                </tr>
                </thead>
                <c:set var="k" value="0"/>
                <c:forEach var="order" items="${requestScope.orderDTOList}">
                    <c:set var="k" value="${k+1}"/>
                    <tr>
                        <td><c:out value="${k}"/></td>
                        <td>${order.model}</td>
                        <td>${order.status}</td>
                        <td>${order.driver==true?'Yes':'No'}</td>
                        <td>${order.fromDate}</td>
                        <td>${order.toDate}</td>
                        <c:if test="${k==1}">
                            <td>
                                <input type="hidden" name="orderId" value="${order.id}">
                                <select name="decide" onchange="toggleInput(this, 'getCar',
                                         ['textAreaCheck', 'inputReturnCar', 'reasonParag', 'penaltySpan']);">
                                    <option value="getCar" ${'getCar' == requestScope.selectedOption ? 'selected':''}>
                                        <cm:translate prefix="return" key="get" locale="${pageScope.locale}"/></option>
                                    <option value="penalty" ${'penalty' == requestScope.selectedOption ? 'selected':''}>
                                        <cm:translate prefix="return" key="penalty" locale="${pageScope.locale}"/>
                                    </option>
                                </select>
                            </td>
                            <td>
                                <span id="penaltySpan">
                                    <cm:translate prefix="return" key="penalty_size" locale="${pageScope.locale}"/>
                                </span>
                                <input id="inputReturnCar" name="cost" type="number" min="1" step="0.01">
                                <p class="center">
                                    <span class="error"><c:out value="${requestScope.errors.cost}"/></span>
                                </p>
                            </td>
                            <td>
                                <p id="reasonParag"><cm:translate prefix="return" key="reason"
                                                                  locale="${pageScope.locale}"/></p>
                                <textarea id="textAreaCheck" rows=" 3" cols="33" name="penaltyComment"></textarea>
                                <span class="error"><c:out value="${requestScope.errors.comment}"/></span>
                            </td>
                            <td><input type="submit"
                                       value="<cm:translate prefix="return" key="send" locale="${pageScope.locale}"/>">
                            </td>
                        </c:if>
                        <c:if test="${k==2}">
                            <td colspan="4">
                                <p><cm:translate prefix="return" key="next" locale="${pageScope.locale}"/></p>
                            </td>
                        </c:if>
                        <c:if test="${k>2}">
                            <td colspan="4">
                                <p><cm:translate prefix="return" key="wait" locale="${pageScope.locale}"/></p>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </form>
</div>
</body>
</html>
