<%--
  Created by IntelliJ IDEA.
  User: ashraful
  Date: ৩/৮/২২
  Time: ১০:৩৪ AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %> <!-- enabling expression language. by default isElIgnored is true -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- enabling jstl -->
<html>
<head>
    <title>Help Page</title>
</head>
<body>
    <h1> Help Page is Loaded </h1>
    <%
        String country = (String) request.getAttribute("country");
    %>
    <h2> Country Name is : <%= country %></h2>
    <h2> Country Name using Expression Language (EL) : ${country} </h2> <!-- here country is the key name -->

    <hr>
    <h2> Divisions </h2>

    <%-- Showing Divisions using JSTL --%>
    <c:forEach var="division" items="${divisions}">
        <%--  Print Data using Expression Language (EL)  --%>
        <h3> Using Expression Language: ${division} </h3>

        <%--  Print Data using JSTL  --%>
        <h3> Using JSTL: <c:out value="${division}"></c:out> </h3>
    </c:forEach>

</body>
</html>
