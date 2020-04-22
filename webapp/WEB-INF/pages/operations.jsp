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

    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>
</head>

<body>
<h1>
    <a href="/customers">Customers</a>
    </br>
    <a href="/parts">Parts</a>
    </br>
    <a href="/operations">Operations</a>


</h1>

<c:url var="addAction" value="/operations/add"/>

<form:form action="${addAction}" modelAttribute="operation">
    <table>


        <tr>
            <td>
                <form:label path="operationid">
                    <spring:message text="operationid"/>
                </form:label>
            </td>
            <td>
                <form:input path="operationid"  size="9" />

            </td>
        </tr>

        <tr>
            <td>
                <form:label path="description">
                    <spring:message text="First Name"/>
                </form:label>
            </td>
            <td>
                <form:input path="description"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="price">
                    <spring:message text="Last name"/>
                </form:label>
            </td>
            <td>
                <form:input path="price"/>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <c:if test="${!empty operation.description}">
                    <input type="submit"
                           value="<spring:message text="Edit Customer"/>"/>
                </c:if>
                <c:if test="${empty operation.description}">
                    <input type="submit"
                           value="<spring:message text="Add New Customer"/>"/>
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
            <th width="80">Gosznak</th>
            <th width="80">First Name</th>
            <th width="80">Last Name</th>
            <th width="80">Telephone</th>
            <th width="80">Edit</th>
            <th width="80">Delete</th>
        </tr>
        <c:forEach items="${listOperations}" var="operation">
            <tr>
                <td>${operation.operationid}</td>
                <td>${operation.description}</td>
                <td>${operation.price}</td>

                <td><a href="<c:url value='/edit/${operation.operationid}'/>">Edit</a></td>
                <td><a href="<c:url value='/remove/${operation.operationid}'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>








</body>
</html>

