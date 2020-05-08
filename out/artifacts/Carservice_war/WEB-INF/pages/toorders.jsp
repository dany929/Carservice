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
    <title>Parts</title>
    <link rel="stylesheet" type="text/css" href="../../css/style.css">
    <ul>
        <li>
            <a  href="/customers/">Customers</a>
        </li>
        <li>
            <a href="/parts/">Parts</a>
        </li>
        <li>
            <a href="/operations/">Operations</a>
        </li>
        <li>
            <a  href="/orders/">Orders</a>
        </li>
        <li>
            <a class="active" href="/toorders/">Queries</a>
        </li>
    </ul>
</head>
<body>
<br>
<h1>Orders with higher total cost, than average cost among all orders</h1>
<c:if test="${!empty ordersFiltered}">
    <table  class="tg">
        <tr>
            <th width="80">Order Num</th>
            <th width="80">Gosznak</th>
            <th width="80">First name</th>
            <th width="80">Last name</th>
            <th width="80">Date out</th>
            <th width="80">Total Cost</th>
        </tr>
        <c:forEach items="${ordersFiltered}" var="order">
            <tr>
                <td>${order.orderid}</td>
                <td>${order.customer.gosznak}</td>
                <td>${order.customer.firstname}</td>
                <td>${order.customer.lastname}</td>
                <td>${order.dateout}</td>
                <td>${order.totalcost}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<br>
<h1>Ordered parts</h1>

<c:if test="${!empty partsOrdered}">
    <table  class="tg">
        <tr>
            <th>Part Category</th>
            <th>Title</th>
            <th>Price</th>
        </tr>
        <c:forEach items="${partsOrdered}" var="part">
            <tr>
                <td>${part.category}</td>
                <td>${part.title}</td>
                <td>${part.price}</td>
            </tr>
        </c:forEach>
    </table>

</c:if>

</body>
</html>

