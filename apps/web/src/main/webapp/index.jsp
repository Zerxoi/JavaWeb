<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    pageContext.setAttribute("basePath", basePath);
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <base href="${ pageScope.basePath }">
    <title>Index</title>
</head>
<body>
    <h1>
        首页
        <br>
        <jsp:include page="/greeting" />
    </h1>
</body>
</html>
