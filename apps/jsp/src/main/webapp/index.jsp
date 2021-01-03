<%@ page import="java.util.Map, java.util.HashMap" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="500.jsp" isErrorPage="true" %>
<html>

<body>
    <%-- <% int i = 12 / 0; %> --%>

    <%!
        private Integer id = 41917049;
        private String name = "zerxoi";
        private static Map<String, String> map;
    %>
    <%!
        static {
            map = new HashMap();
            map.put("key1", "value1");
            map.put("key2", "value2");
            map.put("key3", "value3");
        }
    %>
    <%!
        public int add(int a, int b) {
            return a + b;
        }
    %>
    <%!
        public static class A {
            private String name = "A";
        }
    %>
    
    <%-- <%@ include file="header.html" %> --%>
    <jsp:include page="header.html" />
    <h2>Hello World!</h2>
    <jsp:include page="footer.jsp">
        <jsp:param name="username" value="alice"/>
    </jsp:include>
    <%-- <%@ include file="footer.jsp" %> --%>

    <p>My ID is <%= id %> and my name is <%= name %></p>
    <p>Map: <%= map %></p>
    <p>username from query: <%= request.getParameter("username") %> </p>

    <table border="1">
    <%
        int i = 9;
        while (i > 0) {
            out.print("<tr><td>第" + i + "层</td></tr>");
            i--;
        }
    %>
    </table>

    <!-- <h1>Hello World</h1> -->

    <%
        // 单行注释
        /*
            多行注释
        */
    %>

    <%-- <% 12 / 0; %> --%>

</body>

</html>