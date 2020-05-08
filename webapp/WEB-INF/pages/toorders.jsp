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
            <a class="active" href="/toorders/">ToOrders</a>
        </li>
    </ul>
</head>

<body>
<br>
<br>



<br>
<br>
<%--
<c:if test="${!empty listToorder }">

        <c:forEach items="${listToorder}" var="toorder">
            <table class="tg">
            <tr>
                <th width="80" colspan="3">Orderid ${toorder.order.orderid}</th>
            </tr>
            <tr>
                <th width="80">Gosznak</th>
                <th width="80">Partid</th>
                <th width="80">PartTitle</th>
                <th width="80">PartPrice</th>
                <th width="80">Numofparts</th>
                <th width="80">Operid</th>
                <th width="80">OperDescr</th>
                <th width="80">OperPrice</th>
                <th width="80">Discount</th>
                <th width="80">Total</th>

                <th width="80">Edit</th>
                <th width="80">Delete</th>
            </tr>

            <tr>

                <td>${toorder.order.customer.gosznak}</td>
                <td>${toorder.part.partid}</td>
                <td>${toorder.part.title}</td>
                <td>${toorder.part.price}</td>
                <td>${toorder.numofparts}</td>
                <td>${toorder.operation.operationid}</td>
                <td>${toorder.operation.description}</td>
                <td>${toorder.operation.price}</td>
                <td>${toorder.order.discount}</td>
                <td>${toorder.order.totalcost}</td>

                <td><a href="<c:url value='/edittoorder/${toorder.toorderid}'/>">Edit</a></td>
                <td><a href="<c:url value='/removetoorder/${toorder.toorderid}'/>">Delete</a></td>
            </tr>
            <br>
        </c:forEach>
    </table>
</c:if>
 --%>
<br>

<c:if test="${!empty ordersFiltered}">
    <table>
        <tr>

        </tr>
        <c:forEach items="${ordersFiltered}" var="order">
            <t>
                <td>${order.orderid}</td>
                <td>${order.customer.gosznak}</td>
                <td>${order.totalcost}</td>
            </t>
        </c:forEach>
    </table>
</c:if>
<br>
<br>
<c:if test="${!empty productsListFiltered}">
    <table class="tg">
        <tr>
            <th width="80">Name</th>
            <th width="80">Type</th>
            <th width="80">Cost</th>
            <th width="80">Count</th>
        </tr>
        <c:forEach items="${productsListFiltered}" var="product">
            <tr>
                <td>${product.title}</td>
                <td>${product.category}</td>
                <td>${product.price}</td>

            </tr>
        </c:forEach>
    </table>
</c:if>

<h1> : </h1>

    <table class="tg">
        <tr>
            <th width="80">Name</th>
            <th width="80">Type</th>
            <th width="80">Cost</th>
            <th width="80">Count</th>
        </tr>
        <c:forEach items="${productsListOrdered}" var="product">
            <tr>
                <td>${product.title}</td>
                <td>${product.category}</td>
                <td>${product.price}</td>

            </tr>
        </c:forEach>
    </table>


</body>
</html>

