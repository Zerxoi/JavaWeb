<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <body>
        pageContext.getAttribute("key"): <%= pageContext.getAttribute("key") %> <br/>
        request.getAttribute("key"): <%= request.getAttribute("key") %> <br/>
        session.getAttribute("key"): <%= session.getAttribute("key") %> <br/>
        application.getAttribute("key"): <%= application.getAttribute("key") %>
    </body>
</html>