<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<body>
    <table>
        <% for (int i = 1; i <= 9; i++)  { %>
        <tr>
            <% for (int j = 1; j <= i; j++) { %>
            <td><%= j + "*" + i + "=" + i*j %></td>
            <% } %>
        </tr>
        <% } %>
    </table>
</body>
</html>