<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <body>
        <%
            pageContext.setAttribute("key", "pageContext");
            request.setAttribute("key", "request");
            session.setAttribute("key", "session");
            application.setAttribute("key", "application");
        %>

        pageContext.getAttribute("key"): <%= pageContext.getAttribute("key") %> <br/>
        request.getAttribute("key"): <%= request.getAttribute("key") %> <br/>
        session.getAttribute("key"): <%= session.getAttribute("key") %> <br/>
        application.getAttribute("key"): <%= application.getAttribute("key") %>

        <%
            request.getRequestDispatcher("/scope2.jsp").forward(request, response);
        %>
        <jsp:forward page="scope2.jsp"></jsp:forward>
    </body>
</html>