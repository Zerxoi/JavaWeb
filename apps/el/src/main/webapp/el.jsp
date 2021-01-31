<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ page import="xyz.zerxoi.*, java.util.*" %>
<html>
<head>
    <title>EL</title>
</head>
<body>
    <%
    request.setAttribute("key", "value");
    %>
    <h2>&lt;% request.setAttribute("key", "value"); %&gt;</h2>
    <br />
    <h2>JSP 脚本 &lt;%= request.getAttribute("key") %&gt;: <%= request.getAttribute("key") %></h2>
    <h2>EL表达式 &dollar;{ key }: ${ key } </h2>
    <br />
    <h2>JSP 脚本 &lt;%= request.getAttribute("key1") %&gt;: <%= request.getAttribute("key1") %></h2>
    <h2>EL表达式 &dollar;{ key1 }: ${ key1 } </h2>

    <%
        JavaBean bean = new JavaBean();
        request.setAttribute("bean", bean);
        bean.setName("Tohru");
        bean.setAge(3000);
        String[] interests = new String[] {"cpp", "java", "golang"};
        bean.setInterests(interests);
        List<String> list = new ArrayList<>();
        list.add("orz");
        list.add("mdzz");
        list.add("wdnmd");
        bean.setList(list);
        Map<String, String> map = new HashMap<>();
        map.put("u1s1", "有一说一");
        map.put("yyds", "永远的神");
        map.put("yygq", "阴阳怪气");
        bean.setMap(map);
    %>
    &dollar;{ bean }: ${ bean } <br />
    &dollar;{ bean.age }: ${ bean.age } <br />
    &dollar;{ bean.name }: ${ bean.name } <br />
    &dollar;{ bean.interests }: ${ bean.interests } <br />
    &dollar;{ bean.interests[1] }: ${ bean.interests[1] } <br />
    &dollar;{ bean.list }: ${ bean.list } <br />
    &dollar;{ bean.list[1] }: ${ bean.list[1] } <br />
    &dollar;{ bean.map }: ${ bean.map } <br />
    &dollar;{ bean.map.u1s1 }: ${ bean.map.u1s1 } <br />
    &dollar;{ bean.map["u1s1"] }: ${ bean.map["u1s1"] } <br />
    &dollar;{ bean.x }: ${ bean.x } <br />
</body>
</html>