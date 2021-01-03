<html>

<body>
    <%
        String footer = "I'm a footer";
    %>
    <p>param("username"):<%= request.getParameter("username") %></p>
    <footer><%= footer  %></footer>
</body>

</html>