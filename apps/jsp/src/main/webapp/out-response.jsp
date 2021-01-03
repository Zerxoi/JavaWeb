<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <body>
        <%
            out.write("out 1st<br/>");
            out.flush();
            response.getWriter().write("writer 1st<br/>");
            out.write("out 2st<br/>");
            response.getWriter().write("writer 2st<br/>");
            out.flush();
        %>
    </body>
</html>