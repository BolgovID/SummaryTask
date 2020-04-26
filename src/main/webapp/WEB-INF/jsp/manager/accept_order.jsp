<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<style>
    <%@include file='../../../styles/general.css' %>
    <%@include file='../../../styles/check_orders.css' %>
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
        <input type="hidden" name="command" value="treatmentOrder"/>
        <input type="hidden" name="check" value="${random.nextInt()}">
        <c:if test="${empty requestScope.orderDTOList}">
            <p class="noOrders"><cm:translate prefix="accept" key="no_orders" locale="${pageScope.locale}"/></p>
        </c:if>
        <c:if test="${not empty requestScope.orderDTOList}">
            <table class="table">
                <thead>
                <tr>
                    <td>№</td>
                    <td><cm:translate prefix="accept" key="first_name" locale="${pageScope.locale}"/></td>
                    <td><cm:translate prefix="accept" key="last_name" locale="${pageScope.locale}"/></td>
                    <td><cm:translate prefix="accept" key="model" locale="${pageScope.locale}"/></td>
                    <td><cm:translate prefix="accept" key="with_driver" locale="${pageScope.locale}"/></td>
                    <td><cm:translate prefix="accept" key="from" locale="${pageScope.locale}"/></td>
                    <td><cm:translate prefix="accept" key="to" locale="${pageScope.locale}"/></td>
                    <td><cm:translate prefix="accept" key="cost" locale="${pageScope.locale}"/></td>
                    <td colspan="2"><cm:translate prefix="accept" key="status" locale="${pageScope.locale}"/></td>
                    <td id="commentText"><cm:translate prefix="accept" key="comment" locale="${pageScope.locale}"/></td>
                </tr>
                </thead>
                <c:set var="k" value="0"/>
                <c:forEach var="order" items="${requestScope.orderDTOList}">
                    <c:set var="k" value="${k+1}"/>
                    <tr>
                        <input type="hidden" name="order" value="${order.id}"/>
                        <td><label for="order_${k}"><c:out value="${k}"/> </label></td>
                        <td><label for="order_${k}">${order.firstName}</label></td>
                        <td><label for="order_${k}">${order.lastName}</label></td>
                        <td><label for="order_${k}">${order.model}</label></td>
                        <td><label for="order_${k}">${order.driver==true?'Yes':'No'}</label></td>
                        <td><label for="order_${k}">${order.fromDate}</label></td>
                        <td><label for="order_${k}">${order.toDate}</label></td>
                        <td>
                            <input type="hidden" name="cost" value="${order.cost}">
                                ${order.cost}
                        </td>
                        <c:if test="${k==1}">
                            <td>
                                <select name="decide"
                                        onchange="toggleInput(this, 'accepted', ['textAreaOrder','commentText'] )">
                                    <option value="accepted" ${'accepted' == requestScope.selectedOption ? 'selected':''}>
                                        <cm:translate prefix="accept" key="accept" locale="${pageScope.locale}"/>
                                    </option>
                                    <option value="rejected" ${'rejected' == requestScope.selectedOption ? 'selected':''}>
                                        <cm:translate prefix="accept" key="reject" locale="${pageScope.locale}"/>
                                    </option>
                                </select>
                            </td>
                            <td><input type="submit" value="<cm:translate prefix="accept" key="send" locale="${pageScope.locale}"/>"></td>
                            <td>
                                <textarea id="textAreaOrder" rows="3" cols="33" name="comment" disabled></textarea>
                                <p><span class="error"><c:out value="${requestScope.errors.comment}"/></span></p>
                            </td>
                        </c:if>
                        <c:if test="${k==2}">
                            <td colspan="2">
                                <p><cm:translate prefix="accept" key="next" locale="${pageScope.locale}"/></p>
                            </td>
                        </c:if>
                        <c:if test="${k>2}">
                            <td colspan="2">
                                <p><cm:translate prefix="accept" key="wait" locale="${pageScope.locale}"/></p>
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
