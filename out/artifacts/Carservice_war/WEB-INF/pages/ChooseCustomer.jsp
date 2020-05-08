<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=Cp1251" pageEncoding="Cp1251" %>

<html>
<head>
    <title>Add Product</title>
    <link rel="stylesheet" type="text/css" href="../../css/style.css">
</head>
<body>
<h1>
    Choose Customer
</h1>

<c:if test="${!empty listCustomers }">
    <table class="tg">
        <tr>
            <th width="80">Name</th>
            <th width="80">Surname</th>
            <th width="80">Age</th>
            <th width="80">Phone</th>
            <th width="80">Add</th>
        </tr>
        <c:forEach items="${listCustomers}" var="customer">
            <tr>
                <td>${customer.gosznak}</td>
                <td>${customer.firstname}</td>
                <td>${customer.lastname}</td>
                <td>${customer.tel}</td>
                <td>
                    <form:form action="client" method="POST">
                        <input type="hidden" name="id" value="${customer.gosznak}"/>
                        <input type="submit" class="btnadd" value="Add">
                    </form:form>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
