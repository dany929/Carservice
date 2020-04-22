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

<c:url var="addAction" value="/parts/add"/>

<form:form action="${addAction}" modelAttribute="part">
    <table>

        <c:if test="${!empty part.title}">
        <tr>
            <td>
                <form:label path="partid">
                    <spring:message text="Partid"/>
                </form:label>
            </td>
            <td>
                <form:input path="partid" readonly="true" size="8" disabled="true"/>
                <form:hidden path="partid"/>
            </td>
        </tr>
        </c:if>
        <tr>
            <td>
                <form:label path="category">
                    <spring:message text="Category"/>
                </form:label>
            </td>
            <td>
                <form:input path="category"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="title">
                    <spring:message text="Title"/>
                </form:label>
            </td>
            <td>
                <form:input path="title"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="price">
                    <spring:message text="price"/>
                </form:label>
            </td>
            <td>
                <form:input path="price"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty part.title}">
                    <input type="submit"
                           value="<spring:message text="Edit Customer"/>"/>
                </c:if>
                <c:if test="${empty part.title}">
                    <input type="submit"
                           value="<spring:message text="Add New Customer"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>

</br>
</br>

<c:if test="${!empty listParts }">
    <table class="tg">
        <tr>
            <th width="80">Partid</th>
            <th width="80">Category</th>
            <th width="80">Title</th>
            <th width="80">Price</th>
            <th width="80">Edit</th>
            <th width="80">Delete</th>
        </tr>
        <c:forEach items="${listParts}" var="part">
            <tr>
                <td>${part.partid}</td>
                <td>${part.category}</td>
                <td>${part.title}</td>
                <td>${part.price}</td>
                <td><a href="<c:url value='/editpart/${part.partid}'/>">Edit</a></td>
                <td><a href="<c:url value='/removepart/${part.partid}'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>








</body>
</html>

