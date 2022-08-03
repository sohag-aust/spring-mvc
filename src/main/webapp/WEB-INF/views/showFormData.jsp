<%--
  Created by IntelliJ IDEA.
  User: ashraful
  Date: ৩/৮/২২
  Time: ১:৩১ PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %> <!-- enabling expression language. by default isElIgnored is true -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- enabling jstl -->
<html>
<head>
    <title>Data From Contact Form</title>
</head>
<body>
    <h1>${title}</h1>
    <h2>${desc}</h2>

<%--    <h1> Welcome ${email}, and your password is ${password} </h1>--%>
    <%--  now trying value from ModelAttribute user object  --%>
    <h1> Welcome ${user.email}, and your password is ${user.password} </h1>
</body>
</html>
