<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ page contentType="text/html;charset=UTF-8" %>


<style>
    <%@include file='../../../styles/general.css' %>
    <%@include file='../../../styles/pay_order.css' %>
</style>

<html>
<head>
    <title>Your orders</title>
</head>
<body>

<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div class="mainContainer">
    <div class="grid">
        <div class="mainBox box">
            <c:if test="${not empty requestScope.orderDTOList}">
                <table class="table">
                    <thead>
                    <tr>
                        <td>№</td>
                        <td><cm:translate prefix="pay" key="model" locale="${pageScope.locale}"/></td>
                        <td><cm:translate prefix="pay" key="status" locale="${pageScope.locale}"/></td>
                        <td><cm:translate prefix="pay" key="with_driver" locale="${pageScope.locale}"/></td>
                        <td><cm:translate prefix="pay" key="from" locale="${pageScope.locale}"/></td>
                        <td><cm:translate prefix="pay" key="to" locale="${pageScope.locale}"/></td>
                        <td><cm:translate prefix="pay" key="info" locale="${pageScope.locale}"/></td>
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
                            <td>
                                <c:choose>
                                    <c:when test="${order.status=='accepted'||order.status=='closed'}">
                                        <c:if test="${not empty requestScope.billList}">
                                            <c:forEach var="bill" items="${requestScope.billList}">
                                                <c:if test="${bill.orderId==order.id}">
                                                    ${requestScope.errors.bill}
                                                    <div class="checkContainer">
                                                        <p>#${bill.id}</p>
                                                        <p><cm:translate prefix="pay" key="cost"
                                                                         locale="${pageScope.locale}"/><b> ${bill.cost}</b></p>
                                                        <p><cm:translate prefix="pay" key="bill_for"
                                                                         locale="${pageScope.locale}"/><b>${bill.reason}</b></p>
                                                        <form id="form1" action="controller" method="post">
                                                            <input type="hidden" name="command"
                                                                   value="payOrder"/>
                                                            <input type="hidden" name="check"
                                                                   value="${random.nextInt()}">
                                                            <input type="hidden" name="bill" value="${bill.id}">
                                                            <input type="hidden" name="pay" value="${order.id}">
                                                            <input type="hidden" name="status" value="${order.status}">
                                                            <input type="submit" value="<cm:translate prefix="pay" key="pay"
                                                             locale="${pageScope.locale}"/>">
                                                        </form>
                                                    </div>
                                                </c:if>
                                            </c:forEach>
                                        </c:if>
                                        <c:if test="${empty requestScope.billList}">
                                            <p class="green"><cm:translate prefix="pay" key="info_all_paid"
                                                             locale="${pageScope.locale}"/></p>
                                        </c:if>
                                    </c:when>
                                    <c:when test="${order.status=='rejected'}">
                                        <p><span class="reject"><cm:translate prefix="pay" key="reject"
                                                                              locale="${pageScope.locale}"/>
                                                ${order.reasonDeny}</span></p>
                                    </c:when>
                                    <c:when test="${order.status=='paid'}">
                                        <form id="form2" action="controller" method="post">
                                            <input type="hidden" name="check" value="${random.nextInt()}">
                                            <input type="hidden" name="command" value="returnCar"/>
                                            <input type="hidden" name="orderId" value="${order.id}">
                                            <input type="submit" value="<cm:translate prefix="pay" key="return"
                                                                              locale="${pageScope.locale}"/>">
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <p class="orange"><cm:translate prefix="pay" key="info_wait" locale="${pageScope.locale}"/></p>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>

                    </c:forEach>
                </table>
            </c:if>
            <c:if test="${empty requestScope.orderDTOList}">
                <p>No orders</p>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
