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
            <a  href="/customers">Customers</a>
        </li>
        <li>
            <a href="/parts">Parts</a>
        </li>
        <li>
            <a href="/operations">Operations</a>
        </li>
        <li>
            <a  href="/orders">Orders</a>
        </li>
        <li>
            <a class="active" href="/toorders">ToOrders</a>
        </li>
    </ul>
</head>

<body>
<br>
<br>

    <%--
  <c:url var="addAction" value="/toorders/add"/>

  <form:form action="${addAction}" modelAttribute="toorder">
      <table>


                          <tr>
                              <td>
                                  <form:label path="orderid">
                                      <spring:message text="Orderid"/>
                                  </form:label>
                              </td>
                              <td>
                                  <form:input path="orderid" required="required"/>

                              </td>
                          </tr>

                      <tr>
                          <td>
                              <form:label path="partid">
                                  <spring:message text="partid"/>
                              </form:label>
                          </td>
                          <td>
                              <form:input path="partid" required="required"/>
                          </td>
                      </tr>

                      <tr>
                          <td>
                              <form:label path="operationid">
                                  <spring:message text="operationid"/>
                              </form:label>
                          </td>
                          <td>
                              <form:input path="operationid" required="required"/>
                          </td>
                      </tr>

          <tr>
              <td>
                  <form:label path="numofparts">
                      <spring:message text="numofparts"/>
                  </form:label>
              </td>
              <td>
                  <form:input path="numofparts" required="required"/>
              </td>
          </tr>

          <tr>
              <td colspan="2">
                  <c:if test="${!empty toorder.order.orderid}">
                      <input type="submit"
                             value="<spring:message text="Edit Toorder"/>"/>
                  </c:if>
                  <c:if test="${empty toorder.order.orderid}">
                      <input type="submit"
                             value="<spring:message text="Add Toorder"/>"/>
                  </c:if>
              </td>
          </tr>

      </table>
  </form:form>

  --%>

<br>
<br>

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
<%-- --%>
<br>
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


 --%>

</body>
</html>

