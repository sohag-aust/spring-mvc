<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ashraful
  Date: ২/৮/২২
  Time: ৪:৩৫ PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>About</title>
</head>
<body>
    <h1> This is our About Page </h1>
    <%
        List<String> infos = (List<String>) request.getAttribute("info");
    %>

    <%
        for(String info : infos) {
    %>
        <h1> <%= info %> </h1>
    <%
        }
    %>

</body>
</html>
