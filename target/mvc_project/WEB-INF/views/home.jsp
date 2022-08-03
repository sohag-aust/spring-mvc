<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Spring MVC</title>
</head>
<body>
    <h1> Home Page is called </h1>
    <h2> Spring MVC Page Load Successfully </h2>
    <h3> No Error Found </h3>
    <%
        String name = (String) request.getAttribute("name");
    %>
    <h1> My Name from Model is: <%= name %></h1>
</body>
</html>
