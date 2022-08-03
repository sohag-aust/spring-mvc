<%--
  Created by IntelliJ IDEA.
  User: ashraful
  Date: ৩/৮/২২
  Time: ১০:৩৪ AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
</body>
</html>
