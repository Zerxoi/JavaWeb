<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <%
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
        pageContext.setAttribute("basePath", basePath);
    %>
    <base href="${ pageScope.basePath }">
</head>

<body>
    <form action="loginServlet" method="GET">
        <label for="username">用户名</label><input type="text" name="username"><br>
        <label for="password">密 码</label><input type="password" name="password"><br>
        <input type="submit" value="登录">
    </form>
</body>

</html>