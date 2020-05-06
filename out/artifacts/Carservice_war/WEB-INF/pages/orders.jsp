<%--
  Created by IntelliJ IDEA.
  User: daniil
  Date: 03.04.2020
  Time: 14:07
  To change this template use File | Settings | File Templates.
<%-- --%>
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
    <ul>
        <li>
            <a  href="/customers">Customers</a>
        </li>
        <li>
            <a href="/parts">Parts</a>
        </li>
        <li>
            <a href="/operations">Operations</a>
        </li>
        <li>
            <a class="active" href="/orders">Orders</a>
        </li>
        <li>
            <a href="/toorders">ToOrders</a>
        </li>
    </ul>
</head>

<body>
<br>
<br>
<%--
<c:url var="addAction" value="/orders/add"/>

<form:form action="${addAction}" modelAttribute="order">
    <table>

        <c:if test="${!empty order.customer.gosznak}">
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
                <form:label path="customer.gosznak">
                    <spring:message text="gosznak"/>
                </form:label>
            </td>
            <td>
                <form:input path="customer.gosznak" required="required"/>
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
                <c:if test="${!empty order.customer.gosznak}">
                    <input type="submit"
                           value="<spring:message text="Edit order"/>"/>
                </c:if>
                <c:if test="${empty order.customer.gosznak}">
                    <input type="submit"
                           value="<spring:message text="Add order"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
--%>
<br>
<br>

<a class="buttonEdit" href="/orders/chooseCustomer">Add New Order</a><br><br>

<c:if test="${!empty listOrders }">
    <c:forEach items="${listOrders}" var="order">
    <table class="tg">
        <tr>
            <th width="80">Order #${order.orderid}</th>
        </tr>
        <tr>
            <th width="80">gosznak</th>
            <th width="80">datein</th>
            <th width="80">dateout</th>

            <th width="80">discount</th>
            <th width="80">Total</th>

            <th width="80">Edit</th>
            <th width="80">Delete</th>
        </tr>
        <tr>

            <td>${order.customer.gosznak}</td>
            <td>${order.datein}</td>
            <td>${order.dateout}</td>


            <td>${order.discount}</td>
            <td>${order.totalcost}</td>

            <td><a  href="/orders/edit?id=${order.orderid}" >Add new Position</a></td>
            <td><a href="<c:url value='/removeorder/${order.orderid}'/>">Delete</a></td>


        </tr>

        <c:if test="${!empty order.toorders}">
            <tr>
                <th width="80" colspan="6"> Parts'n'Works </th>
            </tr>
            <tr>
                <th width="80" >Toorderid</th>
                <th width="80" >Product</th>
                <th width="80" >Category</th>
                <th width="80" >Type of work</th>
                <th width="80">Number of parts</th>
                <th width="80">Delete</th>
            </tr>
            <c:forEach items="${order.toorders}" var="prod">
                <tr>
                    <td>${prod.toorderid}</td>
                    <td>${prod.part.title}</td>
                    <td>${prod.part.category}</td>
                    <td>${prod.operation.description}</td>
                    <td>${prod.numofparts}</td>
                    <td><a a  href="/removetoorder/${prod.toorderid}">Delete</a></td>
                </tr>

            </c:forEach>
        </c:if>
        <br>
    </c:forEach>
    </table>
</c:if>


<%--
<table class="tg">
    <tr>
        <th width="80">title</th>
        <th width="80">price</th>
    </tr>
    <c:forEach items="${listpart}" var="part">
        <tr>
            <td>${part.title}</td>
            <td>${part.price}</td>
            <td>${order.discount}</td>
        </tr>
    </c:forEach>
</table>

--%>






</body>
</html>

