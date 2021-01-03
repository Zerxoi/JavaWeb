<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Targer</title>
</head>
<body>
    <%
        System.out.println("target.jsp");
        System.out.println("JSP Thread: " + Thread.currentThread().getName());
        System.out.println("JSP Request: " + request);
    %>
    target.jsp
</body>
</html>