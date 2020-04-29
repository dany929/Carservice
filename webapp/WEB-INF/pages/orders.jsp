<%--
  Created by IntelliJ IDEA.
  User: daniil
  Date: 03.04.2020
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>


<html>
<head>
    <title>Orders</title>

    <link rel="stylesheet" type="text/css" href="../../css/style.css">
</head>

<body>
<h1>
    <%@ include file="header.jsp"%>
</h1>

<c:url var="addAction" value="/orders/add"/>

<form:form action="${addAction}" modelAttribute="order">
    <table>

        <c:if test="${!empty order.gosznak}">
            <tr>
                <td>
                    <form:label path="orderid">
                        <spring:message text="Orderid"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="orderid" readonly="true" size="8" disabled="true" required="required"/>
                    <form:hidden path="orderid"/>
                </td>
            </tr>
        </c:if>
        <tr>
            <td>
                <form:label path="gosznak">
                    <spring:message text="gosznak"/>
                </form:label>
            </td>
            <td>
                <form:input path="gosznak" required="required"/>
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="datein">
                    <spring:message text="datein"/>
                </form:label>
            </td>
            <td>
                <form:input type="date" path="datein" required="required"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="dateout">
                    <spring:message text="dateout"/>
                </form:label>
            </td>
            <td>
                <form:input type="date" path="dateout" required="required"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="discount">
                    <spring:message text="discount"/>
                </form:label>
            </td>
            <td>
                <form:input path="discount" required="required"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty order.gosznak}">
                    <input type="submit"
                           value="<spring:message text="Edit order"/>"/>
                </c:if>
                <c:if test="${empty order.gosznak}">
                    <input type="submit"
                           value="<spring:message text="Add order"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>

</br>
</br>

<c:if test="${!empty listOrders }">
    <table class="tg">
        <tr>
            <th width="80">orderid</th>

            <th width="80">gosznak</th>
            <th width="80">datein</th>
            <th width="80">dateout</th>
            <th width="80">discount</th>
            <th width="80">Edit</th>
            <th width="80">Delete</th>
        </tr>
        <c:forEach items="${listOrders}" var="order">
            <tr>
                <td>${order.orderid}</td>
                <td>${order.gosznak}</td>
                <td>${order.datein}</td>
                <td>${order.dateout}</td>
                <td>${order.discount}</td>
                <td><a href="<c:url value='/editorder/${order.orderid}'/>">Edit</a></td>
                <td><a href="<c:url value='/removeorder/${order.orderid}'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>








</body>
</html>

