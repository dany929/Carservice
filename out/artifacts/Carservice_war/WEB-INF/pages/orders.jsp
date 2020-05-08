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
            <a  href="/customers/">Customers</a>
        </li>
        <li>
            <a href="/parts/">Parts</a>
        </li>
        <li>
            <a href="/operations/">Operations</a>
        </li>
        <li>
            <a class="active" href="/orders/">Orders</a>
        </li>
        <li>
            <a href="/toorders/">Queries</a>
        </li>
    </ul>
</head>

<body>
<br>
<form:form action="update" method="POST"  modelAttribute="order">
<table>

   <c:if test="${!empty order.customer.gosznak}">

            <tr>
                <td>
                    <form:label path="orderid">
                        <spring:message text="Order ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="orderid" readonly="true"  required="required"/>

                </td>
            </tr>

    <tr>
        <td>
            <form:label path="customer.gosznak">
                <spring:message text="Gosznak"/>
            </form:label>
        </td>
        <td>
            <form:input path="customer.gosznak" readonly="true" required="required"/>
        </td>
    </tr>

        <tr>
            <td>
                <form:label path="datein">
                    <spring:message text="Date in"/>
                </form:label>
            </td>
            <td>
                <form:input type="date" path="datein" required="required"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="dateout">
                    <spring:message text="Date out"/>
                </form:label>
            </td>
            <td>
                <form:input type="date" path="dateout" required="required"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="discount">
                    <spring:message text="Discount"/>
                </form:label>
            </td>
            <td>
                <form:input type="number" min="0" path="discount"  max="100"  pattern="[0-9]" required="required"/>
            </td>
        </tr>
       <br>

        <tr>
            <td colspan="2">
                <c:if test="${!empty order.customer}">
                    <input class="btnedit" type="submit"
                           value="<spring:message text="Edit order"/>"/>
                </c:if>
                <c:if test="${empty order.customer}">
                    <input class="btndel" type="submit"
                           value="<spring:message text="Add order"/>"/>
                </c:if>
            </td>
        </tr>
   </c:if>
    </table>
</form:form>
<br>

<a class="btnadd" href="/orders/chooseCustomer">Add New Order</a>
<br>
<br>

<c:if test="${!empty listOrders }">
    <c:forEach items="${listOrders}" var="order">
    <table class="tg">
        <tr>
            <th width="80">Order #${order.orderid}</th>
        </tr>
        <tr>
            <th width="80">Gosznak</th>
            <th width="80">Date In</th>
            <th width="80">Date Out</th>

            <th width="80">Discount</th>
            <th width="80">Total with Discount</th>
            <th width="80">Complition</th>
            <th width="80">Add Pos</th>
            <th width="80">Edit</th>
            <th width="80">Delete</th>
        </tr>
        <tr>

            <td>${order.customer.gosznak}</td>
            <td>${order.datein}</td>
            <td>${order.dateout}</td>


            <td>${order.discount}</td>
            <td>${order.totalcost}</td>

            <td>
                <form:form action="cpl" method="POST">
                <input type="hidden" name="id" value="${order.orderid}"/>
                <input type="submit" class="btnCL" value="Complete with Today Date">
                </form:form>
            </td>

            <td>
                <form:form action="edit" method="POST">
                <input type="hidden" name="id" value="${order.orderid}"/>
                <input type="submit" class="btnadd" value="Add new Position">
                </form:form>
            </td>

            <td>
                <form:form action="editorder" method="POST">
                    <input type="hidden" name="id" value="${order.orderid}"/>
                    <input type="submit" class="btnedit" value="Edit">
                </form:form>
            </td>

            <td>
                <form:form action="delete" method="POST">
                    <input type="hidden" name="id" value="${order.orderid}"/>
                    <input type="submit" class="btndel" value="Delete ORDER">
                </form:form>
            </td>



        </tr>

        <c:if test="${!empty order.toorders}">
            <tr>
                <th width="80" colspan="9" id="part"> Parts'n'Works </th>
            </tr>
            <tr>

                <th width="80" >Product Title</th>
                <th width="80" >Category</th>
                <th width="80">Number of Parts</th>
                <th width="80" >Total Part Cost</th>
                <th width="80" >Type of Work</th>
                <th width="80" >Work Cost</th>
                <th width="80" >Edit</th>
                <th width="80" colspan="2">Delete</th>
            </tr>
            <c:forEach items="${order.toorders}" var="prod">
                <tr>

                    <td>${prod.part.title}</td>
                    <td>${prod.part.category}</td>
                    <td>${prod.numofparts}</td>
                    <td>${prod.part.price*prod.numofparts}</td>
                    <td>${prod.operation.description}</td>
                    <td>${prod.operation.price}</td>
                    <td>
                        <form:form action="edittoorder" method="POST">
                        <input type="hidden" name="id" value="${prod.toorderid}"/>
                        <input  type="submit" class="btnedit" value="Edit">
                        </form:form>
                    </td>
                    <td colspan="2">
                        <form:form action="removetoorder" method="POST">
                        <input type="hidden" name="id" value="${prod.toorderid}"/>
                        <input type="submit" class="btndel" value="Remove from Order">
                        </form:form>
                    </td>
                </tr>

            </c:forEach>
        </c:if>
        <br>
        <br>
    </c:forEach>
    </table>
</c:if>

</body>
</html>

