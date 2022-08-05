<%--
  Created by IntelliJ IDEA.
  User: ashraful
  Date: ৫/৮/২২
  Time: ৪:২০ PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- enabling jstl -->
<html>
<head>
    <title>All Users</title>
</head>
<body>
    <%-- Showing Users using JSTL --%>
    <c:forEach var="user" items="${users}">
        <h3> Id: ${user.id} </h3>
        <h3> Username: ${user.user_name} </h3>
        <h3> Email: ${user.email} </h3>
        <h3> Password: ${user.password} </h3>
        <h3> About: ${user.about} </h3>
        <hr>
    </c:forEach>
</body>
</html>
