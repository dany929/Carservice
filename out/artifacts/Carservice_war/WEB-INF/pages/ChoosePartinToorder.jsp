<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=Cp1251" pageEncoding="Cp1251" %>
<html>
<head>
    <title>Choose product</title>
    <link rel="stylesheet" type="text/css" href="../../css/style.css">
</head>
<body>
<h1>Choose one combination of part and service work</h1>

<table class="tt">


            <form:form action="add" method="post">
                <tr>
                    <input class="btnadd" type="submit"  value="Add">
                </tr>
                <tr>
                    <th width="80">Number of parts</th>
                    <td>
                        <input type="number" name="num" min="1" max="10" pattern="[0-9]" value="1" required/>
                    </td>
                </tr>

                <tr>
                    <th>


                <c:if test="${!empty listParts}">
                    <input type="hidden" name="ord" value="${orderId}"/>
                    <input type="hidden" name="cst" value="${gosznak}"/>
                    <table class="tg">

                        <tr>
                            <th width="80">Category</th>
                            <th width="80">Title</th>
                            <th width="80">Price</th>
                            <th width="80">Options</th>
                        </tr>

                        <c:forEach items="${listParts}" var="part">
                            <tr>
                                <td>${part.category}</td>
                                <td>${part.title}</td>
                                <td>${part.price}</td>
                                <td><input type="checkbox" name="id" value="${part.partid}"/></td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
        </th>

        <th>
            <c:if test="${!empty listOperations}">
                <table class="tg">
                    <tr>
                        <th width="80">Title</th>
                        <th width="80">Price</th>
                        <th width="80">Options</th>
                    </tr>
                    <c:forEach items="${listOperations}" var="opr">
                        <tr>
                            <td>${opr.description}</td>
                            <td>${opr.price}</td>
                            <td>
                                <input type="checkbox" name="idopr" value="${opr.operationid}" /></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
            </form:form>
        </th>
    </tr>
</table>

</body>
</html>
