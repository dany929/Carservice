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
            $('.gosznak').mask('G000GG000',{
                translation: {
                    'G':{
                        pattern: /[A-CEKMHOPTY]/
                    }
                }
            });

            $('.name').mask('LUUUUUUUUUUUUUUU',
                {
                translation: {
                    'L':{pattern: /[A-Z]/},
                    'U':{pattern: /[a-z]/}

                }
            });

        })
    </script>

    <title>Customers</title>

    <link rel="stylesheet" type="text/css" href="../../css/style.css">

    <ul>
        <li>
            <a class="active" href="/customers">Customers</a>
        </li>
        <li>
            <a href="/parts/">Parts</a>
        </li>
        <li>
            <a href="/operations/">Operations</a>
        </li>
        <li>
            <a  href="/orders/">Orders</a>
        </li>
        <li>
            <a href="/toorders/">ToOrders</a>
        </li>
    </ul>
</head>

<body>
<br>
<br>


<form:form action="add" method="POST"  modelAttribute="customer">
    <table>


            <tr>
                <td>
                    <form:label path="gosznak">
                        <spring:message text="Gosznak"/>
                    </form:label>
                </td>
                <td>
                    <form:input class="gosznak" name="znak"  path="gosznak"  size="9" required="required" />

                </td>
            </tr>

        <tr>
            <td>
                <form:label path="firstname">
                    <spring:message text="First Name"/>
                </form:label>
            </td>
            <td>
                <form:input class="name" path="firstname" required="required" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="lastname">
                    <spring:message text="Last name"/>
                </form:label>
            </td>
            <td>
                <form:input class="name" path="lastname" required="required"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="tel">
                    <spring:message text="Telephone"/>
                </form:label>
            </td>
            <td>

                <form:input path="tel" class="phone" required="required"/>


            </td>
        </tr>

        <tr>
            <td colspan="2">  <c:if test="${!empty customer.gosznak}">
                <input type="submit"
                       value="<spring:message text="Edit Part"/>"/>
            </c:if>
                <c:if test="${empty customer.gosznak}">
                    <input type="submit"
                           value="<spring:message text="Add Part"/>"/>
                </c:if></td>
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
                <td>
                    <form:form action="edit" method="POST">
                    <input type="hidden" name="id" value="${customer.gosznak}"/>
                    <input type="submit" class="buttonEdit" value="Edit">
                </form:form>
                </td>
                <td>
                    <form:form action="remove" method="POST">
                    <input type="hidden" name="id" value="${customer.gosznak}"/>
                    <input type="submit" class="buttonDel" value="Delete">
                </form:form>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>








</body>
</html>

