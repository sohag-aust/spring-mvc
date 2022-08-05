<%--
  Created by IntelliJ IDEA.
  User: ashraful
  Date: ৫/৮/২২
  Time: ১২:৪২ PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %> <!-- enabling expression language. by default isElIgnored is true -->
<html>
<head>
    <title>Showing Fake User Data</title>
</head>
<body>
    <h1> Success </h1>
    <h3> Username: ${user.user_name} </h3>
    <h3> Email: ${user.email} </h3>
    <h3> Password: ${user.password} </h3>
    <h3> About: ${user.about} </h3>
</body>
</html>
