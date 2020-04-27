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
<%@ include file="header.jsp"%>


<c:url var="addAction" value="/parts/add"/>

<form:form action="${addAction}" modelAttribute="toorder">
    <table>


            <tr>
                <td>
                    <form:label path="orderid">
                        <spring:message text="Orderid"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="orderid"/>

                </td>
            </tr>

        <tr>
            <td>
                <form:label path="partid">
                    <spring:message text="partid"/>
                </form:label>
            </td>
            <td>
                <form:input path="partid"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="operationid">
                    <spring:message text="operationid"/>
                </form:label>
            </td>
            <td>
                <form:input path="operationid"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="numofparts">
                    <spring:message text="numofparts"/>
                </form:label>
            </td>
            <td>
                <form:input path="numofparts"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty toorder.orderid}">
                    <input type="submit"
                           value="<spring:message text="Edit Toorder"/>"/>
                </c:if>
                <c:if test="${empty toorder.orderid}">
                    <input type="submit"
                           value="<spring:message text="Add Toorder"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>

</br>
</br>

<c:if test="${!empty listToorder }">
    <table class="tg">
        <tr>
            <th width="80">Orderid</th>
            <th width="80">Partid</th>
            <th width="80">Operid</th>
            <th width="80">NumofParts</th>
            <th width="80">Edit</th>
            <th width="80">Delete</th>
        </tr>
        <c:forEach items="${listToorder}" var="toorder">
            <tr>
                <td>${toorder.orderid}</td>
                <td>${toorder.partid}</td>
                <td>${toorder.operationid}</td>
                <td>${toorder.numofparts}</td>
                <td><a href="<c:url value='/edittoorder/${toorder.orderid}'/>">Edit</a></td>
                <td><a href="<c:url value='/removetoorder/${toorder.orderid}'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>








</body>
</html>

