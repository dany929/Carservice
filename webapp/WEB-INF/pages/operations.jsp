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
    <title>Customers</title>
    <link rel="stylesheet" type="text/css" href="../../css/style.css">
</head>

<body>
<%@ include file="header.jsp"%>
<c:url var="addAction" value="/operations/add"/>

<form:form action="${addAction}" modelAttribute="operation">
    <table>

        <c:if test="${!empty operation.description}">
            <tr>
                <td>
                    <form:label path="operationid">
                        <spring:message text="OperationId"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="operationid" readonly="true" size="8" disabled="true" required="required"/>
                    <form:hidden path="operationid"/>
                </td>
            </tr>
        </c:if>

        <tr>
            <td>
                <form:label path="description">
                    <spring:message text="Description"/>
                </form:label>
            </td>
            <td>
                <form:input path="description" required="required"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="price">
                    <spring:message text="Price"/>
                </form:label>
            </td>
            <td>
                <form:input path="price" required="required"/>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <c:if test="${!empty operation.description}">
                    <input type="submit"
                           value="<spring:message text="Edit operation"/>"/>
                </c:if>
                <c:if test="${empty operation.description}">
                    <input type="submit"
                           value="<spring:message text="Add New operation"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>

</br>
</br>

<c:if test="${!empty listOperations }">
    <table class="tg">
        <tr>
            <th width="80">OperationID</th>
            <th width="80">Description</th>
            <th width="80">Price</th>
            <th width="80">Edit</th>
            <th width="80">Delete</th>
        </tr>
        <c:forEach items="${listOperations}" var="operation">
            <tr>
                <td>${operation.operationid}</td>
                <td>${operation.description}</td>
                <td>${operation.price}</td>

                <td><a href="<c:url value='/editoperation/${operation.operationid}'/>">Edit</a></td>
                <td><a href="<c:url value='/removeoperation/${operation.operationid}'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>








</body>
</html>

