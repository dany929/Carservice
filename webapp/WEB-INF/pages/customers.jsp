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
    <!-- Подключение библиотеки jQuery -->
    <script type="text/javascript" src="../../jquery.js"></script>
    <!-- Подключение jQuery плагина Masked Input -->
    <script type="text/javascript" src="../../jquery.mask.js"></script>
    <script type="text/javascript" src="../../jquery.mask.min.js"></script>
    <script>
        $(document).ready(function(){
            $('.phone').mask('8-000-000-00-00');
        <!--  $('.gosznak').mask('\\D [^0-9]') -->
        })
    </script>

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
<%@ include file="header.jsp"%>

<c:url var="addAction" value="/customers/add"/>

<form:form action="${addAction}" modelAttribute="customer">
    <table>


            <tr>
                <td>
                    <form:label path="gosznak">
                        <spring:message text="Gosznak"/>
                    </form:label>
                </td>
                <td>
                    <form:input class="gosznak" name="znak"  path="gosznak"  size="9" />

                </td>
            </tr>

        <tr>
            <td>
                <form:label path="firstname">
                    <spring:message text="First Name"/>
                </form:label>
            </td>
            <td>
                <form:input path="firstname"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="lastname">
                    <spring:message text="Last name"/>
                </form:label>
            </td>
            <td>
                <form:input path="lastname"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="tel">
                    <spring:message text="Telephone"/>
                </form:label>
            </td>
            <td>

                <form:input path="tel" class="phone"  type="text"/>


            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty customer.gosznak}">
                    <input type="submit"
                           value="<spring:message text="Edit Customer"/>"/>
                </c:if>
                <c:if test="${empty customer.gosznak}">
                    <input type="submit"
                           value="<spring:message text="Add New Customer"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>

</br>
</br>

<c:if test="${!empty listCustomers }">
    <table class="tg">
        <tr>
            <th width="80">Gosznak</th>
            <th width="80">First Name</th>
            <th width="80">Last Name</th>
            <th width="80">Telephone</th>
            <th width="80">Edit</th>
            <th width="80">Delete</th>
        </tr>
        <c:forEach items="${listCustomers}" var="customer">
            <tr>
                <td>${customer.gosznak}</td>
                <td>${customer.firstname}</td>
                <td>${customer.lastname}</td>
                <td>${customer.tel}</td>
                <td><a href="<c:url value='/edit/${customer.gosznak}'/>">Edit</a></td>
                <td><a href="<c:url value='/remove/${customer.gosznak}'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>








</body>
</html>

