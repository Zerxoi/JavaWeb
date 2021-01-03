<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>b.jsp</title>
</head>
<body>
    <%
        Object user = session.getAttribute("user");
        if (user == null) {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
    %>
    用户名：${ sessionScope.user.username }
    密 码：${ sessionScope.user.password }
</body>
</html>