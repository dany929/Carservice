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
            <a class="active"href="/parts/">Parts</a>
        </li>
        <li>
            <a href="/operations/">Operations</a>
        </li>
        <li>
            <a  href="/orders/">Orders</a>
        </li>
        <li>
            <a href="/toorders/">Queries</a>
        </li>
    </ul>
</head>

<body>

<br>
<br>


<form:form action="add" method="POST"  modelAttribute="part">
    <table>

        <c:if test="${!empty part.title}">
        <tr>
            <td>
                <form:label path="partid">
                    <spring:message text="Partid"/>
                </form:label>
            </td>
            <td>
                <form:input path="partid" readonly="true" size="8"  required="required"/>
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
                <form:input path="category" pattern="^[A-Za-z\s]+$" required="required"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="title">
                    <spring:message text="Title"/>
                </form:label>
            </td>
            <td>
                <form:input path="title" pattern="^[A-Za-z\s]+$" required="required"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="price">
                    <spring:message text="price"/>
                </form:label>
            </td>
            <td>
                <form:input path="price" type="number" min="1" pattern="[0-9]{6}" required="required"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty part.title}">
                    <input class="btnedit" type="submit"
                           value="<spring:message text="Edit Part"/>"/>
                </c:if>
                <c:if test="${empty part.title}">
                    <input class="btnadd" type="submit"
                           value="<spring:message text="Add Part"/>"/>
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
                <td>
                    <form:form action="edit" method="POST">
                        <input type="hidden" name="id" value="${part.partid}"/>
                        <input type="submit" class="btnedit" value="Edit">
                    </form:form>
                </td>
                <td>
                    <form:form action="remove" method="POST">
                        <input type="hidden" name="id" value="${part.partid}"/>
                        <input type="submit" class="btndel" value="Delete">
                    </form:form>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>








</body>
</html>

